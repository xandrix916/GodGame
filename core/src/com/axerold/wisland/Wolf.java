package com.axerold.wisland;

public class Wolf extends Animal {
    private double points;
    private double foodBuff = 0.2, hungerDeBuff = 0.1;
    private final static int maxAge = 15;
    public Wolf(int x, int y, double p, int SERIAL, double TIME) {
        super(x, y,SERIAL,TIME);
        this.points = p;
    }
    public Wolf(int x, int y, Sex sex, double p, int SERIAL, int TIME) {
        super(x, y, sex, SERIAL,TIME);
        this.points = p;
    }
    public Wolf(int x, int y, Sex sex, double p, double b, double d, int SERIAL, double TIME){
        super(x,y,sex, SERIAL,TIME);
        this.points = p;
        this.foodBuff = b;
        this.hungerDeBuff = d;
    }

    @Override
    public boolean doEat(Animal an) {
        if (an instanceof Hare && an.getX() == this.getX() && an.getY() == this.getY() && !an.equals(this))
        {
            points += foodBuff;
            return true;
        }
        else{
            points -= hungerDeBuff;
            return false;
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
        return this.getX() == wolf.getX() && this.getY() == wolf.getY() && points == wolf.points;
    }
}