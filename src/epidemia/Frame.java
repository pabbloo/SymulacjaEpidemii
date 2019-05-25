package epidemia;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static epidemia.Map.MAPSIZE;
import static epidemia.Simulation.DURATION;

public class Frame extends JPanel implements ActionListener {
    private int POPULATION = 20;
    private int Limit = 100;
    private int Draw[][];

    private JButton bstart;
    private JLabel lustawienia, lofiar, literacji, lpodsumowanie, lh, ldh, lih, la, lda, lia, lwh, lwdh, lwih, lwa, lwda, lwia;
    private JTextField tosobnikow, titeracji;
    private Panel canvas;
    private BufferedImage human, dhuman, ihuman, animal, danimal, ianimal, virus, dvirus, hospital;


    public Frame() {
        JFrame framee = new JFrame("Epidemia");

        framee.setLayout(null);
        framee.setSize(1300, 1080);
        framee.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        lustawienia = new JLabel("Ustawienia początkowe symulacji");
        lustawienia.setFont(new Font("Arial", Font.BOLD, 16));
        lustawienia.setBounds(1020, 50, 300, 20);


        lofiar = new JLabel("Ilosc osobnikow: ");
        lofiar.setFont(new Font("Arial", Font.BOLD, 14));
        lofiar.setBounds(1030, 100, 140, 20);


        tosobnikow = new JTextField(String.valueOf(POPULATION));
        tosobnikow.setFont(new Font("Arial", Font.BOLD, 14));
        tosobnikow.setBounds(1180, 100, 50, 20);


        literacji = new JLabel("Ilosc iteracji: ");
        literacji.setFont(new Font("Arial", Font.BOLD, 14));
        literacji.setBounds(1030, 150, 140, 20);


        titeracji = new JTextField(String.valueOf(Limit));
        titeracji.setFont(new Font("Arial", Font.BOLD, 14));
        titeracji.setBounds(1180, 150, 50, 20);


        bstart = new JButton("START");
        bstart.setBounds(1030, 200, 200, 50);
        bstart.addActionListener(this);

        lpodsumowanie = new JLabel("Wyniki symulacji: ");
        lpodsumowanie.setFont(new Font("Arial", Font.BOLD, 14));
        lpodsumowanie.setBounds(1030, 500, 200, 20);
        lpodsumowanie.setVisible(false);

        lh = new JLabel("Zywych zdrowych ludzi: ");
        lh.setBounds(1030, 520, 200, 20);
        lh.setVisible(false);

        lih = new JLabel("Zarazonych zywych ludzi: ");
        lih.setBounds(1030, 540, 200, 20);
        lih.setVisible(false);

        ldh = new JLabel("Zmarlych ludzi: ");
        ldh.setBounds(1030, 560, 200, 20);
        ldh.setVisible(false);

        la = new JLabel("Zywych zdrowych zwierzat: ");
        la.setBounds(1030, 600, 200, 20);
        la.setVisible(false);

        lia = new JLabel("Zarazonych zywych zwierzat: ");
        lia.setBounds(1030, 620, 200, 20);
        lia.setVisible(false);

        lda = new JLabel("Zmarlych zwirzat: ");
        lda.setBounds(1030, 640, 200, 20);
        lda.setVisible(false);

        lwh = new JLabel("err");
        lwh.setBounds(1250, 520, 50, 20);
        lwh.setVisible(false);

        lwih = new JLabel("err");
        lwih.setBounds(1250, 540, 50, 20);
        lwih.setVisible(false);

        lwdh = new JLabel("err");
        lwdh.setBounds(1250, 560, 50, 20);
        lwdh.setVisible(false);

        lwa = new JLabel("err");
        lwa.setBounds(1250, 600, 50, 20);
        lwa.setVisible(false);

        lwia = new JLabel("err");
        lwia.setBounds(1250, 620, 50, 20);
        lwia.setVisible(false);

        lwda = new JLabel("err");
        lwda.setBounds(1250, 640, 50, 20);
        lwda.setVisible(false);


        framee.add(lustawienia);
        framee.add(lofiar);
        framee.add(tosobnikow);
        framee.add(literacji);
        framee.add(titeracji);
        framee.add(bstart);
        framee.add(lpodsumowanie);

        framee.add(lh);
        framee.add(lih);
        framee.add(ldh);
        framee.add(la);
        framee.add(lia);
        framee.add(lda);

        framee.add(lwh);
        framee.add(lwih);
        framee.add(lwdh);
        framee.add(lwa);
        framee.add(lwia);
        framee.add(lwda);


        canvas = new Panel();
        framee.add(canvas);

        //frame.pack();
        framee.setVisible(true);

        try {
            /*
            human = ImageIO.read(new File("src/img/human.png"));
            dhuman = ImageIO.read(new File("src/img/dhuman.png"));
            ihuman = ImageIO.read(new File("src/img/ihuman.png"));
            animal = ImageIO.read(new File("src/img/animal.png"));
            danimal = ImageIO.read(new File("src/img/danimal.png"));
            ianimal = ImageIO.read(new File("src/img/ianimal.png"));
            virus = ImageIO.read(new File("src/img/virus.png"));
            dvirus = ImageIO.read(new File("src/img/dvirus.png"));
            hospital = ImageIO.read(new File("src/img/hospital.png"));
            */


            human = ImageIO.read(epidemia.Frame.class.getResourceAsStream("/img/human.png"));
            dhuman = ImageIO.read(epidemia.Frame.class.getResourceAsStream("/img/dhuman.png"));
            ihuman = ImageIO.read(epidemia.Frame.class.getResourceAsStream("/img/ihuman.png"));
            animal = ImageIO.read(epidemia.Frame.class.getResourceAsStream("/img/animal.png"));
            danimal = ImageIO.read(epidemia.Frame.class.getResourceAsStream("/img/danimal.png"));
            ianimal = ImageIO.read(epidemia.Frame.class.getResourceAsStream("/img/ianimal.png"));
            virus = ImageIO.read(epidemia.Frame.class.getResourceAsStream("/img/virus.png"));
            dvirus = ImageIO.read(epidemia.Frame.class.getResourceAsStream("/img/dvirus.png"));
            hospital = ImageIO.read(epidemia.Frame.class.getResourceAsStream("/img/hospital.png"));



        } catch (IOException e) {
            System.out.println("Cant load graphics");
        }

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        bstart.setEnabled(false);
        String s = tosobnikow.getText();
        POPULATION = Integer.parseInt(s);
        String s2 = titeracji.getText();
        Limit = Integer.parseInt(s2);

        Draw = new int[POPULATION + 1][3];
        Simulation simulation = new Simulation();
        Frame okienko = this;

        new Thread() {
            public void run() {
                simulation.run(okienko, Limit, POPULATION);

                int[] podsumowanie = simulation.results();
                int ani = 0, dani = 0, iani = 0, hum = 0, dhum = 0, ihum = 0;
                hum = podsumowanie[0];
                dhum = podsumowanie[1];
                ihum = podsumowanie[2];
                ani = podsumowanie[3];
                dani = podsumowanie[4];
                iani = podsumowanie[5];


                lwh.setText(String.valueOf(hum));
                lwih.setText(String.valueOf(ihum));
                lwdh.setText(String.valueOf(dhum));
                lwa.setText(String.valueOf(ani));
                lwia.setText(String.valueOf(iani));
                lwda.setText(String.valueOf(dani));

                lpodsumowanie.setVisible(true);

                lh.setVisible(true);
                lih.setVisible(true);
                ldh.setVisible(true);
                la.setVisible(true);
                lia.setVisible(true);
                lda.setVisible(true);

                lwh.setVisible(true);
                lwih.setVisible(true);
                lwdh.setVisible(true);
                lwa.setVisible(true);
                lwia.setVisible(true);
                lwda.setVisible(true);

                bstart.setEnabled(true);
            }
        }.start();

    }

