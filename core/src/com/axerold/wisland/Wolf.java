package com.axerold.wisland;

public class Wolf extends Animal {
    private double points;
    private double foodBuff, hungerDeBuff;
    private static int maxAge;
    public Wolf(int x, int y, Constants constants, int SERIAL, double TIME) {
        super(x, y,SERIAL,TIME);
        this.points = constants.getStartPoints();
        this.foodBuff = constants.getFoodBuff();
        this.hungerDeBuff = constants.getHungerDeBuff();
        maxAge = constants.getWolfMaxAge();
    }
    public Wolf(int x, int y, Sex sex, Constants constants, int SERIAL, double TIME){
        super(x,y,sex, SERIAL,TIME);
        this.points = constants.getStartPoints();
        this.foodBuff = constants.getFoodBuff();
        this.hungerDeBuff = constants.getHungerDeBuff();
        maxAge = constants.getWolfMaxAge();
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