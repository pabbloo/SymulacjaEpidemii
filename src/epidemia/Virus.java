package epidemia;

import static epidemia.Simulation.LICZNIK;

public class Virus extends ASpecimen{
    private int lifespan;

    public int getImmunity(){return 0;}

    public Virus(){
        isAlive=true;
        isInfected=true;
        lifespan=80;
        this.generateStartingLocation();
    }

    public void turn(){
        if (this.isAlive) {
            this.move();
            this.die();
        }
    }


    private void die(){
        if (LICZNIK>lifespan){
            isAlive=false;
        }

    }

}
