package epidemia;

import static epidemia.Simulation.LICZNIK;

public class Virus extends ASpecimen{
    public int lifespan;

    public Virus(){
        isAlive=true;
        lifespan=5000;
        this.generateStartingLocation();
    }

    public void infect(){
        //infekcje chyba mapa robi?
    }

    public void die(){
        if (LICZNIK>lifespan){
            isAlive=false;
        }

    }

}
