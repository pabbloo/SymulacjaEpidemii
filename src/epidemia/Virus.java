package epidemia;

import static epidemia.Simulation.DURATION;

public class Virus extends ASpecimen {
    private int lifespan;

    public int getImmunity() {
        return 0;
    }

    public String getType() {
        return "Virus";
    }

    public Virus() {
        isAlive = true;
        isInfected = true;
        lifespan = 80;
        this.generateStartingLocation();
    }

    public void turn() {
        this.move();
        this.die();
    }

    public void hospitalContact() {
        this.isAlive = false;
        System.out.println(DURATION + ": Virus has DIED due to HOSPITAL contact");
    }

    private void die() {
        if (DURATION >= this.lifespan) {
            this.isAlive = false;
            System.out.println(DURATION + ": Virus has DIED due to lifespan limit");
        }

    }

}
