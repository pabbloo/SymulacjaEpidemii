package epidemia;

import java.util.Random;

import static epidemia.Map.MAPSIZE;

public abstract class ASpecimen implements ISpecimen {
    private int xPos, yPos;
    public boolean isAlive;
    public boolean isInfected;

    Random generator = new Random();

    public void infect() {
        isInfected = true;
    }

    public boolean checkInfection() {
        return isInfected;
    }

    public boolean checkAlive() {
        return isAlive;
    }

    public void generateStartingLocation() {
        int los = generator.nextInt(MAPSIZE);
        this.xPos = los;
        los = generator.nextInt(MAPSIZE);
        this.yPos = los;
    }

    public void move() {
        int pozycja, los;

        do {
            los = generator.nextInt(401) - 200;
            pozycja = this.xPos + los;

        } while ((pozycja < 0) || (pozycja > MAPSIZE - 30));

        this.xPos = pozycja;

        do {
            los = generator.nextInt(401) - 200;
            pozycja = this.yPos + los;
        } while ((pozycja < 0) || (pozycja > MAPSIZE - 30));

        this.yPos = pozycja;
    }

    public int getXPos() {
        return this.xPos;
    }

    public int getYPos() {
        return this.yPos;
    }
}
