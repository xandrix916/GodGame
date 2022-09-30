package com.axerold.wisland;

import java.util.ArrayList;
import java.util.List;

public class Region {
    private ArrayList<Animal> animals;
    private boolean anyHares;
    private static int x, y;
    public Region(ArrayList<Animal> a, int x1, int y1)
    {
        x = x1;
        y = y1;
        this.animals = a;
        this.anyHares = false;
    }
    public boolean isEmpty(){
        return animals.isEmpty();
    }
    public boolean getAnyHares(){
        return anyHares;
    }
    public void add(Animal an)
    {
        animals.add(an);
        if (an instanceof Hare)
        {
            anyHares = true;
        }
    }
    public void remove(int index)
    {
        animals.remove(index);
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
}
