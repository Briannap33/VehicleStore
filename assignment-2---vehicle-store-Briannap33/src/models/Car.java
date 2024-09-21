package models;

import utils.Utilities;

public abstract class Car extends Vehicle {
    private int power = 120;
    private int secs0To60 = 4;
    private int topSpeed = 50;
    private float torque = 100;


    public Car(Manufacturer manufacturer, String regNumber, String model, float cost, int year, int power, int secs0To60, int topSpeed, float torque) {
        super(manufacturer, regNumber, model, cost, year);
        setPower(power);
        setSecs0To60(secs0To60);
        setTopSpeed(topSpeed);
        setTorque(torque);
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        if (Utilities.validRange(power, 120, 300)) {
            this.power = power;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Car car = (Car) o;

        if (power != car.power) return false;
        if (secs0To60 != car.secs0To60) return false;
        if (topSpeed != car.topSpeed) return false;
        return Float.compare(car.torque, torque) == 0;
    }


    public int getSecs0To60() {
        return secs0To60;
    }

    public void setSecs0To60(int secs0To60) {
        if (Utilities.validRange(secs0To60, 4, 25)) {
            this.secs0To60 = secs0To60;
        }
    }

    public int getTopSpeed() {
        return topSpeed;
    }

    public void setTopSpeed(int topSpeed) {
        if (Utilities.validRange(topSpeed,50, 3000)) {
            this.topSpeed = topSpeed;
        }
    }

    public float getTorque() {
        return torque;
    }

    public void setTorque(float torque) {
        if (torque >= 100 && torque <= 400) {
            this.torque = torque;
        }
    }
    public abstract double getCarbonFootPrint();

    @Override
    public String toString() {
        return "Car - " +super.toString()+
                " power :" + power +
                ", secs0To60 :" + secs0To60 + " secs " +
                ", topSpeed :" + topSpeed + " k/h  " +
                ", torque :" + torque;
    }
}