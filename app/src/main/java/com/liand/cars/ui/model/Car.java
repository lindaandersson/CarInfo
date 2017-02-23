package com.liand.cars.ui.model;

import java.util.ArrayList;
import java.util.Date;

public class Car {
    private final String registrationNumber;
    private final String vinNumber;
    private final CarBrand brand;
    private final int year;
    private final GearBox gearBox;
    private final Date lastUpdated;
    private ArrayList<FuelTank> fuelTanks = new ArrayList<>();

    public Car(String registrationNumber, String vinNumber, CarBrand brand, int year, GearBox gearBox, Date lastUpdated) {
        this.registrationNumber = registrationNumber;
        this.vinNumber = vinNumber;
        this.brand = brand;
        this.year = year;
        this.gearBox = gearBox;
        this.lastUpdated = lastUpdated;
    }

    public void addFuelTank(FuelTank tank) {
        this.fuelTanks.add(tank);
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getVinNumber() {
        return vinNumber;
    }

    public CarBrand getBrand() {
        return brand;
    }

    public int getYear() {
        return year;
    }

    public GearBox getGearBox() {
        return gearBox;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public ArrayList<FuelTank> getFuelTanks() {
        return this.fuelTanks;
    }
}
