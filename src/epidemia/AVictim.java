package epidemia;

import java.util.Random;

public abstract class AVictim extends ASpecimen{
    protected int immunity;
    private int prawdopodobienstwoZgonu=3;

    public void Infect(){
        isInfected=true;
    }
    public int getImmunity(){return immunity;}

    private void tryToDie(){
        Random generator = new Random();

        int los = (generator.nextInt(100)+1);

        if (los<prawdopodobienstwoZgonu){
            isAlive=false;
        }


    }
    public void turn() {
                if (this.isInfected){
                    this.tryToDie();
                }
            this.move();
    }
}
