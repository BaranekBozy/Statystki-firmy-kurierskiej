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

class kosztaAdapter extends ArrayAdapter<Dodatkowe_koszta> {
    private static final String TAG = "kosztaAdapter";
    private Context mContext;
    int mResource;

    public kosztaAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Dodatkowe_koszta> objects) {
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
        double koszta = getItem(position).getKoszta();
        String data = getItem(position).getData();

        Dodatkowe_koszta dodatkowe_koszta = new Dodatkowe_koszta(id,id_kurier,opis,koszta,data);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource,parent,false);

        TextView tv_numer_kuriera_koszta = (TextView) convertView.findViewById(R.id.tv_numer_kuriera_koszta);
        TextView tv_opis_koszta = (TextView) convertView.findViewById(R.id.tv_opis_koszta);
        TextView tv_koszta = (TextView) convertView.findViewById(R.id.tv_koszta);
        TextView tv_data_koszta = (TextView) convertView.findViewById(R.id.tv_data_koszta);

        tv_numer_kuriera_koszta.setText("ID kuriera: " + String.valueOf(id_kurier));
        tv_opis_koszta.setText("Opis: " + String.valueOf(opis));
        tv_koszta.setText("Przychód: " + String.valueOf(koszta));
        tv_data_koszta.setText("Data: " + String.valueOf(data));

        return convertView;
    }
}
