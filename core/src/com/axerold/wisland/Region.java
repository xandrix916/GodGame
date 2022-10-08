package com.axerold.wisland;

import java.util.ArrayList;

public class Region {
    private final ArrayList<Animal> animals;
    private int amHares, amWolves;
    private int amFemales, amMales;
    private final int x;
    private final int y;
    public Region(ArrayList<Animal> a, int x1, int y1)
    {
        x = x1;
        y = y1;
        this.animals = a;
        this.amHares = 0;
    }
    public boolean isEmpty(){
        return animals.isEmpty();
    }
    public int getAmHares(){
        return amHares;
    }
    public int getAmWolves() {return amWolves; }
    public void add(Animal an)
    {
        animals.add(an);
        if (an instanceof Hare)
        {
            amHares++;
        }
        if (an instanceof Wolf)
        {
            amWolves++;
        }
        /*if (an.sex == Sex.Male)
        {
            amMales++;
        }
        if (an.sex == Sex.Female)
        {
            amFemales++;
        }*/
    }
    public void remove(Animal an)
    {
        if (an instanceof Hare)
        {
            amHares--;
        }
        if (an instanceof Wolf)
        {
            amWolves--;
        }
        /*if (an.sex == Sex.Male)
        {
            amMales--;
        }
        if (an.sex == Sex.Female)
        {
            amFemales--;
        }*/
        animals.remove(an);
    }

    public int size()
    {
        return animals.size();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Animal get(int index)
    {
        if (index >= 0 && index < animals.size()) {return animals.get(index);}
        return null;
    }

    public int getAmFemales() {
        return amFemales;
    }

    public int getAmMales() {
        return amMales;
    }
}
