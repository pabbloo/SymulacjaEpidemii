package epidemia;


import javax.swing.*;
import java.awt.*;

public class Simulation extends JPanel {
    static final int POPULACJA=6;
    static int LIMIT=1000;
    static int LICZNIK=0;
    static int WIELKOSCMAPY=1000;

    static int x0,y0,x1,y1,x2,y2,x3,y3,x4,y4,x5,y5,x6,y6;


    @Override
    public void paint(Graphics g) {

        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(Color.red);
        g2d.fillOval(x0, y0, 30, 30);
        g2d.setColor(Color.green);
        g2d.fillOval(x1, y1, 30, 30);
        g2d.setColor(Color.green);
        g2d.fillOval(x2, y2, 30, 30);
        g2d.setColor(Color.green);
        g2d.fillOval(x3, y3, 30, 30);
        g2d.setColor(Color.blue);
        g2d.fillOval(x4, y4, 30, 30);
        g2d.setColor(Color.blue);
        g2d.fillOval(x5, y5, 30, 30);

        g2d.setColor(Color.black);
        g2d.setFont(new Font("Arial", Font.PLAIN, 32));
        g2d.drawString("H",500,500);
    }

    public static void run() {
            Map plansza= new Map();

        Simulation sim=new Simulation();
        JFrame frame = new JFrame("Simulation");
        frame.add(sim);
        frame.setSize(1100, 1100);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        while (LICZNIK<LIMIT) {

            //glowna petla

            x0=plansza.ArrSpecimen[0].getXPos();
            y0=plansza.ArrSpecimen[0].getYPos();

            x1=plansza.ArrSpecimen[1].getXPos();
            y1=plansza.ArrSpecimen[1].getYPos();

            x2=plansza.ArrSpecimen[2].getXPos();
            y2=plansza.ArrSpecimen[2].getYPos();

            x3=plansza.ArrSpecimen[3].getXPos();
            y3=plansza.ArrSpecimen[3].getYPos();

            x4=plansza.ArrSpecimen[4].getXPos();
            y4=plansza.ArrSpecimen[4].getYPos();

            x5=plansza.ArrSpecimen[5].getXPos();
            y5=plansza.ArrSpecimen[5].getYPos();

            plansza.turn();
            frame.repaint();

            try {
                Thread.sleep(10);
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
