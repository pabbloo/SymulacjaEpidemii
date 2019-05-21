package epidemia;

import java.util.Random;

import static epidemia.Simulation.WIELKOSCMAPY;

public abstract class ASpecimen implements ISpecimen {
    public int xPos, yPos;
    public boolean isAlive;

    Random generator = new Random();

    public void generateStartingLocation() {
        int los = generator.nextInt(WIELKOSCMAPY);
        this.xPos=los;
        los = generator.nextInt(WIELKOSCMAPY);
        this.yPos=los;
    }

    public void move(){


        int mnoznik=1,pozycja,los;

        do {
            los = generator.nextInt(100);
            if (los > 49) mnoznik = -1;
            los = generator.nextInt(100);
            pozycja=this.xPos+(los*mnoznik);

        }while((pozycja<0) || (pozycja>1000));

        this.xPos =pozycja;

        do {
            los = generator.nextInt(100);
            if (los > 49) mnoznik = -1;
            los = generator.nextInt(100);
            pozycja=this.yPos+(los*mnoznik);

        }while((pozycja<0) || (pozycja>1000));

        this.yPos=pozycja;
    }

    public int getXPos(){
        return this.xPos;
    }
    public int getYPos(){
        return this.yPos;
    }
}
