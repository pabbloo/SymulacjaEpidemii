package epidemia;

import static epidemia.Simulation.LICZNIK;

public class Virus extends ASpecimen{
    public int lifespan;

    public Virus(){
        isAlive=true;
        isInfected=true;
        lifespan=80;
        this.generateStartingLocation();
    }

    public void turn(){
        if (this.isAlive==true) {
            this.move();
            this.die();
        }
    }


    public void die(){
        if (LICZNIK>lifespan){
            isAlive=false;
        }

    }

}
