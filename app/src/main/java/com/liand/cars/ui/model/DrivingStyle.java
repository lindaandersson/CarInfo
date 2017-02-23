package com.liand.cars.ui.model;

public class DrivingStyle {
    private Double mixed = null;
    private Double rural = null;
    private Double urban = null;

    public void setMixed(Double mixed) {
        this.mixed = mixed;
    }

    public void setRural(Double rural) {
        this.rural = rural;
    }

    public void setUrban(Double urban) {
        this.urban = urban;
    }

    public Double getMixed() {
        return mixed;
    }

    public Double getRural() {
        return rural;
    }

    public Double getUrban() {
        return urban;
    }
}
