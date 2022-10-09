package com.axerold.wisland;

public class Constants {
    private static double startPoints = 1.0, foodBuff = 0.2, hungerDeBuff = 0.1;
    private static double birthChanceWolf = 0.1, birthChanceHare = 0.4,breedChanceWolf = 0.8, breedChanceHare = 0.96;
    private static int pixels = 40;
    private static double deltaTime = 0.5;
    private static int wolfMaxAge = 15, hareMaxAge = 10;
    public Constants(){}
    public Constants(double stpts, double fbf, double hdbf, double bcw, double bch, double bicw, double bich, int px){
        startPoints = stpts;
        foodBuff = fbf;
        hungerDeBuff = hdbf;
        birthChanceWolf = bicw;
        birthChanceHare = bich;
        breedChanceWolf = bcw;
        breedChanceHare = bch;
        pixels = px;
    }

    public double getBirthChanceHare() {
        return birthChanceHare;
    }

    public double getBirthChanceWolf() {
        return birthChanceWolf;
    }

    public double getBreedChanceHare() {
        return breedChanceHare;
    }

    public double getBreedChanceWolf() {
        return breedChanceWolf;
    }

    public double getFoodBuff() {
        return foodBuff;
    }

    public double getHungerDeBuff() {
        return hungerDeBuff;
    }

    public double getStartPoints() {
        return startPoints;
    }

    public int getPixels() {
        return pixels;
    }

    public double getDeltaTime() {
        return deltaTime;
    }

    public static int getHareMaxAge() {
        return hareMaxAge;
    }
    public int getWolfMaxAge(){
        return wolfMaxAge;
    }
}
