/*
TODO:
 -IRandomizable
 -testy jednostkowe
 -automatyczne budowanie
 -komentarze w kodzie - javadoc?
 -wyswietlanie podsumowania

 KNOWN BUGS:
 -jak wszyscy zgina to sie zawiesza
 -jak dwa sie zarazaja to leci log 5->2, 2->5
 -tylko raz mozna kliknac start

 */


package epidemia;


public class Simulation{
    static int LICZNIK=0;

    public void run(Frame frame,int Limit,int populacja) {

            Map plansza = new Map(populacja);

            for (LICZNIK=0;LICZNIK<Limit;LICZNIK++) {

                plansza.turn();
                frame.repaint(plansza);


                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    break;
                }

            }
            System.out.println(LICZNIK + ": SIMULATION HAS ENDED");

    }

    public static void main(String[] args) {
        new Frame();

    }
}
