package com.liand.cars.ui.presenter;

import android.support.annotation.NonNull;

import com.android.volley.Response;
import com.liand.cars.ui.model.Car;
import com.liand.cars.ui.model.CarBrand;
import com.liand.cars.ui.model.DrivingStyle;
import com.liand.cars.ui.model.FuelTank;
import com.liand.cars.ui.model.FuelType;
import com.liand.cars.ui.model.GearBox;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CarResponseListener implements Response.Listener<JSONObject> {
    private CarInfoPresenter carInfoPresenter;

    public CarResponseListener(CarInfoPresenter presenter) {
        carInfoPresenter = presenter;
    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            Car car = getCar(response);
            addFuelTank(response, car);
            carInfoPresenter.setCar(car);
        } catch (ParseException e) {
            e.getMessage();
            carInfoPresenter.setErrorMessage("Wrong date format");
        } catch (JSONException e) {
            e.getMessage();
            carInfoPresenter.setErrorMessage("Unable to fully parse JSON");
        }
    }

    @NonNull
    private Car getCar(JSONObject response) throws JSONException, ParseException {
        String registrationNumber = response.getString("regno");
        String vinNumber = response.getString("vin");
        CarBrand brand = CarBrand.fromString(response.getString("brand"));
        int year = response.getInt("year");
        GearBox gearBox = GearBox.fromString(response.getString("gearbox_type"));
        Date lastUpdated = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US).parse(response.getString("timestamp"));
        return new Car(registrationNumber, vinNumber, brand, year, gearBox, lastUpdated);
    }

    private void addFuelTank(JSONObject response, Car car) throws JSONException {
        JSONArray fuelTypes = response.getJSONArray("fuel_types");
        String fuelType = "";
        for (int i = 0; i < fuelTypes.length(); i++) {
            fuelType += fuelTypes.getString(i);
        }
        if (fuelType.contains(FuelType.GASOLINE.getFuelType())) {
            car.addFuelTank(getTank(response, FuelType.GASOLINE));
        } else if (fuelType.contains(FuelType.DIESEL.getFuelType())) {
            car.addFuelTank(getTank(response, FuelType.DIESEL));
        }
    }

    private FuelTank getTank(JSONObject response, FuelType fuelType) throws JSONException {
        JSONObject consumption = response.getJSONObject("fuel").getJSONObject(fuelType.getFuelType()).getJSONObject("average_consumption");
        Double tankVolume = response.getJSONObject("fuel").getDouble("tank_volume");
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
