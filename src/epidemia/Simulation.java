/*
TODO:
 -IRandomizable
 -testy jednostkowe
 -automatyczne budowanie
 -komentarze w kodzie - javadoc?
 -wyswietlanie podsumowania
 -zapis logu do pilku

 KNOWN BUGS:
 -jak wszyscy zgina to sie zawiesza
 -jak dwa sie zarazaja to leci log 5->2, 2->5
 -chyba sie buguje jak sie uruchomi przyciskiem symulacje pare razy

 */


package epidemia;


public class Simulation{
    static int LICZNIK=0;
    Map plansza;

    public void run(Frame frame,int Limit,int populacja) {

            plansza = new Map(populacja);

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

    public int[] results(){

        int ani=0,dani=0,iani=0,hum=0,dhum=0,ihum=0;

        for (int i=0;i<plansza.ArrSpecimen.length;i++){
            if (plansza.ArrSpecimen[i].getType().equals("Human")){
                if (plansza.ArrSpecimen[i].checkAlive()){
                    if (plansza.ArrSpecimen[i].checkInfection()) ihum++;
                    else hum++;
                }
                else dhum++;
            }
            else if (plansza.ArrSpecimen[i].getType().equals("Animal")){
                if (plansza.ArrSpecimen[i].checkAlive()){
                    if (plansza.ArrSpecimen[i].checkInfection()) iani++;
                    else ani++;
                }
                else dani++;
            }
        }

        int[] zwrot = new int[6];
        zwrot[0] = hum;
        zwrot[1] = ihum;
        zwrot[2] = dhum;
        zwrot[3] = ani;
        zwrot[4] = iani;
        zwrot[5] = dani;

        return zwrot;
    }

    public static void main(String[] args) {

        new Frame();

    }
}
