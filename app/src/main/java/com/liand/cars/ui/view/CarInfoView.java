package com.liand.cars.ui.view;

import com.liand.cars.ui.model.Car;

public interface CarInfoView {
    void displayCar(Car car);

    void displayErrorMessage(String errorMessage);
}