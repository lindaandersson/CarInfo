package com.liand.cars.ui.presenter;

import android.content.Context;

import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.liand.cars.ui.model.Car;
import com.liand.cars.ui.view.CarInfoView;

public class CarInfoPresenterImpl implements CarInfoPresenter {
    private static final String BASE_URL = "https://gist.githubusercontent.com/sommestad/e38c1acf2aed495edf2d/raw/cdb6dfb85101eedad60853c44266249a3f4ac5df/";
    private CarInfoView carInfoView;
    private Context context;
    private Car car;

    public CarInfoPresenterImpl() {
    }

    public CarInfoPresenterImpl(CarInfoView carInfoView, Context context) {
        this.carInfoView = carInfoView;
        this.context = context;
    }

    public void setView(CarInfoView view) {
        carInfoView = view;
    }

    @Override
    public void onResume() {
    }

    @Override
    public void onDestroy() {
        carInfoView = null;
    }

    @Override
    public void getCar(String vin) {
        final JsonObjectRequest jsonObjReq = new VolleyCarRequest(BASE_URL, this);
        Volley.newRequestQueue(context).add(jsonObjReq);
    }

    @Override
    public void setCar(Car car) {
        this.car = car;
        carInfoView.displayCar(car);
    }

    public Car getCar() {
        return car;
    }

    @Override
    public void setErrorMessage(String errorMessage) {
        carInfoView.displayErrorMessage(errorMessage);
    }
}
