package com.liand.cars.ui.view;

public class FuelAdapter {//extends ArrayAdapter<FuelTank> {

   /* public FuelAdapter(Context context) {
        super(context, R.layout.tank_info_fragment);
    }

    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(this.getContext()).inflate(R.layout.tank_info_fragment, parent, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        FuelTank item = getItem(position);
        /*if (item != null) {
            viewHolder.mixed.setText(String.valueOf(item.getMixed()));
            viewHolder.rural.setText(String.valueOf(item.getRural()));
            viewHolder.urban.setText(String.valueOf(item.getUrban()));
        }

        return view;
    }

    static class ViewHolder {
        @BindView(R.id.mixed)
        TextView mixed;
        @BindView(R.id.rural)
        TextView rural;
        @BindView(R.id.urban)
        TextView urban;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    } */
}

