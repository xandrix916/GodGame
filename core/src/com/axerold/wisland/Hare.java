package com.axerold.wisland;

public class Hare extends Animal{
    private static int maxAge = Constants.getHareMaxAge();
    public Hare(int x, int y, int SERIAL, double TIME)
    {
        super(x,y, SERIAL, TIME);
    }
    public Hare(int x, int y, Sex sex, int SERIAL, double TIME)
    {
        super(x,y,sex,SERIAL,TIME);
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
        return this.getX() == hare.getX() && this.getY() == hare.getY();
    }

}
