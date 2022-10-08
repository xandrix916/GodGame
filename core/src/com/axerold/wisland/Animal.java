package com.axerold.wisland;

abstract public class Animal {
    protected int x, y;
    //protected Sex sex = Sex.Default;
    protected boolean newborn = false;
    protected int maxAge = 0;
    public Animal(int x,int y){
        this.x = x;
        this.y = y;
    }
    public Animal(int x, int y, Sex sex)
    {
        this.x = x;
        this.y = y;
        //this.sex = sex;
    }
    public void doStep(Island island){}
    public boolean doBreed(double chance){
        double breedSuccess = Island.getRandomDouble(0.0,1.0);
        return breedSuccess <= chance;
    }
    public boolean doBreed(Island island){
        return false;
    }
    public boolean doEat(Animal an){return true;}
    public Region[] makeVars(Island island)
    {
        Region[] vars;
        if ((x == 0 || x == island.getN() - 1) && (y == 0 || y == island.getN() - 1)) {
            vars = new Region[4];
        } else if (((x == 0 || x == island.getN() - 1) && (0 < y) && (y < island.getN() - 1)) || ((y == 0 || y == island.getN() - 1) && (0 < x) && (x < island.getN() - 1))) {
            vars = new Region[6];
        }
        else {
            vars = new Region[9];
        }
        int varsCounter = 0;
        for (int i = y-1; i < y+2; i++) {
            if (i>=0 && i < island.getN())
            {
                for (int j = x-1; j < x+2; j++) {
                    if (j>=0 && j < island.getN())
                    {
                        vars[varsCounter] = island.getRegion(i,j);
                        varsCounter++;
                    }
                }
            }
        }
        return vars;
    }
    public void toChild() {newborn = true;}
    public void growUp() {newborn = false;}

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
}
