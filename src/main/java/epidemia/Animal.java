package epidemia;

import static epidemia.Simulation.DURATION;

/**
 * Type of a Victim with immunity=3; Can be killed in hospital
 */
public class Animal extends AVictim {

    /**
     * Creates Animal set values and random position
     */
    public Animal() {
        this.immunity = 3;
        this.isAlive = true;
        this.isInfected = false;
        this.generateStartingLocation();
    }

    /**
     * @return string with type of specimen
     */
    public String getType() {
        return "Animal";
    }

    /**
     * Method which is called after contact with Hospital; Kills and sets isAlive to false
     */
    public void hospitalContact() {
        if (this.isInfected) {
            this.isAlive = false;
            System.out.println(DURATION + ": Animal has DIED due to HOSPITAL contact.");
        }
    }
}
