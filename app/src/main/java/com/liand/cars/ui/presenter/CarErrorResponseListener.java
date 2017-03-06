package com.liand.cars.ui.presenter;

import com.android.volley.Response;
import com.android.volley.VolleyError;

public class CarErrorResponseListener implements Response.ErrorListener {
    private CarInfoPresenter carInfoPresenter;

    public CarErrorResponseListener(CarInfoPresenter presenter) {
        carInfoPresenter = presenter;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        String errorMessage = "";
        if (error.networkResponse == null) {
            errorMessage = "Server not responding";
        } else if (error.networkResponse.statusCode == 404) {
            errorMessage = "Page not found";
        } else if (error.networkResponse.statusCode == 500) {
            errorMessage = "Server error";
        }
        carInfoPresenter.setErrorMessage(errorMessage);
    }
}
