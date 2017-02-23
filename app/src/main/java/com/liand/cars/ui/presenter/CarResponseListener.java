package com.liand.cars.ui.presenter;

import android.support.annotation.NonNull;

import com.android.volley.Response;
import com.liand.cars.ui.model.DrivingStyle;
import com.liand.cars.ui.model.FuelTank;
import com.liand.cars.ui.model.FuelType;
import com.liand.cars.ui.model.Car;
import com.liand.cars.ui.model.CarBrand;
import com.liand.cars.ui.model.GearBox;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CarResponseListener implements Response.Listener<JSONObject> {
    private CarInfoPresenter carInfoView;

    public CarResponseListener(CarInfoPresenter view) {
        carInfoView = view;
    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            Car car = getCar(response);

            String fuelType = response.getString("fuel_types");
            if (fuelType.contains(FuelType.GASOLINE.getFuelType())) {
                car.addFuelTank(getTank(response, FuelType.GASOLINE));
            } else if (fuelType.contains(FuelType.DIESEL.getFuelType())) {
                car.addFuelTank(getTank(response, FuelType.DIESEL));
            }
            carInfoView.setCar(car);
        } catch (JSONException e) {
            e.getMessage();
            carInfoView.setErrorMessage("Unable to fully parse JSON");
        }
    }

    @NonNull
    private Car getCar(JSONObject response) throws JSONException {
        String registrationNumber = response.getString("regno");
        String vinNumber = response.getString("vin");
        CarBrand brand = CarBrand.fromString(response.getString("brand"));
        int year = response.getInt("year");
        GearBox gearBox = GearBox.fromString(response.getString("gearbox_type"));

        Date lastUpdated = null;
        String date = response.getString("timestamp");
        try {
            lastUpdated = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US).parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Car(registrationNumber, vinNumber, brand, year, gearBox, lastUpdated);
    }

    private FuelTank getTank(JSONObject response, FuelType fuelType) throws JSONException {
        JSONObject consumption = response.getJSONObject("fuel").getJSONObject(fuelType.getFuelType()).getJSONObject("average_consumption");

        Double tankVolume = null;
        try {
            tankVolume = response.getJSONObject("fuel").getJSONObject(fuelType.getFuelType()).getDouble("tank_volume");
        } catch (JSONException e){

        }
        JSONObject emission = response.getJSONObject("emission").getJSONObject(fuelType.getFuelType()).getJSONObject("co2");

        FuelTank fuelTank = new FuelTank(fuelType);
        fuelTank.setConsumption(getDrivingStyle(consumption));
        fuelTank.setTankVolume(tankVolume);
        fuelTank.setEmission(getDrivingStyle(emission));
        return fuelTank;
    }

    private DrivingStyle getDrivingStyle(JSONObject json) throws JSONException {
        DrivingStyle drivingStyle = new DrivingStyle();
        if (json.has("mixed")) {
            drivingStyle.setMixed(json.getDouble("mixed"));
        }
        if (json.has("rural")) {
            drivingStyle.setRural(json.getDouble("rural"));
        }
        if (json.has("urban")) {
            drivingStyle.setUrban(json.getDouble("urban"));
        }
        return drivingStyle;
    }
}
