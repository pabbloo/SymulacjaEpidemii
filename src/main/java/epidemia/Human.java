package epidemia;

import static epidemia.Simulation.DURATION;

public class Human extends AVictim {

    public Human() {
        this.immunity = 6;
        this.isAlive = true;
        this.isInfected = false;
        this.generateStartingLocation();
    }

    public String getType() {
        return "Human";
    }

    public void hospitalContact() {
        if (this.isInfected) {
            this.isInfected = false;
            System.out.println(DURATION + ": Human has been CURED at HOSPITAL!");
        }
    }
}
