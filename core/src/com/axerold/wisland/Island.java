package com.axerold.wisland;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Island {
    private Region[][] wolfMap;
    private ArrayList<Animal> animalList = new ArrayList<>();
    private static int n;
    private static double birthChance, startPoints, hungerDeBuff, foodBuff;

    public Island(int size, double bc, double sp, double hd, double fb)
    {
        this.wolfMap = new Region[n][n];
        n = size;
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
                Hare hare = new Hare(i1,j1);
                wolfMap[i1][j1].add(hare);
                animalList.add(hare);
            }
        }
        for (int i = 0; i < amWolfs; i++) {
            i1 = getRandomInteger(0,n-1);
            j1 = getRandomInteger(0,n-1);
            if (wolfMap[i1][j1].isEmpty()){
                Wolf wolf = new Wolf(i1,j1,startPoints);
                wolfMap[i1][j1].add(wolf);
                animalList.add(wolf);
            }
        }
    }
    private void moveAn(Animal an, int fromX, int fromY)
    {
        int toX = an.getX();
        int toY = an.getY();
        if (fromX != toX || fromY != toY)
        {
            int index = wolfMap[fromY][fromX].find(an);
            wolfMap[fromY][fromX].remove(index);
            wolfMap[toY][toX].add(an);
        }
    }
    private void summonAn(Animal an)
    {
        if (an instanceof Hare){
            wolfMap[an.getY()][an.getX()].add(an);
        }
        if (an instanceof Wolf)
        {
            wolfMap[an.getY()][an.getX()].add(new Wolf(an.getX(),an.getY(),startPoints));
        }

    }

    private void zeroAn(Animal an)
    {
        int index = wolfMap[an.getY()][an.getX()].find(an);
        wolfMap[an.getY()][an.getX()].remove(index);
    }
    private void eatAn(Wolf w)
    {
        Hare h = new Hare(w.getX(),w.getY());
        int index = wolfMap[w.getY()][w.getX()].find(h);
        wolfMap[h.getY()][h.getX()].remove(index);
    }

    public void doCycle()
    {
        Animal an;
        int anX, anY;
        for (int i = 0; i < animalList.size(); i++) {
            an = animalList.get(i);
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
                an.doStep(this);
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
