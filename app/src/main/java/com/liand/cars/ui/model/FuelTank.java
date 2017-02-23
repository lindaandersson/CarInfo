package com.liand.cars.ui.model;

public class FuelTank {
    private FuelType fuelType;
    private DrivingStyle consumption;
    private DrivingStyle emission;
    private Double tankVolume;

    public FuelTank(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setConsumption(DrivingStyle consumption) {
        this.consumption = consumption;
    }

    public void setEmission(DrivingStyle emission) {
        this.emission = emission;
    }

    public void setTankVolume(Double tankVolume) {
        this.tankVolume = tankVolume;
    }

    public DrivingStyle getConsumption() {
        return consumption;
    }

    public DrivingStyle getEmission() {
        return emission;
    }

    public Double getTankVolume() {
        return tankVolume;
    }
}
