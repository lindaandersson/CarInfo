package com.liand.cars.ui.presenter;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

public class VolleyCarRequest extends JsonObjectRequest {
    public VolleyCarRequest(String baseUrl, final CarInfoPresenter carInfoPresenter) {
        super(
                Request.Method.GET,
                baseUrl + "vehicle-attributes.json",
                new CarResponseListener(carInfoPresenter),
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("ERROR response", error.getStackTrace().toString());

                        String errorMessage = "";
                        if (error.networkResponse == null) {
                            errorMessage = "There is no address with the provided URL";
                        } else if (error.networkResponse.statusCode == 404) {
                            errorMessage = "Page not found";
                        } else if (error.networkResponse.statusCode == 500) {
                            errorMessage = "Server error";
                        }
                        carInfoPresenter.setErrorMessage(errorMessage);
                    }
                }
        );
    }
}
