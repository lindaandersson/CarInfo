package com.liand.cars.ui.view;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liand.cars.R;
import com.liand.cars.ui.model.Car;
import com.liand.cars.ui.model.CarBrand;
import com.liand.cars.ui.model.GearBox;

import java.text.SimpleDateFormat;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class CarInfoFragment extends Fragment {
    public static final SimpleDateFormat DATE_AND_TIME_FORMATTER = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.ENGLISH);
    @BindView(R.id.car_info)
    LinearLayout fragmentView;
    @BindView(R.id.reg_number)
    TextView regNumber;
    @BindView(R.id.vin_number)
    TextView vinNumber;
    @BindView(R.id.brand)
    TextView brand;
    @BindView(R.id.year)
    TextView year;
    @BindView(R.id.gearbox)
    TextView gearbox;
    @BindView(R.id.last_edited)
    TextView lastEdited;

    private Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_car_info, container, false);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    public void setCarInfo(Car car) {
        fragmentView.setVisibility(View.VISIBLE);
        regNumber.setText(car.getRegistrationNumber());
        vinNumber.setText(car.getVinNumber());
        brand.setText(getString(CarBrand.getLocalizedString(car.getBrand())));
        year.setText(String.valueOf(car.getYear()));
        gearbox.setText(getString(GearBox.getLocalizedString(car.getGearBox())));
        lastEdited.setText(DATE_AND_TIME_FORMATTER.format(car.getLastUpdated()));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}