package com.liand.cars.ui.presenter;

import com.liand.cars.ui.model.Car;

public interface CarInfoPresenter {
    void onResume();

    void onDestroy();

    void getCar(String vin);

    void setCar(Car car);

    void setErrorMessage(String errorMessage);
}