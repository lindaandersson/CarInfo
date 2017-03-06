package com.liand.cars.ui.presenter;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;

public class VolleyCarRequest extends JsonObjectRequest {
    public VolleyCarRequest(String baseUrl, final CarInfoPresenter carInfoPresenter) {
        super(
                Request.Method.GET,
                baseUrl + "vehicle-attributes.json",
                new CarResponseListener(carInfoPresenter),
                new CarErrorResponseListener(carInfoPresenter)
        );
    }
}
