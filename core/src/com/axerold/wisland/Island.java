package com.axerold.wisland;


import java.util.ArrayList;

public class Island {
    private final Region[][] wolfMap;
    private final ArrayList<Animal> animalList = new ArrayList<>();
    private static int n;
    private static double startPoints, hungerDeBuff, foodBuff;
    private static double birthChanceWolf, birthChanceHare/* ,breedChanceWolf, breedChanceHare*/;
    private static boolean hareAI;
    private static final Sex M = Sex.Male, F = Sex.Female;

    public Island(int size, double bicw, double bich, double bcw, double bch, double sp, double hd, double fb)
    {
        n = size;
        this.wolfMap = new Region[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                wolfMap[i][j] = new Region(new ArrayList<Animal>(),j,i);
            }
        }
        birthChanceWolf = bicw;
        birthChanceHare = bich;
        /*breedChanceWolf = bcw;
        breedChanceHare = bch;*/
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
                Hare hare = new Hare(j1,i1,M);
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
                Wolf wolf = new Wolf(j1,i1,M,startPoints, foodBuff, hungerDeBuff);
                wolfMap[i1][j1].add(wolf);
                animalList.add(wolf);
            }
            else{
                i--;
            }

        }
    }

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
            hare.toChild();
            wolfMap[an.getY()][an.getX()].add(hare);
            animalList.add(hare);
        }
        if (an instanceof Wolf)
        {
            Wolf wolf = new Wolf(an.getX(),an.getY(),startPoints);
            wolf.toChild();
            wolfMap[an.getY()][an.getX()].add(wolf);
            animalList.add(wolf);
        }

    }

    private void zeroAn(Animal an)
    {
        wolfMap[an.getY()][an.getX()].remove(an);
        animalList.remove(an);
    }
    private void eatAn(Wolf w)
    {
        Region r = wolfMap[w.getY()][w.getX()];
        for (int i = 0; i < r.size(); i++) {
            if (w.doEat(r.get(i))){
                animalList.remove(r.get(i));
                r.remove(r.get(i));
                return;
        }
        }
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
                if (an.doBreed(birthChanceHare))
                {
                    summonAn(an);
                }
            }
            if (an instanceof Wolf)
            {
                an.doStep(this); //another order???
                moveAn(an,anX,anY);
                eatAn((Wolf) an);
                if (((Wolf) an).getPoints() <= 0.0)
                {
                    zeroAn(an);
                }
                if (an.doBreed(birthChanceWolf))
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

    /*public double getBirthChance() {
        return birthChance;
    }

    public double getStartPoints() {
        return startPoints;
    }*/

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
