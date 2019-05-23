package epidemia;

import java.util.HashMap;
import java.util.Random;

import static epidemia.Simulation.*;
import static java.lang.Math.abs;
import static java.lang.Math.floor;

public class Map implements IMap{

    private Random generator = new Random();
    public ISpecimen ArrSpecimen[] = new ISpecimen[POPULACJA];
    private HashMap<Integer,String> lista = new HashMap<Integer,String>();
    public int HospitalPos[]=new int[2];

    private void fillHashMap(){
        lista.clear();

        for (int k=0;k<POPULACJA;k++){
            lista.put(k,"zawartosc"+Integer.toString(k));
        }
    }

    public Map(){

        ArrSpecimen[0]=new Virus();

        double Dhuman = floor(POPULACJA*0.6);
        int human = (int)Dhuman;

        for (int i=1;i<=human;i++){
            ArrSpecimen[i]=new Human();
        }

        for (int j=human+1;j<POPULACJA;j++){
            ArrSpecimen[j]=new Animal();
        }

        int los = generator.nextInt(WIELKOSCMAPY-30);
        HospitalPos[0]=los;
        los = generator.nextInt(WIELKOSCMAPY-30);
        HospitalPos[1]=los;


        System.out.println("Created "+POPULACJA+" specimens: ");
        for (int k=0;k<POPULACJA;k++){
            System.out.println(k+". "+ ArrSpecimen[k].getType());
        }
        System.out.println("Generated Hospital at: ("+HospitalPos[0]+", "+HospitalPos[1]+")");
        System.out.println("");
        System.out.println(LICZNIK+": SIMULATION HAS STARTED");
    }

    public void turn(){
        fillHashMap();

        for (int i=0;i<POPULACJA;i++) {
            if (ArrSpecimen[i].checkAlive()) {

                ArrSpecimen[i].turn();

                if ((abs(ArrSpecimen[i].getXPos() - HospitalPos[0]) < 30) && (abs(ArrSpecimen[i].getYPos() - HospitalPos[1]) < 30)) {
                    ArrSpecimen[i].hospitalContact();
                }
            }
        }
        for (int j=0;j<POPULACJA;j++){

            if (ArrSpecimen[j].checkAlive()){
                String s = collisionDetection(j);
                if (s!=null){

                    int q = Integer.parseInt(s);

                    if ((ArrSpecimen[j].checkInfection()) || (ArrSpecimen[q].checkInfection())) {

                        int los = generator.nextInt(10)+1;
                        if ((ArrSpecimen[j].getImmunity()<=los) && (j!=0)) {
                            ArrSpecimen[j].infect();
                            System.out.println(LICZNIK+": Specimen "+j+" "+ArrSpecimen[j].getType()+" has been INFECTED by specimen "+q+" "+ArrSpecimen[q].getType()+" with efficiency "+los+", which was more than immunity "+ArrSpecimen[j].getImmunity());
                        }
                        else if(!ArrSpecimen[j].checkInfection()) System.out.println(LICZNIK+": Specimen "+j+" "+ArrSpecimen[j].getType()+" was IMMUNE at "+q+" "+ArrSpecimen[q].getType()+" contact. Efficiency "+los+" was less than immunity "+ArrSpecimen[j].getImmunity());


                        los = generator.nextInt(10)+1;
                        if ((ArrSpecimen[q].getImmunity()<=los) && (q!=0)) {
                            ArrSpecimen[q].infect();
                            System.out.println(LICZNIK+": Specimen "+q+" "+ArrSpecimen[q].getType()+" has been INFECTED by specimen "+j+" "+ArrSpecimen[j].getType()+" with efficiency "+los+", which was more than immunity "+ArrSpecimen[q].getImmunity());
                        }
                        else if(!ArrSpecimen[q].checkInfection()) System.out.println(LICZNIK+": Specimen "+q+" "+ArrSpecimen[q].getType()+" was IMMUNE at "+j+" "+ArrSpecimen[j].getType()+" contact. Efficiency "+los+" was less than immunity "+ArrSpecimen[q].getImmunity());
                    }
                }
            }
        }
    }

    private String collisionDetection(int i){

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

                if (((roznicaX<30)&&(roznicaY<30))&&(i!=j)){
                    System.out.println(LICZNIK+": Collision detected. Objects "+i+", "+j+" ("+x1+", "+y1+"); ("+x2+", "+y2+")");
                    zwrot = Integer.toString(j);
                }

            }

        return zwrot;
    }
    }

