package com.liand.cars.ui.presenter;

import com.liand.cars.ui.model.Car;
import com.liand.cars.ui.model.CarBrand;
import com.liand.cars.ui.model.GearBox;
import com.liand.cars.ui.view.CarInfoActivity;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Date;

import static org.mockito.Mockito.verify;

public class CarInfoPresenterImplTest {
    private CarInfoActivity carInfoActivityMock;
    private CarInfoPresenterImpl presenter;

    @Before
    public void setup() throws Exception {
        carInfoActivityMock = Mockito.mock(CarInfoActivity.class);
        presenter = new CarInfoPresenterImpl(carInfoActivityMock, carInfoActivityMock);
    }

    @Test
    public void test_setCar_callsActivity() throws Exception {
        Car testCar = new Car("reg", "vin", CarBrand.FORD, 2017, GearBox.AUTOMATIC, new Date());

        presenter.setCar(testCar);

        verify(carInfoActivityMock).displayCar(testCar);
    }

    @Test
    public void test_setErrorMessage_callsActivity() throws Exception {
        presenter.setErrorMessage("Error");

        verify(carInfoActivityMock).displayErrorMessage("Error");
    }
}