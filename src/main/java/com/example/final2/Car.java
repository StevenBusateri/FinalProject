package com.example.final2;

public class Car {

       private  String make = "";
    private  String model = "";
        private int year = 0;

    private int mpg =0;
    private int tankSize= 0;

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
