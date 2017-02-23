package com.liand.cars.ui.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.liand.cars.R;
import com.liand.cars.ui.model.Car;
import com.liand.cars.ui.model.FuelTank;
import com.liand.cars.ui.model.FuelType;
import com.liand.cars.ui.presenter.CarInfoPresenter;
import com.liand.cars.ui.presenter.CarInfoPresenterImpl;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static butterknife.ButterKnife.findById;

public class CarInfoActivity extends AppCompatActivity implements CarInfoView {
    private CarInfoPresenter presenter;

    @BindView(R.id.vin_edit_text)
    EditText vinEditText;
    @BindView(R.id.error_message)
    TextView errorMessage;
    CarInfoFragment carInfoFragment;
    TankInfoFragment gasolineTankInfoFragment;
    TankInfoFragment dieselTankInfoFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupUI(findById(this, R.id.activity_main));
        carInfoFragment = (CarInfoFragment) getFragmentManager().findFragmentById(R.id.car_info_fragment);
        gasolineTankInfoFragment = (TankInfoFragment) getFragmentManager().findFragmentById(R.id.gasoline_tank_info_fragment);
        dieselTankInfoFragment = (TankInfoFragment) getFragmentManager().findFragmentById(R.id.diesel_tank_info_fragment);

        presenter = new CarInfoPresenterImpl(this, this);
    }

    @OnClick(R.id.get_car_button)
    public void onGetCarClick() {
        String vin = vinEditText.getText().toString();
        if (!vin.isEmpty()) {
            presenter.getCar(vin);
        }
    }

    @Override
    public void displayCar(Car car) {
        carInfoFragment.setCarInfo(car);
        ArrayList<FuelTank> fuelTanks = car.getFuelTanks();
        for (FuelTank tank : fuelTanks) {
            if (tank.getFuelType().equals(FuelType.GASOLINE)) {
                gasolineTankInfoFragment.setTankInfo(tank);
            } else if (tank.getFuelType().equals(FuelType.DIESEL)) {
                dieselTankInfoFragment.setTankInfo(tank);
            }
        }
        errorMessage.setVisibility(View.GONE);
    }

    @Override
    public void displayErrorMessage(String error) {
        errorMessage.setText(error);
        errorMessage.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    public void setupUI(View view) {

        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(CarInfoActivity.this);
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView);
            }
        }
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }
}

