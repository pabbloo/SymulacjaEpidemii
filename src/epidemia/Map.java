package epidemia;

import static epidemia.Simulation.POPULACJA;

public class Map implements IMap{
    public ISpecimen ArrSpecimen[] = new ISpecimen[POPULACJA];

    public int ArrPos[][] = new int[POPULACJA][2];


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
        for (int i=0;i<POPULACJA;i++){
            ArrSpecimen[i].move();
        }
    }

    public void collisionDetection(){

    }
    }

