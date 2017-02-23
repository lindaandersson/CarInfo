package com.liand.cars.ui.view;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liand.cars.R;
import com.liand.cars.ui.model.DrivingStyle;
import com.liand.cars.ui.model.FuelTank;
import com.liand.cars.ui.model.FuelType;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class TankInfoFragment extends Fragment {
    private static DecimalFormat decimalFormat = new DecimalFormat("#.#########");
    @BindView(R.id.tank_info)
    LinearLayout fragmentView;
    @BindView(R.id.fuel_type)
    TextView fuelType;
    @BindView(R.id.tank_volume)
    TextView tankVolume;

    @BindView(R.id.mixed_consumption)
    TextView mixedConsumption;
    @BindView(R.id.rural_consumption)
    TextView ruralConsumption;
    @BindView(R.id.urban_consumption)
    TextView urbanConsumption;
    @BindView(R.id.mixed_emission)
    TextView mixedEmission;
    @BindView(R.id.rural_emission)
    TextView ruralEmission;
    @BindView(R.id.urban_emission)
    TextView urbanEmission;

    private Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tank_info, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    public void setTankInfo(FuelTank tank) {
        fragmentView.setVisibility(View.VISIBLE);
        fuelType.setText(FuelType.getLocalizedString(tank.getFuelType()));
        setText(tankVolume, tank.getTankVolume());

        DrivingStyle consumption = tank.getConsumption();
        setText(mixedConsumption, consumption.getMixed());
        setText(ruralConsumption, consumption.getRural());
        setText(urbanConsumption, consumption.getUrban());

        DrivingStyle emission = tank.getEmission();
        setText(mixedEmission, emission.getMixed());
        setText(ruralEmission, emission.getRural());
        setText(urbanEmission, emission.getUrban());
    }

    private void setText(TextView textView, Double value) {
        if (value != null) {
            textView.setText(String.valueOf(decimalFormat.format(value)));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
