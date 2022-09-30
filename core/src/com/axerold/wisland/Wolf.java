package com.axerold.wisland;

import java.util.ArrayList;

public class Wolf extends Animal {
    private double points;
    public Wolf(int x, int y, double p) {
        super(x, y);
        this.points = p;
    }
    public ArrayList<Region> getHareRegions(Region[] regions){
        ArrayList<Region> hareRegions = new ArrayList<>();
        for (Region region : regions) {
            if (region.getAnyHares()) {
                hareRegions.add(region);
            }
        }
        return hareRegions;
    }

    @Override
    public boolean doEat(Island island) {
        Region r = island.getRegion(y,x);
        if (r.getAnyHares())
        {
            points += island.getFoodBuff();
            return true;
        }
        else{
            points -= island.getHungerDeBuff();
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
            index = Island.getRandomInteger(0, vars.length);
            this.x = vars[index].getX();
            this.y = vars[index].getY();
        }
        else{
            index = Island.getRandomInteger(0, hareRegions.size());
            this.x = hareRegions.get(index).getX();
            this.y = hareRegions.get(index).getY();
        }
    }

    public double getPoints() {
        return points;
    }
}