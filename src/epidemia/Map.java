package epidemia;

import java.util.HashMap;

import static epidemia.Simulation.*;
import static java.lang.Math.abs;

public class Map implements IMap{
    public ISpecimen ArrSpecimen[] = new ISpecimen[POPULACJA];
    HashMap<Integer,String> lista = new HashMap<Integer,String>();

    private void fillHashMap(){
        lista.clear();

        for (int k=0;k<POPULACJA;k++){
            lista.put(k,"zawartosc"+Integer.toString(k));
        }
    }

    public Map(){
        //hardcoded na testy
        ArrSpecimen[0]=new Virus();
        ArrSpecimen[1]=new Animal();
        ArrSpecimen[2]=new Animal();
        ArrSpecimen[3]=new Animal();
        ArrSpecimen[4]=new Human();
        ArrSpecimen[5]=new Human();
    }

    public void turn(){
        fillHashMap();

        for (int i=0;i<POPULACJA;i++) {
            ArrSpecimen[i].turn();
            }
        for (int j=0;j<POPULACJA;j++){

            if (ArrSpecimen[j].checkAlive()){
                String s = collisionDetection(j);
                if (s!=null){

                    int q = Integer.parseInt(s);

                    if ((ArrSpecimen[j].checkInfection()) || (ArrSpecimen[q].checkInfection())) {
                        ArrSpecimen[j].infect();
                        ArrSpecimen[q].infect();
                    }
                }
            }
        }
    }

    public String collisionDetection(int i){

            String zwrot=null;

            lista.remove(i);
            for(int j : lista.keySet()){

                int x1,x2,y1,y2,roznicaX,roznicaY;

                x1=ArrSpecimen[i].getXPos();
                y1=ArrSpecimen[i].getYPos();
                x2=ArrSpecimen[j].getXPos();
                y2=ArrSpecimen[j].getYPos();

                roznicaX=abs(x1-x2);
                roznicaY=abs(y1-y2);

                //testing element
                if (((roznicaX<30)&&(roznicaY<30))&&(i!=j)){
                    /*
                    System.out.println("Collision detected. Objects "+i+", "+j+" at turn "+LICZNIK);
                    System.out.println("X1: "+x1+" Y1: "+y1);
                    System.out.println("Y2: "+x2+" Y2: "+y2);
                    System.out.println("rX: "+roznicaX+" rY: "+roznicaY);
                    */
                    zwrot = Integer.toString(j);

                }

            }

        return zwrot;
    }
    }

