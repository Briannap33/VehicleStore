package models;

import utils.FuelTypeUtility;
import utils.Utilities;

import java.util.Objects;

public class CarbonFuelCar extends Car {
    private float fuelConsumption = 5;
    private float carbonEmission = 1;
    private int engineSize = 800;
    private boolean automatic = false;
    private String fuelType = "petrol";

    public CarbonFuelCar(Manufacturer manufacturer, String regNumber, String model, float cost, int year, int power, int secs0To60, int topSpeed, float torque, float fuelConsumption, float carbonEmission, int engineSize, boolean automatic, String fuelType) {
        super(manufacturer, regNumber, model, cost, year, power, secs0To60, topSpeed, torque);
        setFuelConsumption(fuelConsumption);
        setCarbonEmission(carbonEmission);
        setEngineSize(engineSize);
        this.automatic = automatic;
        setFuelType(fuelType);
    }

    public float getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(float fuelConsumption) {
        if (fuelConsumption >= 5 && fuelConsumption <= 20) {
            this.fuelConsumption = fuelConsumption;
        }
    }

    public float getCarbonEmission() {
        return carbonEmission;
    }

    public void setCarbonEmission(float carbonEmission) {
        if (carbonEmission > 0) {
            this.carbonEmission = carbonEmission;
        }
    }

    public int getEngineSize() {
        return engineSize;
    }

    public void setEngineSize(int engineSize) {
        if (Utilities.validRange(engineSize, 800, 2500)) {
            this.engineSize = engineSize;
        }
    }

    public boolean isAutomatic() {
        return automatic;
    }

    public void isAutomatic(boolean automatic) {
        this.automatic = automatic;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        if (FuelTypeUtility.validFuelType(fuelType)) {
            this.fuelType = fuelType;
        }
    }

    public double getCarbonFootPrint() {
        return (engineSize * fuelConsumption * carbonEmission * getAge()) / 2000;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        CarbonFuelCar that = (CarbonFuelCar) o;

        if (Float.compare(that.fuelConsumption, fuelConsumption) != 0) return false;
        if (Float.compare(that.carbonEmission, carbonEmission) != 0) return false;
        if (engineSize != that.engineSize) return false;
        if (automatic != that.automatic) return false;
        return Objects.equals(fuelType, that.fuelType);
    }

    @Override
    public String toString() {
        String isAuto;
        if (automatic == false) {
            isAuto =  "manual";
        } else {
            isAuto = "automatic";
        }
     return     super.toString() +
                "fuelConsumption :" + fuelConsumption + "LP100k " +
                ", carbonEmission :" + carbonEmission +
                ", engineSize :" + engineSize + "cc " +
                ", automatic :" + isAuto +
                ", fuelType :'" + fuelType + '\'';
    }
}
