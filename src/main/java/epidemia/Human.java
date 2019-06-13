package epidemia;

import static epidemia.Simulation.DURATION;
import static epidemia.Simulation.txt;

/**
 * Type of a Victim with immunity=6; Can be treated at Hospital
 */
public class Human extends AVictim {

    /**
     * Creates Human with set values and random position
     */
    public Human() {
        this.immunity = 6;
        this.isAlive = true;
        this.isInfected = false;
        this.generateStartingLocation();
    }

    /**
     * @return string with type of specimen
     */
    public String getType() {
        return "Human";
    }

    /**
     * Method which is called after contact with Hospital; Cures and sets isInfected to false
     */
    public void hospitalContact() {
        if (this.isInfected) {
            this.isInfected = false;
            txt.println(DURATION + ": Human has been CURED at HOSPITAL!");
        }
    }
}
