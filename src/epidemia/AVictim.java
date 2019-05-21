package epidemia;

import java.util.Random;

public class AVictim extends ASpecimen{
    public boolean isInfected;
    public int immunity;
    private int prawdopodobienstwoZgonu=20;

    protected boolean checkInfection(){

        return isInfected;
    }

    protected void tryToDie(){
        Random generator = new Random();

        int los = (generator.nextInt(100)+1);

        if (los<prawdopodobienstwoZgonu){
            isAlive=false;
        }
    }
}
