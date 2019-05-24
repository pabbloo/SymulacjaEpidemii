package epidemia;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static epidemia.Map.WIELKOSCMAPY;
import static epidemia.Simulation.LICZNIK;

public class Frame extends JPanel implements ActionListener{
    private int POPULACJA=20;
    private int Limit=100;
    private int Draw[][];
    JButton jstart;
    JLabel lustawienia,lofiar,literacji;
    JTextField tosobnikow,titeracji;
    Panel canvas;


    public Frame() {
        JFrame framee = new JFrame("Epidemia");

        framee.setLayout(null);
        framee.setSize(1300, 1080);
        framee.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        lustawienia = new JLabel("Ustawienia początkowe symulacji");
        lustawienia.setFont(new Font("Arial", Font.BOLD, 16));
        lustawienia.setBounds(1020,50,300,20);


        lofiar = new JLabel ("Ilosc osobnikow: ");
        lofiar.setFont(new Font("Arial", Font.BOLD, 14));
        lofiar.setBounds(1030,100,140,20);


        tosobnikow = new JTextField(String.valueOf(POPULACJA));
        tosobnikow.setFont(new Font("Arial", Font.BOLD, 14));
        tosobnikow.setBounds(1180,100,50,20);


        literacji = new JLabel ("Ilosc iteracji: ");
        literacji.setFont(new Font("Arial", Font.BOLD, 14));
        literacji.setBounds(1030,150,140,20);


        titeracji = new JTextField(String.valueOf(Limit));
        titeracji.setFont(new Font("Arial", Font.BOLD, 14));
        titeracji.setBounds(1180,150,50,20);


        jstart = new JButton("START");
        jstart.setBounds(1030,200,200,50);
        jstart.addActionListener(this);

        framee.add(lustawienia);
        framee.add(lofiar);
        framee.add(tosobnikow);
        framee.add(literacji);
        framee.add(titeracji);
        framee.add(jstart);

        canvas = new Panel();
        framee.add(canvas);

        //frame.pack();
        framee.setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e){
        String s = tosobnikow.getText();
        POPULACJA=Integer.parseInt(s);
        String s2=titeracji.getText();
        Limit = Integer.parseInt(s2);

        Draw = new int[POPULACJA+1][3];
        Simulation simulation = new Simulation();
        Frame okienko=this;

        new Thread() {
            public void run() {
                simulation.run(okienko,Limit,POPULACJA);
            }
        }.start();

        //podsumowanie visible
    }

    public void repaint(Map plansza){
        for(int i=0;i<POPULACJA;i++){
            this.Draw[i][0]=plansza.ArrSpecimen[i].getXPos();
            this.Draw[i][1]=plansza.ArrSpecimen[i].getYPos();

            if (plansza.ArrSpecimen[i] instanceof Virus) this.Draw[i][2]=1;
            else if (plansza.ArrSpecimen[i] instanceof Human) {

                if (!plansza.ArrSpecimen[i].checkAlive()) this.Draw[i][2] = 6;
                else if (plansza.ArrSpecimen[i].checkInfection()) this.Draw[i][2] = 4;
                else this.Draw[i][2] = 2;
            }
            else if (plansza.ArrSpecimen[i] instanceof Animal) {
                if (!plansza.ArrSpecimen[i].checkAlive()) this.Draw[i][2] = 7;
                else if (plansza.ArrSpecimen[i].checkInfection()) this.Draw[i][2] = 5;
                else this.Draw[i][2] = 3;
            }
        }
        this.Draw[POPULACJA][0]=plansza.HospitalPos[0];
        this.Draw[POPULACJA][1]=plansza.HospitalPos[1];

        this.canvas.repaint();


    }

    public class Panel extends JPanel{

        private Panel(){
            setBounds(5,5,WIELKOSCMAPY,WIELKOSCMAPY);
            setBackground(Color.white);
        }

        @Override
        public void paint(Graphics g) {

            super.paint(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2d.setColor(Color.black);
            g2d.drawRect(0, 0, WIELKOSCMAPY - 1, WIELKOSCMAPY - 1);

            if (LICZNIK != 0) {
                for (int i = 0; i < POPULACJA; i++) {
                    switch (Draw[i][2]) {
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
                g2d.setFont(new Font("Arial", Font.PLAIN, 28));
                g2d.drawString("H", Draw[POPULACJA][0] + 5, Draw[POPULACJA][1] + 25);
                g2d.drawRect(Draw[POPULACJA][0], Draw[POPULACJA][1], 30, 30);
            }
        }
    }
}
