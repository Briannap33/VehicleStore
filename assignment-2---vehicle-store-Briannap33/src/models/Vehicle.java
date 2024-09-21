package models;

import utils.Utilities;

import java.util.Objects;
public abstract class Vehicle {

    private Manufacturer manufacturer;
    private String regNumber = "No reg";
    private String model = "No model";
    private float cost = 1000;
    private int year = 2000;

    public abstract double getCarbonFootPrint();

    public Vehicle(Manufacturer manufacturer, String regNumber, String model, float cost, int year) {
        this.regNumber = Utilities.truncateString(regNumber, 8);
        this.model = Utilities.truncateString(model, 15);
        setCost(cost);
        setYear(year);
        this.manufacturer = manufacturer;
    }

    public Vehicle() {

        this.regNumber = Utilities.truncateString(regNumber, 8);
        this.model = Utilities.truncateString(model, 15);
        setCost(cost);
        setYear(year);
        this.manufacturer = manufacturer;
        // this.getCarbonFootprint();
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        if (Utilities.validStringlength(regNumber, 8)) {
            this.regNumber = regNumber;
        }

    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        if (cost >= 1000) {
            this.cost = cost;
        }
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        if (Utilities.validStringlength(model, 15)) {
            this.model = model;
        }
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        if (year >= 2000 && year <= 2023) {
            this.year = year;
        }
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vehicle vehicle = (Vehicle) o;

        if (Float.compare(vehicle.cost, cost) != 0) return false;
        if (year != vehicle.year) return false;
        if (!manufacturer.equals(vehicle.manufacturer)) return false;
        if (!Objects.equals(regNumber, vehicle.regNumber)) return false;
        return Objects.equals(model, vehicle.model);
    }


    public int getAge() {
        return 2023 - year;
    }

    @Override
    public String toString() {
        String ageSTr = "";
        if (getAge() == 0) {
            ageSTr ="Brand New! ";
        } else if (getAge() == 1) {
            ageSTr= "1 year old ";
        } else {
            ageSTr = getAge() + " years old" ;
        }

            return  "  RegNumber : " + regNumber +
                    ", Model : '" + model +
                    '\'' + ", Cost : " + cost +
                    " Manufacturer : " + manufacturer
                     + " Age : " + ageSTr ;


    }
}






