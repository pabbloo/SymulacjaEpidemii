package epidemia;

public interface ISpecimen {
    public void move();
    public void generateStartingLocation();
    public int getXPos();
    public int getYPos();
    public void turn();
    public void infect();
    public boolean checkInfection();
    public boolean checkAlive();
    public int getImmunity();
}
