package com.axerold.wisland;

import java.util.ArrayList;

public class Region {
    private ArrayList<Animal> animals;
    private int amHares, amWolves;
    private static int x, y;
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
    }
    public void remove(int index)
    {
        if (0 <= index && index < animals.size())
        {
            if (animals.get(index) instanceof Hare)
            {
                amHares--;
            }
            if (animals.get(index) instanceof Wolf)
            {
                amWolves--;
            }
            animals.remove(index);
        }
    }
    public int size()
    {
        return animals.size();
    }

    public int find(Animal an)
    {
        return animals.indexOf(an);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Animal get(int index)
    {
        if (index > 0 && index < animals.size()) {return animals.get(index);}
        return null;
    }
}
