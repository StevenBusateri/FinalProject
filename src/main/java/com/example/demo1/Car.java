package com.example.demo1;

public class Car {

    private String make = "";
    private String model = "";
    private int year = 0;

    private int mpg = 0;
    private int tankSize = 0;
    private int maxMillage;

    public double tankNeed;
    public double gasNeed;

    public double cal(double miles) {

        maxMillage = mpg * tankSize;
        tankNeed = miles / maxMillage;//link to <Text id="stopCount"
        gasNeed = tankNeed * tankSize;  // link to <Text id="galCount"
        double gasPrice = 3;
        double totalcost = gasNeed * gasPrice;


        System.out.println("Max Mi " + maxMillage);
        System.out.println("Tank need " + tankSize);
        System.out.println("Gas need " + mpg);

        return totalcost;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public int getMpg() {
        return mpg;
    }

    public int getTankSize() {
        return tankSize;
    }

    public Car(String make, String model, int year, int mpg, int tankSize) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.mpg = mpg;
        this.tankSize = tankSize;
    }

    @Override
    public String toString() {
        return "Car{" +
                "mpg=" + mpg +
                ", tanksize=" + tankSize +
                ", year=" + year +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}
