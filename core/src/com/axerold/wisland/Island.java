package com.axerold.wisland;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Island {
    private Region[][] wolfMap;
    private ArrayList<Animal> animalList = new ArrayList<>();
    private static int n;
    private static double birthChance, startPoints, hungerDeBuff, foodBuff;
    private ArrayIndexOutOfBoundsException indexBelowZero;

    public Island(int size, double bc, double sp, double hd, double fb)
    {
        n = size;
        this.wolfMap = new Region[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                wolfMap[i][j] = new Region(new ArrayList<Animal>(),j,i);
            }
        }
        birthChance = bc;
        startPoints = sp;
        hungerDeBuff = hd;
        foodBuff = fb;
    }
    public static int getRandomInteger(double min, double max){
        return (int) ((int)(Math.random()*((max-min)+1))+min);
    }

    public static double getRandomDouble(double min, double max){
        return (Math.random()*((max-min)+1))+min;
    }

    public void randomFill(int amWolfs, int amHares)
    {
        int i1, j1;
        for (int i = 0; i < amHares; i++) {
            i1 = getRandomInteger(0,n-1);
            j1 = getRandomInteger(0,n-1);
            if (wolfMap[i1][j1].isEmpty()){
                Hare hare = new Hare(j1,i1);
                wolfMap[i1][j1].add(hare);
                animalList.add(hare);
            }
            else{
                i--;
            }
        }
        for (int i = 0; i < amWolfs; i++) {
            i1 = getRandomInteger(0,n-1);
            j1 = getRandomInteger(0,n-1);
            if (wolfMap[i1][j1].isEmpty()){
                Wolf wolf = new Wolf(j1,i1,startPoints);
                wolfMap[i1][j1].add(wolf);
                animalList.add(wolf);
            }
            else{
                i--;
            }

        }
    }

    /*public int fieldIndex(Animal an, int i1, int j1)
    {
        Region r = wolfMap[i1][j1];
        for (int i = 0; i < r.size(); i++) {
            if (r.get(i) != null)
            {
                if (r.get(i).equals(an))
                {
                    return i;
                }
            }
        }
        return -1;
    }*/

    private void moveAn(Animal an, int fromX, int fromY)
    {
        int toX = an.getX();
        int toY = an.getY();
        if (fromX != toX || fromY != toY)
        {
            wolfMap[toY][toX].add(an);
            wolfMap[fromY][fromX].remove(an);
        }
    }
    private void summonAn(Animal an)
    {
        if (an instanceof Hare){
            Hare hare = new Hare(an.getX(), an.getY());
            wolfMap[an.getY()][an.getX()].add(hare);
            animalList.add(hare);
        }
        if (an instanceof Wolf)
        {
            Wolf wolf = new Wolf(an.getX(),an.getY(),startPoints);
            wolfMap[an.getY()][an.getX()].add(wolf);
            animalList.add(wolf);
        }

    }

    private void zeroAn(Animal an)
    {
        //int index = wolfMap[an.getY()][an.getX()].find(an);
        //int index = fieldIndex(an,an.getY(),an.getX());
        wolfMap[an.getY()][an.getX()].remove(an);
        animalList.remove(an);
    }
    private void eatAn(Wolf w)
    {
        //int index = wolfMap[w.getY()][w.getX()].find(h);
        /*int index = fieldIndex(h,w.getY(),w.getX());
        if (index != -1)*/
        Region r = wolfMap[w.getY()][w.getX()];
        for (int i = 0; i < r.size(); i++) {
            if (r.get(i) instanceof Hare)
            {
                animalList.remove(r.get(i));
                r.remove(r.get(i));
                return;
            }
        }
           // wolfMap[h.getY()][h.getX()].remove(index);
    }

    public void doCycle()
    {
        Animal an;
        int anX, anY;
        for (int i = 0; i < animalList.size(); i++) {
            an = animalList.get(i);
            if (an.newborn){
                an.growUp();
                continue;
            }
            anX = an.getX();
            anY = an.getY();
            if (an instanceof Hare)
            {
                an.doStep(this);
                moveAn(an,anX,anY);
                if (an.doBreed(birthChance))
                {
                    summonAn(an);
                }
            }
            if (an instanceof Wolf)
            {
                an.doStep(this); //another order???
                moveAn(an,anX,anY);
                if (an.doEat(this))
                {
                    eatAn((Wolf) an);
                }
                if (((Wolf) an).getPoints() <= 0.0)
                {
                    zeroAn(an);
                }
                if (an.doBreed(birthChance))
                {
                    summonAn(an);
                }
            }
        }
    }
    public int getN(){
        return n;
    }

    //public int getM() {return 0;}

    public double getBirthChance() {
        return birthChance;
    }

    public double getStartPoints() {
        return startPoints;
    }

    public double getFoodBuff() {
        return foodBuff;
    }

    public double getHungerDeBuff() {
        return hungerDeBuff;
    }

    public Region getRegion(int i1, int j1)
    {
        return this.wolfMap[i1][j1];
    }

}
