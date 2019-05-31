package epidemia;

import java.util.Random;

import static epidemia.Map.mapSize;

/**
 * Abstract class for specimen
 */
public abstract class ASpecimen implements ISpecimen {
    private int xPos, yPos;
    public boolean isAlive;
    public boolean isInfected;

    private Random generator = new Random();

    /**
     * Changes specimen's boolean isInfected to true
     */
    public void infect() {
        isInfected = true;
    }

    /**
     * @return boolean isInfected, which checks the current state of specimen
     */
    public boolean checkInfection() {
        return isInfected;
    }

    /**
     * @return boolean isAlive, which checks the current state of specimen
     */
    public boolean checkAlive() {
        return isAlive;
    }

    /**
     * Method which generates random position on map for current specimen
     */
    public void generateStartingLocation() {
        int los = generator.nextInt(mapSize);
        this.xPos = los;
        los = generator.nextInt(mapSize);
        this.yPos = los;
    }

    /**
     * Method responsible for moving the specimen. It changes the xPosition and yPosition of current specimen
     */
    public void move() {
        int pozycja, los;

        do {
            los = generator.nextInt(401) - 200;
            pozycja = this.xPos + los;

        } while ((pozycja < 0) || (pozycja > mapSize - 30));

        this.xPos = pozycja;

        do {
            los = generator.nextInt(401) - 200;
            pozycja = this.yPos + los;
        } while ((pozycja < 0) || (pozycja > mapSize - 30));

        this.yPos = pozycja;
    }

    /**
     * @return Integer XPosition of current specimen
     */
    public int getXPos() {
        return this.xPos;
    }

    /**
     * @return Integer YPosition of current specimen
     */
    public int getYPos() {
        return this.yPos;
    }
}
