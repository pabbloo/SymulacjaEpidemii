/*
TODO:
 -IRandomizable
 -testy jednostkowe
 -zapis logu do pilku
 */

package epidemia;

/**
 * Main class responsible for initializing simulation with the initial conditions
 */
public class Simulation {
    static int DURATION = 0;

    private Map map;

    public int[] stats;

    /**
     * Initialize the map, contains main loop where turns are coded
     *
     * @param frame choose the swing frame in which simulation should be displayed
     * @param Limit input from user, which defines maximum iteration limit
     * @param population input from user, how many specimens will program spawn on the map
     */
    public void run(Frame frame, int Limit, int population) {

        map = new Map(population);

        for (DURATION = 0; DURATION < Limit; DURATION++) {

            map.turn();
            frame.refresh(map);
            stats=results();
            frame.showStats();

            int dead=0;
            for (int i=0;i<population;i++){

                if(!map.ArrSpecimen[i].checkAlive()){
                    dead++;
                }
            }

            if(dead==population) break;

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                break;
            }

        }
        System.out.println(DURATION + ": SIMULATION HAS ENDED");
    }

    private int[] results() {

        int ani = 0, dani = 0, iani = 0, hum = 0, dhum = 0, ihum = 0;

        for (int i = 0; i < map.ArrSpecimen.length; i++) {
            if (map.ArrSpecimen[i].getType().equals("Human")) {
                if (map.ArrSpecimen[i].checkAlive()) {
                    if (map.ArrSpecimen[i].checkInfection()) ihum++;
                    else hum++;
                } else dhum++;
            } else if (map.ArrSpecimen[i].getType().equals("Animal")) {
                if (map.ArrSpecimen[i].checkAlive()) {
                    if (map.ArrSpecimen[i].checkInfection()) iani++;
                    else ani++;
                } else dani++;
            }
        }

        int[] array = new int[6];
        array[0] = hum;
        array[1] = ihum;
        array[2] = dhum;
        array[3] = ani;
        array[4] = iani;
        array[5] = dani;

        return array;
    }

    /**
     * Initialize main frame
     *
     * @param args default main param
     */
    public static void main(String[] args) {

        new Frame();

    }
}
