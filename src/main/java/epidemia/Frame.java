package epidemia;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static epidemia.Map.mapSize;
import static epidemia.Simulation.DURATION;
import static epidemia.Simulation.txt;

/**
 *Swing JFrame, providing a gui. Is used to enter starting conditions and start the simulation
 */
public class Frame extends JFrame implements ActionListener, WindowListener {
    private int POPULATION = 20;
    private int Limit = 100;
    private int Draw[][];
    private int iterator=0;
    private Simulation simulations[]=new Simulation[50];

    private JButton bstart;
    private JLabel lustawienia, lofiar, literacji, lpodsumowanie, lh, ldh, lih, la, lda, lia, lwh, lwdh, lwih, lwa, lwda, lwia,ltur,lwtur,lpodpis;
    private JTextField tosobnikow, titeracji;
    private Panel canvas;
    private BufferedImage human, dhuman, ihuman, animal, danimal, ianimal, virus, dvirus, hospital;
    private JFrame framee;


    /**
     * Creates JFrame with labels, buttons and text fields; Load images from resources
     */
    public Frame() {
        framee = new JFrame("Epidemia");

        framee.setLayout(null);
        framee.setSize(1300, 1080);
        framee.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        framee.addWindowListener(this);


        lustawienia = new JLabel("Ustawienia poczatkowe symulacji");
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

        lpodpis = new JLabel("by Pabbloo & Brzychwa");
        lpodpis.setBounds(1140, 960, 250, 50);
        lpodpis.setForeground(Color.GRAY);


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

        lda = new JLabel("Zmarlych zwierzat: ");
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

        ltur = new JLabel("Liczba wykonanych tur: ");
        ltur.setFont(new Font("Arial", Font.BOLD, 14));
        ltur.setBounds(1030, 700, 200, 20);
        ltur.setVisible(false);

        lwtur = new JLabel();
        lwtur.setFont(new Font("Arial", Font.BOLD, 14));
        lwtur.setBounds(1240, 700, 200, 20);
        lwtur.setVisible(false);

        framee.add(lustawienia);
        framee.add(lofiar);
        framee.add(tosobnikow);
        framee.add(literacji);
        framee.add(titeracji);
        framee.add(bstart);
        framee.add(lpodsumowanie);
        framee.add(lpodpis);

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

        framee.add(ltur);
        framee.add(lwtur);

        canvas = new Panel();
        framee.add(canvas);

        framee.setVisible(true);

        try {

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


    public void windowClosed(WindowEvent e) {}
    public void windowOpened(WindowEvent e) {}
    public void windowIconified(WindowEvent e) {}
    public void windowDeiconified(WindowEvent e) {}
    public void windowActivated(WindowEvent e) {}
    public void windowDeactivated(WindowEvent e) {}
    public void windowClosing(WindowEvent e)
    {
        exit();
    }

    /**
     * Method called when user clicks X to exit.
     * Prompts confrimation dialog befofe closing the window.
     * Also closes the results file.
     */
    private void exit()
    {
        int confirmed = JOptionPane.showConfirmDialog(framee,
                "Czy napewno chcesz wyjsc?", "Wyjscie", JOptionPane.YES_NO_OPTION);

        if (confirmed == JOptionPane.YES_OPTION)
        {
            txt.close();
            framee.dispose();
        }
    }

    /**
     * Method from actionListener, performs actions after clicking a button
     *
     * @param e performed action
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        bstart.setEnabled(false);
        String s = tosobnikow.getText();
        POPULATION = Integer.parseInt(s);
        String s2 = titeracji.getText();
        Limit = Integer.parseInt(s2);

        if (POPULATION<=1){
            JOptionPane.showMessageDialog(null,"Wartość musi być wieksza niż 1","Błąd", JOptionPane.ERROR_MESSAGE);
            bstart.setEnabled(true);
        }
        else if(Limit <=0){
            JOptionPane.showMessageDialog(null,"Wartość musi być wieksza niż 0","Błąd", JOptionPane.ERROR_MESSAGE);
            bstart.setEnabled(true);
        }
        else {
            Draw = new int[POPULATION + 1][3];

            txt.println("===============================================");
            txt.println("Initializng simlation "+iterator);
            txt.println("===============================================");
            simulations[iterator] = new Simulation();


            Frame okienko = this;

            new Thread(() -> {
                simulations[iterator].run(okienko, Limit, POPULATION);

                showStats();

                bstart.setEnabled(true);
                iterator++;
            }).start();

        }
    }

    /**
     * Method used to show live statistics
     */
    public void showStats(){
        int[] summary = simulations[iterator].stats;
        int ani, dani, iani, hum, dhum, ihum;
        hum = summary[0];
        ihum = summary[1];
        dhum = summary[2];
        ani = summary[3];
        iani = summary[4];
        dani = summary[5];


        lwh.setText(String.valueOf(hum));
        lwih.setText(String.valueOf(ihum));
        lwdh.setText(String.valueOf(dhum));
        lwa.setText(String.valueOf(ani));
        lwia.setText(String.valueOf(iani));
        lwda.setText(String.valueOf(dani));
        lwtur.setText(String.valueOf(DURATION));

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

        ltur.setVisible(true);
        lwtur.setVisible(true);

    }

    /**
     * Method used to refresh specimens positions on map
     *
     * @param map which map should be used to refresh the Frame
     */
    public void refresh(Map map) {
        for (int i = 0; i < POPULATION; i++) {
            this.Draw[i][0] = map.ArrSpecimen[i].getXPos();
            this.Draw[i][1] = map.ArrSpecimen[i].getYPos();

            if (map.ArrSpecimen[i] instanceof Virus) {
                if (!map.ArrSpecimen[i].checkAlive()) this.Draw[i][2] = 8;
                else this.Draw[i][2] = 1;
            } else if (map.ArrSpecimen[i] instanceof Human) {

                if (!map.ArrSpecimen[i].checkAlive()) this.Draw[i][2] = 6;
                else if (map.ArrSpecimen[i].checkInfection()) this.Draw[i][2] = 4;
                else this.Draw[i][2] = 2;
            } else if (map.ArrSpecimen[i] instanceof Animal) {
                if (!map.ArrSpecimen[i].checkAlive()) this.Draw[i][2] = 7;
                else if (map.ArrSpecimen[i].checkInfection()) this.Draw[i][2] = 5;
                else this.Draw[i][2] = 3;
            }
        }
        this.Draw[POPULATION][0] = map.HospitalPos[0];
        this.Draw[POPULATION][1] = map.HospitalPos[1];

        this.canvas.repaint();


    }

    /**
     * Swing JPanel, used to paint objects
     */
    public class Panel extends JPanel {

        /**
         * Constructor which sets the size to mapSize
         */
        private Panel() {
            setBounds(5, 5, mapSize, mapSize);
            setBackground(Color.white);
        }

        /**
         * Method from Swing used to paint the objects on Panel
         *
         * @param g magic Parameter
         */
        @Override
        public void paint(Graphics g) {

            super.paint(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(Color.black);
            g2d.drawRect(0, 0, mapSize - 1, mapSize - 1);

            if (simulations[iterator] != null) {
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
