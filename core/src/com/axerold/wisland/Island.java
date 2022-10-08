package com.axerold.wisland;


import java.util.ArrayList;
import java.util.HashMap;

public class Island {
    private final Region[][] wolfMap;
    private final ArrayList<Animal> animalList = new ArrayList<>();
    private static int n;
    private static double startPoints, hungerDeBuff, foodBuff, deltaTime = 0.5;
    private static double birthChanceWolf, birthChanceHare/* ,breedChanceWolf, breedChanceHare*/;
    private static boolean hareAI;
    private static final Sex M = Sex.Male, F = Sex.Female;
    private int SERIAL = 0;
    private double TIME = 0.0;

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
                Hare hare = new Hare(j1,i1,SERIAL,TIME);
                wolfMap[i1][j1].add(hare);
                animalList.add(hare);
                SERIAL++;
            }
            else{
                i--;
            }
        }
        for (int i = 0; i < amWolfs; i++) {
            i1 = getRandomInteger(0,n-1);
            j1 = getRandomInteger(0,n-1);
            if (wolfMap[i1][j1].isEmpty()){
                Wolf wolf = new Wolf(j1,i1,M,startPoints, foodBuff, hungerDeBuff, SERIAL, TIME);
                wolfMap[i1][j1].add(wolf);
                animalList.add(wolf);
                SERIAL++;
            }
            else{
                i--;
            }

        }
    }

    public Region[] makeVars(int x, int y)
    {
        Region[] vars;
        if ((x == 0 || x == this.getN() - 1) && (y == 0 || y == this.getN() - 1)) {
            vars = new Region[4];
        } else if (((x == 0 || x == this.getN() - 1) && (0 < y) && (y < this.getN() - 1)) || ((y == 0 || y == this.getN() - 1) && (0 < x) && (x < this.getN() - 1))) {
            vars = new Region[6];
        }
        else {
            vars = new Region[9];
        }
        int varsCounter = 0;
        for (int i = y-1; i < y+2; i++) {
            if (i>=0 && i < this.getN())
            {
                for (int j = x-1; j < x+2; j++) {
                    if (j>=0 && j < this.getN())
                    {
                        vars[varsCounter] = wolfMap[i][j];
                        varsCounter++;
                    }
                }
            }
        }
        return vars;
    }

    public ArrayList<Region> getHareRegions(Region[] regions){
        ArrayList<Region> hareRegions = new ArrayList<>();
        for (Region region : regions) {
            if (region.getAmHares()>0) {
                hareRegions.add(region);
            }
        }
        return hareRegions;
    }


    private void moveAn(Animal an, int fromX, int fromY)
    {
        Region[] vars = makeVars(fromX,fromY);
        int index = 0, toX = 0, toY = 0;
        ArrayList<Region> hareRegions = getHareRegions(vars);
        if (an instanceof Wolf)
        {
            if (!hareRegions.isEmpty()){
                index = Island.getRandomInteger(0, hareRegions.size()-1);
                toX = hareRegions.get(index).getX();
                toY = hareRegions.get(index).getY();
        }
        }
        if (an instanceof Hare || hareRegions.isEmpty()){
            index = Island.getRandomInteger(0, vars.length -1);
            toX = vars[index].getX();
            toY = vars[index].getY();
        }
        if (fromX != toX || fromY != toY)
        {
            wolfMap[toY][toX].add(an);
            an.doStep(vars[index]);
            wolfMap[fromY][fromX].remove(an);
        }
    }
    private void summonAn(Animal an)
    {
        if (an instanceof Hare){
            Hare hare = new Hare(an.getX(), an.getY(),SERIAL,TIME);
            wolfMap[an.getY()][an.getX()].add(hare);
            animalList.add(hare);
            SERIAL++;
        }
        if (an instanceof Wolf)
        {
            Wolf wolf = new Wolf(an.getX(),an.getY(),startPoints, SERIAL, TIME);
            wolfMap[an.getY()][an.getX()].add(wolf);
            animalList.add(wolf);
            SERIAL++;
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
                if (r.get(i).doDie(w))
                {
                    zeroAn(r.get(i));
                    return;
                }
        }
        }
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
                moveAn(an,anX,anY);
                if (an.doBreed(birthChanceHare))
                {
                    summonAn(an);
                }
            }
            if (an instanceof Wolf)
            {
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
        TIME+=deltaTime;
    }
    public int getN(){
        return n;
    }

    //public int getM() {return 0;}

    public HashMap<String, Integer> getRegion(int i1, int j1)
    {
        Region r = this.wolfMap[i1][j1];
        HashMap<String, Integer> stats = new HashMap<>();
        stats.put("hares", r.getAmHares());
        stats.put("wolves", r.getAmWolves());
        return stats;
    }

}
