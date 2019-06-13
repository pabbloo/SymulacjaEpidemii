package epidemia;

import static epidemia.Simulation.DURATION;
import static epidemia.Simulation.txt;

/**
 *Type of specimen who is infected at the start of simulation
 */
public class Virus extends ASpecimen {
    private final int lifespan;

    /**
     * @return Integer immunity of current specimen
     */
    public int getImmunity() {
        return 0;
    }

    /**
     * @return string with type of specimen
     */
    public String getType() {
        return "Virus";
    }

    /**
     * Creates a virus with set values
     */
    public Virus() {
        isAlive = true;
        isInfected = true;
        lifespan = 80;
        this.generateStartingLocation();
    }

    /**
     * Method called by Map to perform a virus's turn
     */
    public void turn() {
        this.move();
        this.die();
    }

    /**
     * Method which is called after contact with Hospital; Kills and sets isAlive to false
     */
    public void hospitalContact() {
        this.isAlive = false;
        txt.println(DURATION + ": Virus has DIED due to HOSPITAL contact");
    }

    /**
     * Method which kills the virus after set number of iterations
     */
    private void die() {
        if (DURATION >= this.lifespan) {
            this.isAlive = false;
            txt.println(DURATION + ": Virus has DIED due to lifespan limit");
        }

    }

}
