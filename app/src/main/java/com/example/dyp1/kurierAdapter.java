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

class kurierAdapter extends ArrayAdapter<Kurier> {
    private static final String TAG = "przychodyAdapter";
    private Context mContext;
    int mResource;

    public kurierAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Kurier> objects) {
        super(context, resource, objects);
        //this.mContext = mContext;
        mContext = context;
        mResource = resource;


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        int id = getItem(position).getId();
        String imie = getItem(position).getImie();
        String nazwisko = getItem(position).getNazwisko();
        int numer_kuriera = getItem(position).getNumer_kuriera();
        int id_auto = getItem(position).getId_auto();
        double stawka_za_paczke = getItem(position).getStawka_za_paczke();
        double stawka_za_odbior = getItem(position).getStawka_za_odbior();
        int numer_kontaktowy = getItem(position).getNumer_kontaktowy();


        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource,parent,false);

        TextView tv_imie = (TextView) convertView.findViewById(R.id.tv_imie);
        TextView tv_nazwisko = (TextView) convertView.findViewById(R.id.tv_nazwisko);
        TextView tv_numer_kuriera = (TextView) convertView.findViewById(R.id.tv_numer_kuriera);
        TextView tv_id_auto = (TextView) convertView.findViewById(R.id.tv_id_auto);
        TextView tv_stawka_za_paczke = (TextView) convertView.findViewById(R.id.tv_stawka_za_paczke);
        TextView tv_stawka_za_odbior = (TextView) convertView.findViewById(R.id.tv_stawka_za_odbior);
        TextView tv_numer_kontaktowy = (TextView) convertView.findViewById(R.id.tv_numer_kontaktowy);

        tv_imie.setText("Imię: " + String.valueOf(imie));
        tv_nazwisko.setText("Nazwisko: " + String.valueOf(nazwisko));
        tv_numer_kuriera.setText("Numer kuriera: " + String.valueOf(numer_kuriera));
        tv_id_auto.setText("ID samochodu: " + String.valueOf(id_auto));
        tv_stawka_za_paczke.setText("Stawka za paczkę: " + String.valueOf(stawka_za_paczke));
        tv_stawka_za_odbior.setText("Stawka za odbiór: " + String.valueOf(stawka_za_odbior));
        tv_numer_kontaktowy.setText("Numer kontaktowy: " + String.valueOf(numer_kontaktowy));

        return convertView;
    }
}
