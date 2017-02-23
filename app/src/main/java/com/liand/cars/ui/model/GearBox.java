package com.liand.cars.ui.model;

import com.liand.cars.R;

public enum GearBox {
    MANUAL("manual"),
    AUTOMATIC("automatic");

    private String gearBox;

    GearBox(String gearBox) {
        this.gearBox = gearBox;
    }

    public static GearBox fromString(String text) {
        for (GearBox box : GearBox.values()) {
            if (box.gearBox.equalsIgnoreCase(text)) {
                return box;
            }
        }
        return null;
    }

    public static int getLocalizedString(GearBox gearBox) {
        switch (gearBox) {
            case MANUAL:
                return R.string.manual_gearbox;
            case AUTOMATIC:
                return R.string.automatic_gearbox;
            default:
                return R.string.not_applicable;
        }
    }
}
