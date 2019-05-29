package epidemia;

public interface ISpecimen {
    void move();
    void generateStartingLocation();
    int getXPos();
    int getYPos();
    void turn();
    void infect();
    boolean checkInfection();
    boolean checkAlive();
    int getImmunity();
    String getType();
    void hospitalContact();
}
