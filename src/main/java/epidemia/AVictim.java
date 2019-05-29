package epidemia;

import java.util.Random;

import static epidemia.Simulation.DURATION;

public abstract class AVictim extends ASpecimen {
    protected int immunity;
    private final int prawdopodobienstwoZgonu = 3;

    public int getImmunity() {
        return immunity;
    }

    private void tryToDie() {
        Random generator = new Random();

        int los = (generator.nextInt(100) + 1);

        if (los < prawdopodobienstwoZgonu) {
            isAlive = false;
            System.out.println(DURATION + ": Specimen " + this.getType() + " has DIED due to infection with probability " + los + "<" + prawdopodobienstwoZgonu);
        }

    }

    public void turn() {
        if (this.isInfected) {
            this.tryToDie();
        }
        this.move();
    }
}
