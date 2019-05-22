/*
TODO:
 -IRandomizable
 -przyciski w gui.
 */


package epidemia;

import javax.swing.*;
import java.awt.*;

public class Simulation extends JPanel {
    static final int POPULACJA=20;
    static final int WIELKOSCMAPY=1000;
    static int LICZNIK=0;
    static int Draw[][]=new int[POPULACJA+1][3];

    private final int LIMIT=100;

    public void run() {
        Frame frame = new Frame();
        Map plansza= new Map();

        while (LICZNIK<LIMIT) {
            //glowna petla
            plansza.turn();

            for(int i=0;i<POPULACJA;i++){
                Draw[i][0]=plansza.ArrSpecimen[i].getXPos();
                Draw[i][1]=plansza.ArrSpecimen[i].getYPos();

                if (plansza.ArrSpecimen[i] instanceof Virus) Draw[i][2]=1;
                else if (plansza.ArrSpecimen[i] instanceof Human) {

                    if (!plansza.ArrSpecimen[i].checkAlive()) Draw[i][2] = 6;
                    else if (plansza.ArrSpecimen[i].checkInfection()) Draw[i][2] = 4;
                    else Draw[i][2] = 2;
                }
                else if (plansza.ArrSpecimen[i] instanceof Animal) {
                    if (!plansza.ArrSpecimen[i].checkAlive()) Draw[i][2] = 7;
                    else if (plansza.ArrSpecimen[i].checkInfection()) Draw[i][2] = 5;
                    else Draw[i][2] = 3;
                }
            }
            Draw[POPULACJA][0]=plansza.HospitalPos[0];
            Draw[POPULACJA][1]=plansza.HospitalPos[1];

            frame.canvas.repaint();

            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                break;
            }
            LICZNIK++;
        }
        System.out.println(LICZNIK+": SIMULATION HAS ENDED");
    }

    public static void main(String[] args) {

        Simulation simulation = new Simulation();
        simulation.run();
    }
}
