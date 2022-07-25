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

class przychodyAdapter extends ArrayAdapter<Dodatkowe_przychody> {
    private static final String TAG = "przychodyAdapter";
    private Context mContext;
    int mResource;

    public przychodyAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Dodatkowe_przychody> objects) {
        super(context, resource, objects);
        //this.mContext = mContext;
        mContext = context;
        mResource = resource;


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        int id = getItem(position).getId();
        int id_kurier = getItem(position).getNumer_kuriera();
        String opis = getItem(position).getOpis();
        double przychod = getItem(position).getPrzychod();
        String data = getItem(position).getData();

        Dodatkowe_przychody dodatkowe_przychody = new Dodatkowe_przychody(id,id_kurier,opis,przychod,data);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource,parent,false);

        TextView tv_numer_kuriera_przychod = (TextView) convertView.findViewById(R.id.tv_numer_kuriera_przychod);
        TextView tv_opis_przychod = (TextView) convertView.findViewById(R.id.tv_opis_przychod);
        TextView tv_przychod = (TextView) convertView.findViewById(R.id.tv_przychod);
        TextView tv_data_przychod = (TextView) convertView.findViewById(R.id.tv_data_przychod);

        tv_numer_kuriera_przychod.setText("ID kuriera: " + String.valueOf(id_kurier));
        tv_opis_przychod.setText("Opis: " + String.valueOf(opis));
        tv_przychod.setText("Przychód: " + String.valueOf(przychod));
        tv_data_przychod.setText("Data: " + String.valueOf(data));

        return convertView;
    }
}
