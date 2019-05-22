package epidemia;

import static epidemia.Simulation.LICZNIK;

public class Animal extends AVictim{

    public Animal(){
        this.immunity=3;
        this.isAlive=true;
        this.isInfected=false;
        this.generateStartingLocation();
    }
    public String getType(){return "Animal";}

    public void hospitalContact(){
        this.isAlive=false;
        System.out.println(LICZNIK+": Animal has DIED due to HOSPITAL contact.");
    }
}
