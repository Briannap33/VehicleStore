package models;

import utils.Utilities;

public class Scooter extends Vehicle{
    private int power= 250;
    private int topRiderWeight= 100;
    private float weight= 5;

    public Scooter( String regNumber, String model, float cost,Manufacturer manufacturer, int year, int power, float weight, int topRiderWeight) {
        super(manufacturer, regNumber, model, cost, year);
        setPower(power);
        setWeight(weight);
        setTopRiderWeight(topRiderWeight);
    }


    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        if (weight >=5 && weight <=100) {
            this.weight = weight;
        }
    }
    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        if (Utilities.validRange(power, 250, 1000)) {
            this.power = power;
        }
    }
    public int getTopRiderWeight() {
        return topRiderWeight;
    }

    public void setTopRiderWeight(int topRiderWeight) {
        if (Utilities.validRange(topRiderWeight, 100, 120)) {
            this.topRiderWeight = topRiderWeight;
        }
    }


    @Override
    public double getCarbonFootPrint() {
        return (power* weight*getAge())/15000 ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Scooter scooter = (Scooter) o;

        if (power != scooter.power) return false;
        if (topRiderWeight != scooter.topRiderWeight) return false;
        return Float.compare(scooter.weight, weight) == 0;
    }


    @Override
    public String toString() {
        return  super.toString()+
                " power :" + power +
                ", topRiderWeight :" + topRiderWeight +
                ", Weight : " + weight + "kg ";
    }
}