    public void repaint(Map plansza) {
        for (int i = 0; i < POPULATION; i++) {
            this.Draw[i][0] = plansza.ArrSpecimen[i].getXPos();
            this.Draw[i][1] = plansza.ArrSpecimen[i].getYPos();

            if (plansza.ArrSpecimen[i] instanceof Virus) {
                if (!plansza.ArrSpecimen[i].checkAlive()) this.Draw[i][2] = 8;
                else this.Draw[i][2] = 1;
            } else if (plansza.ArrSpecimen[i] instanceof Human) {

                if (!plansza.ArrSpecimen[i].checkAlive()) this.Draw[i][2] = 6;
                else if (plansza.ArrSpecimen[i].checkInfection()) this.Draw[i][2] = 4;
                else this.Draw[i][2] = 2;
            } else if (plansza.ArrSpecimen[i] instanceof Animal) {
                if (!plansza.ArrSpecimen[i].checkAlive()) this.Draw[i][2] = 7;
                else if (plansza.ArrSpecimen[i].checkInfection()) this.Draw[i][2] = 5;
                else this.Draw[i][2] = 3;
            }
        }
        this.Draw[POPULATION][0] = plansza.HospitalPos[0];
        this.Draw[POPULATION][1] = plansza.HospitalPos[1];

        this.canvas.repaint();


    }

    public class Panel extends JPanel {

        private Panel() {
            setBounds(5, 5, MAPSIZE, MAPSIZE);
            setBackground(Color.white);
        }

        @Override
        public void paint(Graphics g) {

            super.paint(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(Color.black);
            g2d.drawRect(0, 0, MAPSIZE - 1, MAPSIZE - 1);

            if (DURATION != 0) {
                for (int i = 0; i < POPULATION; i++) {
                    switch (Draw[i][2]) {
                        case 1:
                            g2d.drawImage(virus, Draw[i][0], Draw[i][1], null);
                            break;
                        case 2:
                            g2d.drawImage(human, Draw[i][0], Draw[i][1], null);
                            break;
                        case 3:
                            g2d.drawImage(animal, Draw[i][0], Draw[i][1], null);
                            break;
                        case 4:
                            g2d.drawImage(ihuman, Draw[i][0], Draw[i][1], null);
                            break;
                        case 5:
                            g2d.drawImage(ianimal, Draw[i][0], Draw[i][1], null);
                            break;
                        case 6:
                            g2d.drawImage(dhuman, Draw[i][0], Draw[i][1], null);
                            break;
                        case 7:
                            g2d.drawImage(danimal, Draw[i][0], Draw[i][1], null);
                            break;
                        case 8:
                            g2d.drawImage(dvirus, Draw[i][0], Draw[i][1], null);
                            break;
                        default:
                            g2d.setColor(Color.black);
                    }
                }
                g2d.drawImage(hospital, Draw[POPULATION][0], Draw[POPULATION][1], null);
            }
        }
    }
}
