package epidemia;

public class Human extends AVictim{

    public Human(){
        this.immunity=8;
        this.isAlive=true;
        this.isInfected=false;
        this.generateStartingLocation();
    }
}
