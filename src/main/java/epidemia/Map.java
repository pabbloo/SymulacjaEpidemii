package epidemia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import static epidemia.Simulation.DURATION;
import static epidemia.Simulation.txt;
import static java.lang.Math.abs;
import static java.lang.Math.floor;

/**
 * Class which contains specimens
 */
public class Map {
    static final int mapSize = 1000;

    /**
     * Stores integer x and integer y as hospital position in array. 0-X, 1=Y
     */
    public int HospitalPos[] = new int[2];

    /**
     * Main array with specimens
     */
    public ISpecimen ArrSpecimen[];


    private int population;
    private Random generator = new Random();
    private HashMap<Integer, String> lista = new HashMap<>();

    /**
     * Fills map with specimens
     *
     * @param population how many specimens will program spawn on the map
     */
    public Map(int population) {
        this.population = population;

        ArrSpecimen = new ISpecimen[this.population];

        ArrSpecimen[0] = new Virus();

        double Dhuman = floor(this.population * 0.6);
        int human = (int) Dhuman;

        for (int i = 1; i <= human; i++) {
            ArrSpecimen[i] = new Human();
        }

        for (int j = human + 1; j < this.population; j++) {
            ArrSpecimen[j] = new Animal();
        }

        int los = generator.nextInt(mapSize - 30);
        HospitalPos[0] = los;
        los = generator.nextInt(mapSize - 30);
        HospitalPos[1] = los;

        txt.println("Created " + this.population + " specimens: ");
        for (int k = 0; k < this.population; k++) {
            txt.println(k + ". " + ArrSpecimen[k].getType());
        }
        txt.println("Generated Hospital at: (" + HospitalPos[0] + ", " + HospitalPos[1] + ")");
        txt.println("===============================================");
        txt.println(DURATION + ": SIMULATION HAS STARTED");
    }


    /**
     * Method tries to infect specimens who have collided with each other
     *
     * @param firstObject index of the first specimen who collided with another
     * @param secondObject index of the second specimen who collided with another
     */
    private void tryToInfect(int firstObject, int secondObject) {
        int los = generator.nextInt(10) + 1;
        if ((!ArrSpecimen[firstObject].checkInfection()) && (ArrSpecimen[secondObject].checkInfection())) {
            if ((ArrSpecimen[firstObject].getImmunity() <= los) && (firstObject != 0)) {
                ArrSpecimen[firstObject].infect();
                txt.println(DURATION + ": Specimen " + firstObject + " " + ArrSpecimen[firstObject].getType() + " has been INFECTED by specimen " + secondObject + " " + ArrSpecimen[secondObject].getType() + " with efficiency " + los + ", which was more or equal immunity " + ArrSpecimen[firstObject].getImmunity());
            } else
                txt.println(DURATION + ": Specimen " + firstObject + " " + ArrSpecimen[firstObject].getType() + " was IMMUNE at " + secondObject + " " + ArrSpecimen[secondObject].getType() + " contact. Efficiency " + los + " was less than immunity " + ArrSpecimen[firstObject].getImmunity());
        }
    }

    /**
     * fills HashMap which is used to detect correct collisions
     */
    private void fillHashMap() {
        lista.clear();

        for (int k = 0; k < population; k++) {
            lista.put(k, "zawartosc" + k);
        }
    }

    /**
     * Method called by main loop; performs basic activity and logic on map
     */
    public void turn() {
        fillHashMap();

        for (int i = 0; i < population; i++) {
            if (ArrSpecimen[i].checkAlive()) {

                ArrSpecimen[i].turn();

                if ((abs(ArrSpecimen[i].getXPos() - HospitalPos[0]) < 30) && (abs(ArrSpecimen[i].getYPos() - HospitalPos[1]) < 30)) {
                    ArrSpecimen[i].hospitalContact();
                }
            }
        }

        for (int j = 0; j < population; j++) {

            if (ArrSpecimen[j].checkAlive()) {
                ArrayList<Integer> ID = collisionDetection(j);
                if (ID != null) {
                    for (int k : ID) {
                        if (ArrSpecimen[k].checkAlive()) {
                            tryToInfect(j, k);
                            tryToInfect(k, j);
                        }
                    }
                }
            }
        }
    }

    /**
     * Method detects if any of specimens had collision
     *
     * @param i index of specimen for which we check collisions
     * @return ArrayList with indexes of specimens who collided with i-specimen
     */
    private ArrayList<Integer> collisionDetection(int i) {

        ArrayList<Integer> vec = new ArrayList<>();

        lista.remove(i);
        for (int j : lista.keySet()) {

            int x1, x2, y1, y2, roznicaX, roznicaY;

            x1 = ArrSpecimen[i].getXPos();
            y1 = ArrSpecimen[i].getYPos();
            x2 = ArrSpecimen[j].getXPos();
            y2 = ArrSpecimen[j].getYPos();

            roznicaX = abs(x1 - x2);
            roznicaY = abs(y1 - y2);

            if (((roznicaX < 30) && (roznicaY < 30)) && (i != j)) {
                txt.println(DURATION + ": Collision detected. Objects " + i + ", " + j + " (" + x1 + ", " + y1 + "); (" + x2 + ", " + y2 + ")");
                vec.add(j);
            }

        }
        return vec;
    }
}

