/*
TODO:
 -IRandomizable
 -przyciski w gui.
 -testy jednostkowe
 -automatyczne budowanie
 -komentarze w kodzie - javadoc?

 KNOWN BUGS:
 -jak wszyscy zgina to sie zawiesza
 -jak dwa sie zarazaja to leci log 5->2, 2->5

 */


package epidemia;

import javax.swing.*;
import java.awt.*;

public class Simulation extends JPanel {
    static final int POPULACJA=20;
    static final int WIELKOSCMAPY=1000;
    static int LICZNIK=0;

    private final int LIMIT=100;

    public void run() {
        Frame frame = new Frame();
        Map plansza= new Map();

        while (LICZNIK<LIMIT) {
            //glowna petla
            plansza.turn();

            for(int i=0;i<POPULACJA;i++){
                frame.Draw[i][0]=plansza.ArrSpecimen[i].getXPos();
                frame.Draw[i][1]=plansza.ArrSpecimen[i].getYPos();

                if (plansza.ArrSpecimen[i] instanceof Virus) frame.Draw[i][2]=1;
                else if (plansza.ArrSpecimen[i] instanceof Human) {

                    if (!plansza.ArrSpecimen[i].checkAlive()) frame.Draw[i][2] = 6;
                    else if (plansza.ArrSpecimen[i].checkInfection()) frame.Draw[i][2] = 4;
                    else frame.Draw[i][2] = 2;
                }
                else if (plansza.ArrSpecimen[i] instanceof Animal) {
                    if (!plansza.ArrSpecimen[i].checkAlive()) frame.Draw[i][2] = 7;
                    else if (plansza.ArrSpecimen[i].checkInfection()) frame.Draw[i][2] = 5;
                    else frame.Draw[i][2] = 3;
                }
            }
            frame.Draw[POPULACJA][0]=plansza.HospitalPos[0];
            frame.Draw[POPULACJA][1]=plansza.HospitalPos[1];

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
