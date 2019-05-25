package epidemia;

import static epidemia.Simulation.DURATION;

public class Animal extends AVictim {

    public Animal() {
        this.immunity = 3;
        this.isAlive = true;
        this.isInfected = false;
        this.generateStartingLocation();
    }

    public String getType() {
        return "Animal";
    }

    public void hospitalContact() {
        this.isAlive = false;
        System.out.println(DURATION + ": Animal has DIED due to HOSPITAL contact.");
    }
}
