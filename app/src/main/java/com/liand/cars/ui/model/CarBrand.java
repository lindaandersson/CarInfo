package com.liand.cars.ui.model;

import com.liand.cars.R;

public enum CarBrand {
    VOLVO("volvo"),
    FORD("ford"),
    SAAB("saab"),
    ROVER("rover");

    private String brand;

    CarBrand(String brand) {
        this.brand = brand;
    }

    public static CarBrand fromString(String text) {
        for (CarBrand carBrand : CarBrand.values()) {
            if (carBrand.brand.equalsIgnoreCase(text)) {
                return carBrand;
            }
        }
        return null;
    }

    public static int getLocalizedString(CarBrand gearBox) {
        switch (gearBox) {
            case VOLVO:
                return R.string.volvo;
            case FORD:
                return R.string.ford;
            case SAAB:
                return R.string.saab;
            case ROVER:
                return R.string.rover;
            default:
                return R.string.not_applicable;
        }
    }
}
