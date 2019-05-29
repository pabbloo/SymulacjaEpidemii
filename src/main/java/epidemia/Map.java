package epidemia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import static epidemia.Simulation.DURATION;
import static java.lang.Math.abs;
import static java.lang.Math.floor;

public class Map {
    static final int MAPSIZE = 1000;

    public int HospitalPos[] = new int[2];
    public ISpecimen ArrSpecimen[];

    private int population;
    private Random generator = new Random();
    private HashMap<Integer, String> lista = new HashMap<>();

    public Map(int populacja) {
        population = populacja;

        ArrSpecimen = new ISpecimen[population];

        ArrSpecimen[0] = new Virus();

        double Dhuman = floor(population * 0.6);
        int human = (int) Dhuman;

        for (int i = 1; i <= human; i++) {
            ArrSpecimen[i] = new Human();
        }

        for (int j = human + 1; j < population; j++) {
            ArrSpecimen[j] = new Animal();
        }

        int los = generator.nextInt(MAPSIZE - 30);
        HospitalPos[0] = los;
        los = generator.nextInt(MAPSIZE - 30);
        HospitalPos[1] = los;


        System.out.println("Created " + population + " specimens: ");
        for (int k = 0; k < population; k++) {
            System.out.println(k + ". " + ArrSpecimen[k].getType());
        }
        System.out.println("Generated Hospital at: (" + HospitalPos[0] + ", " + HospitalPos[1] + ")");
        System.out.println("");
        System.out.println(DURATION + ": SIMULATION HAS STARTED");
    }


    private void tryToInfect(int firstObject, int secondObject) {
        int los = generator.nextInt(10) + 1;
        if ((!ArrSpecimen[firstObject].checkInfection()) && (ArrSpecimen[secondObject].checkInfection())) {
            if ((ArrSpecimen[firstObject].getImmunity() <= los) && (firstObject != 0)) {
                ArrSpecimen[firstObject].infect();
                System.out.println(DURATION + ": Specimen " + firstObject + " " + ArrSpecimen[firstObject].getType() + " has been INFECTED by specimen " + secondObject + " " + ArrSpecimen[secondObject].getType() + " with efficiency " + los + ", which was more or equal immunity " + ArrSpecimen[firstObject].getImmunity());
            } else
                System.out.println(DURATION + ": Specimen " + firstObject + " " + ArrSpecimen[firstObject].getType() + " was IMMUNE at " + secondObject + " " + ArrSpecimen[secondObject].getType() + " contact. Efficiency " + los + " was less than immunity " + ArrSpecimen[firstObject].getImmunity());
        }
    }

    private void fillHashMap() {
        lista.clear();

        for (int k = 0; k < population; k++) {
            lista.put(k, "zawartosc" + Integer.toString(k));
        }
    }

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
                    for (int k = 0; k < ID.size(); k++) {
                        if (ArrSpecimen[ID.get(k)].checkAlive()) {
                            tryToInfect(j, ID.get(k));
                            tryToInfect(ID.get(k), j);
                        }
                    }
                }
            }
        }
    }

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
                System.out.println(DURATION + ": Collision detected. Objects " + i + ", " + j + " (" + x1 + ", " + y1 + "); (" + x2 + ", " + y2 + ")");
                vec.add(j);
            }

        }
        return vec;
    }
}

