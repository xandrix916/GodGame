package com.axerold.wisland;

public class Hare extends Animal{
    public Hare(int x, int y)
    {
        super(x,y);
    }
    public Hare(int x, int y, Sex sex)
    {
        super(x,y,sex);
    }
    @Override
    public void doStep(Island island) {
        Region[] vars = makeVars(island);
        int index = Island.getRandomInteger(0, vars.length-1);
        this.x = vars[index].getX();
        this.y = vars[index].getY();
    }

    public boolean equals(Object obj)
    {
        if (obj == this){
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()){
            return false;
        }
        Hare hare = (Hare) obj;
        return x == hare.x && y == hare.y;
    }
}
