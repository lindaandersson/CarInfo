package com.liand.cars.ui.model;

import com.liand.cars.R;

public enum FuelType {
    GASOLINE("gasoline"),
    DIESEL("diesel");

    private String fuelType;

    FuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getFuelType() {
        return fuelType;
    }

    public static FuelType fromString(String text) {
        for (FuelType fuel : FuelType.values()) {
            if (fuel.fuelType.equalsIgnoreCase(text)) {
                return fuel;
            }
        }
        return null;
    }

    public static int getLocalizedString(FuelType fuelType) {
        switch (fuelType) {
            case GASOLINE:
                return R.string.gasoline;
            case DIESEL:
                return R.string.diesel;
            default:
                return R.string.not_applicable;
        }
    }
}
