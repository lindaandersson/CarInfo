package com.liand.cars.ui.view;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.liand.cars.BuildConfig;
import com.liand.cars.R;
import com.liand.cars.ui.model.Car;
import com.liand.cars.ui.model.CarBrand;
import com.liand.cars.ui.model.DrivingStyle;
import com.liand.cars.ui.model.FuelTank;
import com.liand.cars.ui.model.FuelType;
import com.liand.cars.ui.model.GearBox;
import com.liand.cars.ui.presenter.CarErrorResponseListener;
import com.liand.cars.ui.presenter.CarInfoPresenterImpl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;

import java.util.Date;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;


@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class CarInfoActivityTest {
    private  CarInfoActivity activity;
    private CarInfoPresenterImpl presenter;

    @Before
    public void setup() throws Exception {
        activity = Robolectric.setupActivity(CarInfoActivity.class);
        presenter = mock(CarInfoPresenterImpl.class);
    }


    @Test
    public void test_clickingGetCar_withInvalidVin_shouldNotGetCar() {
        EditText vin = (EditText) activity.findViewById(R.id.vin_edit_text);

        vin.setText("");

        activity.findViewById(R.id.get_car_button).performClick();

        verifyZeroInteractions(presenter);
    }

    @Test
    public void test_clickingGetCar_withValidVin_shouldGetCar() {
        activity.setPresenter(presenter);
        EditText vin = (EditText) activity.findViewById(R.id.vin_edit_text);

        vin.setText("test vin");

        activity.findViewById(R.id.get_car_button).performClick();

        verify(presenter).getCar("test vin");
    }

    @Test
    public void test_clickingGetCar_shouldDisplayCarInfo() {
        Car car = new Car("Reg", "vin", CarBrand.FORD, 1222, GearBox.AUTOMATIC, new Date());
        FuelTank dieselTank = new FuelTank(FuelType.DIESEL);
        dieselTank.setConsumption(new DrivingStyle());
        dieselTank.setEmission(new DrivingStyle());
        dieselTank.setTankVolume(12.2);
        car.addFuelTank(dieselTank);
        FuelTank gasolineTank = new FuelTank(FuelType.GASOLINE);
        DrivingStyle consumption = new DrivingStyle();
        consumption.setMixed(0.000099);
        consumption.setMixed(0.000059);
        consumption.setMixed(0.000079);
        gasolineTank.setConsumption(new DrivingStyle());
        gasolineTank.setEmission(new DrivingStyle());
        gasolineTank.setTankVolume(12.2);
        car.addFuelTank(gasolineTank);
        activity.displayCar(car);

        TextView vinNumber = (TextView) activity.findViewById(R.id.vin_number);
        assertEquals(View.VISIBLE, vinNumber.getVisibility());
        assertEquals("vin", vinNumber.getText());
    }

    @Test
    public void test_displayErrorMessage() {
        TextView errorTextView = (TextView) activity.findViewById(R.id.error_message);
        assertEquals("Error message", errorTextView.getText());
        assertEquals(View.GONE, errorTextView.getVisibility());

        activity.displayErrorMessage("Some new error");

        assertEquals("Some new error", errorTextView.getText());
        assertEquals(View.VISIBLE, errorTextView.getVisibility());
    }

    @Test
    public void test_destroyActivity_isSuccessful() {
        ActivityController<CarInfoActivity> activityController = Robolectric.buildActivity(CarInfoActivity.class);
        CarInfoActivity activity = activityController.create().start().postCreate(null).resume().visible().get();
        activityController.pause().stop().destroy();

        assertTrue(activity.isDestroyed());
    }
}