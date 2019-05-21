/*
TODO:
 -losowanie gatunkow osobinkow,
 -przyciski w gui.
 -prawdopodobienstwo zarazenia
 */


package epidemia;

import javax.swing.*;
import java.awt.*;

public class Simulation extends JPanel {
    static final int POPULACJA=6;
    static int LIMIT=100;
    static int LICZNIK=0;
    static int WIELKOSCMAPY=1000;

    static int Draw[][]=new int[POPULACJA][3];

    @Override
    public void paint(Graphics g) {

        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (int i=0;i<POPULACJA;i++){
            switch (Draw[i][2]){
                case 1:
                    g2d.setColor(Color.red);
                    break;
                case 2:
                    g2d.setColor(Color.blue);
                    break;
                case 3:
                    g2d.setColor(Color.green);
                    break;
                case 4:
                    g2d.setColor((new Color(35, 35, 74)));
                    break;
                case 5:
                    g2d.setColor((new Color(91, 116, 57)));
                    break;
                case 6:
                    g2d.setColor((new Color(0, 0, 0)));
                    break;
                case 7:
                    g2d.setColor((new Color(0, 0, 0)));
                    break;
                default:
                    g2d.setColor(Color.black);
            }
            g2d.fillOval(Draw[i][0], Draw[i][1], 30, 30);
        }


        g2d.setColor(Color.black);
        g2d.setFont(new Font("Arial", Font.PLAIN, 32));
        g2d.drawString(Integer.toString(LICZNIK),500,500);
    }

    public static void run() {
            Map plansza= new Map();

        Simulation sim=new Simulation();
        JFrame frame = new JFrame("Simulation");
        frame.add(sim);
        frame.setSize(WIELKOSCMAPY, WIELKOSCMAPY);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

            frame.repaint();


            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                break;
            }
            LICZNIK++;
        }
    }

    public static void main(String[] args) {

        Simulation.run();
    }
}
