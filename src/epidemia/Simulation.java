/*
TODO:
 -IRandomizable
 -IMap
 -testy jednostkowe
 -automatyczne budowanie
 -komentarze w kodzie - javadoc?
 -zapis logu do pilku
 -lepszy ruch. Moze na podstawie przesuniecia o wektor.
 -collisiondetection przerobic na int


 */


package epidemia;


public class Simulation {
    static int DURATION = 0;

    private Map plansza;

    public int[] stats;

    public void run(Frame frame, int Limit, int populacja) {

        plansza = new Map(populacja);

        for (DURATION = 0; DURATION < Limit; DURATION++) {

            plansza.turn();
            frame.refresh(plansza);
            stats=results();
            frame.showStats();

            int dead=0;
            for (int i=0;i<populacja;i++){

                if(!plansza.ArrSpecimen[i].checkAlive()){
                    dead++;
                }
            }

            if(dead==populacja) break;

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                break;
            }

        }
        System.out.println(DURATION + ": SIMULATION HAS ENDED");
    }

    private int[] results() {

        int ani = 0, dani = 0, iani = 0, hum = 0, dhum = 0, ihum = 0;

        for (int i = 0; i < plansza.ArrSpecimen.length; i++) {
            if (plansza.ArrSpecimen[i].getType().equals("Human")) {
                if (plansza.ArrSpecimen[i].checkAlive()) {
                    if (plansza.ArrSpecimen[i].checkInfection()) ihum++;
                    else hum++;
                } else dhum++;
            } else if (plansza.ArrSpecimen[i].getType().equals("Animal")) {
                if (plansza.ArrSpecimen[i].checkAlive()) {
                    if (plansza.ArrSpecimen[i].checkInfection()) iani++;
                    else ani++;
                } else dani++;
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
