package epidemia;

import java.util.Random;

import static epidemia.Simulation.WIELKOSCMAPY;

public abstract class ASpecimen implements ISpecimen {
    public int xPos, yPos;
    public boolean isAlive;
    public boolean isInfected;

    Random generator = new Random();

    public void infect(){
        isInfected=true;
    }

    public boolean checkInfection(){
        return isInfected;
    }

    public boolean checkAlive(){
        return isAlive;
    }

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
            if (los > 60) mnoznik = -1;
            los = generator.nextInt(300);
            pozycja=this.xPos+(los*mnoznik);

        }while((pozycja<0) || (pozycja>WIELKOSCMAPY-30));

        this.xPos =pozycja;

        do {
            los = generator.nextInt(100);
            if (los > 60) mnoznik = -1;
            los = generator.nextInt(300);
            pozycja=this.yPos+(los*mnoznik);

        }while((pozycja<0) || (pozycja>WIELKOSCMAPY-30));

        this.yPos=pozycja;
    }

    public int getXPos(){
        return this.xPos;
    }
    public int getYPos(){
        return this.yPos;
    }
}
