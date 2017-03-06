package com.liand.cars.ui.presenter;

import com.liand.cars.ui.view.CarInfoActivity;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class CarResponseListenerTest {
    private CarInfoPresenterImpl presenter;
    private CarResponseListener carResponseListener;
    private JSONObject jsonObj;

    @Before
    public void setup() throws Exception {
        presenter = Mockito.mock(CarInfoPresenterImpl.class);
        carResponseListener = new CarResponseListener(presenter);

        String json = readFile("C:\\Users\\liand1\\Documents\\AndroidSamples\\Cars\\app\\src\\test\\java\\com\\liand\\cars\\CompleteCarJson.json");
        jsonObj = new JSONObject(json);
    }

    @Test
    public void test_onResponse_canParseCorrectJsonToCar() throws Exception {
        CarInfoPresenterImpl presenter = spy(CarInfoPresenterImpl.class);
        CarInfoActivity carInfoActivityMock = Mockito.mock(CarInfoActivity.class);
        presenter.setView(carInfoActivityMock);
        carResponseListener = new CarResponseListener(presenter);

        carResponseListener.onResponse(jsonObj);

        assertEquals("tmbga61z852094863", presenter.getCar().getVinNumber());
    }

    @Test
    public void test_onResponse_needsMandatoryJsonFields() throws Exception {
        jsonObj.remove("vin");

        carResponseListener.onResponse(jsonObj);
        verify(presenter).setErrorMessage("Unable to fully parse JSON");
    }

    @Test
    public void test_onResponse_hasValidDateFormat() throws Exception {
        jsonObj.remove("timestamp");
        jsonObj.put("timestamp", "29/05/2015 18:31");

        carResponseListener.onResponse(jsonObj);
        verify(presenter).setErrorMessage("Wrong date format");
    }

    private String readFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
            return sb.toString();
        } finally {
            br.close();
        }
    }

}