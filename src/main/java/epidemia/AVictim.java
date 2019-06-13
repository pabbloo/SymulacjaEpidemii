package epidemia;

import java.util.Random;

import static epidemia.Simulation.DURATION;
import static epidemia.Simulation.txt;

/**
 * Abstract class of victim, which is a type of specimen who got infected during the simulation
 */
public abstract class AVictim extends ASpecimen {
    protected int immunity;
    private final int chanceOfDeath = 3;

    /**
     * @return Integer immunity of current specimen
     */
    public int getImmunity() {
        return immunity;
    }

    /**
     * Generates random Integer which is used to determine if victim can die
     */
    private void tryToDie() {
        Random generator = new Random();

        int los = (generator.nextInt(100) + 1);

        if (los < chanceOfDeath) {
            isAlive = false;
            txt.println(DURATION + ": Specimen " + this.getType() + " has DIED due to infection with probability " + los + "<" + chanceOfDeath);
        }

    }

    /**
     * Method called by Map to perform a specimen's turn
     */
    @Override
    public void turn() {
        if (this.isInfected) {
            this.tryToDie();
        }
        this.move();
    }
}
