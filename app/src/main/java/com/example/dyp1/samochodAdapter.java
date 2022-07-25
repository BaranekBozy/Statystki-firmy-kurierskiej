package com.example.dyp1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

class samochodAdapter extends ArrayAdapter<Samochod> {
    private static final String TAG = "przychodyAdapter";
    private Context mContext;
    int mResource;
    public samochodAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Samochod> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        int id = getItem(position).getId();
        String marka = getItem(position).getMarka();
        String model = getItem(position).getModel();
        String numer_rejestracyjny = getItem(position).getNumer_rejestracyjny();
        double koszt = getItem(position).getKoszt();
        LayoutInflater inflater = LayoutInflater.from(mContext);

        convertView = inflater.inflate(mResource,parent,false);

        TextView tv_numer_rejestracyjny = (TextView) convertView.findViewById(R.id.tv_numer_rejestracyjny);
        TextView tv_marka = (TextView) convertView.findViewById(R.id.tv_marka);
        TextView tv_model = (TextView) convertView.findViewById(R.id.tv_model);
        TextView tv_koszt = (TextView) convertView.findViewById(R.id.tv_koszt);

        tv_numer_rejestracyjny.setText("Numer rejestracyjny: " + String.valueOf(numer_rejestracyjny));
        tv_marka.setText("Marka: " + String.valueOf(marka));
        tv_model.setText("Model: " + String.valueOf(model));
        tv_koszt.setText("koszt: " + String.valueOf(koszt));

        return convertView;
    }
}
