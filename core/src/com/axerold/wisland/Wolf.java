package com.axerold.wisland;

import java.util.ArrayList;

public class Wolf extends Animal {
    private double points;
    protected double foodBuff = 0.2, hungerDeBuff = 0.1;
    protected final int maxAge = 15;
    public Wolf(int x, int y, double p) {
        super(x, y);
        this.points = p;
    }
    public Wolf(int x, int y, Sex sex, double p) {
        super(x, y, sex);
        this.points = p;
    }
    public Wolf(int x, int y, Sex sex, double p, double b, double d){
        super(x,y,sex);
        this.points = p;
        this.foodBuff = b;
        this.hungerDeBuff = d;
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

    @Override
    public boolean doEat(Animal an) {
        /*Region r = island.getRegion(y,x);
        if (r.getAmHares() > 0)
        {
            points += island.getFoodBuff();
            return true;
        }
        else{
            points -= island.getHungerDeBuff();
            return false;
        }*/
        if (an instanceof Hare && an.getX() == this.x && an.getY() == this.y && !an.equals(this))
        {
            points += foodBuff;
            return true;
        }
        else{
            points -= hungerDeBuff;
            return false;
        }
    }

    @Override
    public void doStep(Island island) {
        Region[] vars = makeVars(island);
        ArrayList<Region> hareRegions = getHareRegions(vars);
        int index;
        if (hareRegions.isEmpty())
        {
            index = Island.getRandomInteger(0, vars.length -1);
            this.x = vars[index].getX();
            this.y = vars[index].getY();
        }
        else{
            index = Island.getRandomInteger(0, hareRegions.size()-1);
            this.x = hareRegions.get(index).getX();
            this.y = hareRegions.get(index).getY();
        }
    }

    public double getPoints() {
        return points;
    }

    public boolean equals(Object obj)
    {
        if (obj == this){
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()){
            return false;
        }
        Wolf wolf = (Wolf) obj;
        return x == wolf.x && y == wolf.y && points == wolf.points;
    }
}