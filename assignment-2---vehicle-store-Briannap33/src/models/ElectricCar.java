package models;

import utils.Utilities;

public class ElectricCar extends Car {
    private float engineKWatts = 40;
    private int range = 100;


    public ElectricCar(Manufacturer manufacturer, String regNumber, String model, float cost, int year, int power, int secs0To60, int topSpeed, float torque, float engineKWatts, int range) {
        super(manufacturer, regNumber, model, cost, year, power, secs0To60, topSpeed, torque);
        setEngineKWatts(engineKWatts);
        setRange(range);
    }

    public float getEngineKWatts() {
        return engineKWatts;
    }

    public void setEngineKWatts(float engineKWatts) {
        if (engineKWatts >= 40 && engineKWatts <= 60) {
            this.engineKWatts = engineKWatts;
        }
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        if (Utilities.validRange(range, 100, 500)) {
            this.range = range;
        }
    }


    public double getCarbonFootPrint() {
        return(engineKWatts* getAge())/20000;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ElectricCar that = (ElectricCar) o;

        if (Float.compare(that.engineKWatts, engineKWatts) != 0) return false;
        return range == that.range;
    }

    @Override
    public String toString() {
        return   super.toString() +
                "  engineKWatts :" + engineKWatts + "KWatts "+
                ", range :" + range + "kms  " ;
    }
}
