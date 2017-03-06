package com.liand.cars.ui.presenter;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;

public class CarErrorResponseListenerTest {
    private CarInfoPresenterImpl presenter;
    private CarErrorResponseListener carErrorResponseListener;

    @Before
    public void setup() throws Exception {
        presenter = Mockito.mock(CarInfoPresenterImpl.class);
        carErrorResponseListener = new CarErrorResponseListener(presenter);
    }

    @Test
    public void test_handle_noResponseError() {
        VolleyError error = new VolleyError();
        carErrorResponseListener.onErrorResponse(error);

        verify(presenter).setErrorMessage("Server not responding");
    }

    @Test
    public void test_handle_404_error() {
        VolleyError error = new VolleyError(new NetworkResponse(404, null, null, false));
        carErrorResponseListener.onErrorResponse(error);

        verify(presenter).setErrorMessage("Page not found");
    }

    @Test
    public void test_handle_500_error() {
        VolleyError error = new VolleyError(new NetworkResponse(500, null, null, false));
        carErrorResponseListener.onErrorResponse(error);

        verify(presenter).setErrorMessage("Server error");
    }
}