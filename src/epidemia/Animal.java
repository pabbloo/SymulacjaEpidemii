package epidemia;

public class Animal extends AVictim{

    public Animal(){
        this.immunity=5;
        this.isAlive=true;
        this.isInfected=false;
        this.generateStartingLocation();
    }
}
