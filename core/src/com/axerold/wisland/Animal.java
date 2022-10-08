package com.axerold.wisland;

abstract public class Animal {
    private int x, y;
    private int age = 0;
    private final int SERIAL;
    private final double TIME;
    //protected Sex sex = Sex.Default;
    private static int maxAge = 42;
    private boolean isAlive = true;
    public Animal(int x,int y, int SERIAL, double TIME){
        this.x = x;
        this.y = y;
        this.SERIAL = SERIAL;
        this.TIME = TIME;
    }
    public Animal(int x, int y, Sex sex, int serial, double TIME)
    {
        this.x = x;
        this.y = y;
        //this.sex = sex;
        this.SERIAL = serial;
        this.TIME = TIME;
    }
    public void doStep(Region r) {
        if (r.in(this)){
            this.x = r.getX();
            this.y = r.getY();
        }
        this.age++;
    }
    public boolean doBreed(double chance){
        double breedSuccess = Island.getRandomDouble(0.0,1.0);
        return breedSuccess <= chance;
    }

    public boolean doBreed(Animal partner){
        return false;
    }
    public boolean doEat(Animal an){return true;}
    public boolean doDie(){
        if (age == maxAge){
            isAlive = false;
            return true;
        }
        return false;
    }

    public boolean doDie(Animal predator){
        if (predator.x == this.x && predator.y == this.y && predator != this){
            isAlive = false;
            return true;
        }
        return false;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getAge(){return age;}

    public int getSERIAL() {
        return SERIAL;
    }

    public double getTIME() {
        return TIME;
    }
}
