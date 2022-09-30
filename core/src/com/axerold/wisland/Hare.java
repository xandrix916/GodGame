package com.axerold.wisland;

public class Hare extends Animal{
    public Hare(int x, int y)
    {
        super(x,y);
    }
    @Override
    public void doStep(Island island) {
        Region[] vars = makeVars(island);
        int index = Island.getRandomInteger(0, vars.length);
        this.x = vars[index].getX();
        this.y = vars[index].getY();
    }
}
