package epidemia;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static epidemia.Simulation.*;

public class Frame implements ActionListener{

    JButton jok;
    Panel canvas;

    public Frame() {
        JFrame frame = new JFrame("Epidemia");
        frame.setSize(1300, 1080);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        jok = new JButton("Lala");
        jok.setBounds(1100,100,80,40);
        frame.add(jok);

        canvas = new Panel();
        frame.add(canvas);

    }

    @Override
    public void actionPerformed(ActionEvent e){

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
            g2d.drawRect(0,0,WIELKOSCMAPY-1,WIELKOSCMAPY-1);

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
            g2d.setFont(new Font("Arial", Font.PLAIN, 28));
            g2d.drawString("H",Draw[POPULACJA][0]+5,Draw[POPULACJA][1]+25);
            g2d.drawRect(Draw[POPULACJA][0],Draw[POPULACJA][1],30,30);

        }

    }
}
