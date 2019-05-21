package epidemia;

public class Animal extends AVictim{

    public Animal(){
        this.immunity=3;
        this.isAlive=true;
        this.isInfected=false;
        this.generateStartingLocation();
    }
}
