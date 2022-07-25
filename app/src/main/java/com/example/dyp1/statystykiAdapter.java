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

class statystykiAdapter extends ArrayAdapter<Wprowadz_statystyki> {
    private static final String TAG = "statystykiAdapter";
    private Context mContext;
    int mResource;

    public statystykiAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Wprowadz_statystyki> objects) {
        super(context, resource, objects);
        //this.mContext = mContext;
        mContext = context;
        mResource = resource;


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        int id = getItem(position).getId();
        int id_kurier = getItem(position).getId_kurier();
        String data = getItem(position).getData();
        int ilosc_DOR = getItem(position).getIlosc_DOR();
        int przejechane_km = getItem(position).getPrzejechane_km();
        double paliwo_objetosc = getItem(position).getPaliwo_objetosc();
        double paliwo_koszt = getItem(position).getPaliwo_koszt();
        int ilosc_odb = getItem(position).getIlosc_odb();
        int ilosc_dni_odb = getItem(position).getIlosc_dni_odb();
        String kary_opis = getItem(position).getKary_opis();
        double kary_koszt = getItem(position).getKary_koszt();
        double wynagrodzenie_z_trasy_DOR = getItem(position).getWynagrodzenie_z_trasy_DOR();
        double wynagrodzenie_z_trasy_ODB = getItem(position).getWynagrodzenie_z_trasy_ODB();
        double wynagrodzenie_kuriera = getItem(position).getWynagrodzenie_kuriera();
        double zysk_z_trasy = getItem(position).getZysk_z_trasy();

        Wprowadz_statystyki wprowadz_statystyki = new Wprowadz_statystyki(kary_opis,data,paliwo_objetosc,paliwo_koszt,kary_koszt,wynagrodzenie_z_trasy_DOR,wynagrodzenie_z_trasy_ODB,
                wynagrodzenie_kuriera,zysk_z_trasy,id,id_kurier,ilosc_DOR,przejechane_km,ilosc_odb,ilosc_dni_odb);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource,parent,false);

        TextView tv_id_kurier = (TextView) convertView.findViewById(R.id.tv_id);
        TextView tv_data = (TextView) convertView.findViewById(R.id.tv_data);
        TextView tv_ilosc_DOR = (TextView) convertView.findViewById(R.id.tv_ilosc_DOR);
        TextView tv_przejechane_km = (TextView) convertView.findViewById(R.id.tv_przejechane_km);
        TextView tv_paliwo_objetosc = (TextView) convertView.findViewById(R.id.tv_paliwo_objetosc);
        TextView tv_paliwo_koszt = (TextView) convertView.findViewById(R.id.tv_paliwo_koszt);
        TextView tv_ilosc_odb = (TextView) convertView.findViewById(R.id.tv_ilosc_odb);
        TextView tv_ilosc_dni_odb = (TextView) convertView.findViewById(R.id.tv_ilosc_dni_odb);
        TextView tv_kary_opis = (TextView) convertView.findViewById(R.id.tv_kary_opis);
        TextView tv_kary_koszt = (TextView) convertView.findViewById(R.id.tv_kary_koszt);
        TextView tv_wynagrodzenie_z_trasy_DOR = (TextView) convertView.findViewById(R.id.tv_wynagrodzenie_z_trasy_DOR);
        TextView tv_wynagrodzenie_z_trasy_ODB = (TextView) convertView.findViewById(R.id.tv_wynagrodzenie_z_trasy_ODB);
        TextView tv_wynagrodzenie_kuriera = (TextView) convertView.findViewById(R.id.tv_wynagrodzenie_kuriera);
        TextView tv_zysk_z_trasy = (TextView) convertView.findViewById(R.id.tv_zysk_z_trasy);

        tv_id_kurier.setText("ID kuriera: " + String.valueOf(id_kurier));
        tv_data.setText("Data: " + data);
        tv_ilosc_DOR.setText("Ilość doręczonych paczek: " + String.valueOf(ilosc_DOR));
        tv_przejechane_km.setText("Przejechane kilometry: " + String.valueOf(przejechane_km));
        tv_paliwo_objetosc.setText("Zużyte palwio: " + String.valueOf(paliwo_objetosc));
        tv_paliwo_koszt.setText("Koszt paliwa: " + String.valueOf(paliwo_koszt));
        tv_ilosc_odb.setText("Ilość odebranych paczek: " + String.valueOf(ilosc_odb));
        tv_ilosc_dni_odb.setText("Ilość dni z odbiorami: " + String.valueOf(ilosc_dni_odb));
        tv_kary_opis.setText("Opis kar: " + kary_opis);
        tv_kary_koszt.setText("Koszt kar: " + String.valueOf(kary_koszt));
        tv_wynagrodzenie_z_trasy_DOR.setText("Przychód z doręczeń: " + String.valueOf(wynagrodzenie_z_trasy_DOR));
        tv_wynagrodzenie_z_trasy_ODB.setText("Przychód z odbiorów: " + String.valueOf(wynagrodzenie_z_trasy_ODB));
        tv_wynagrodzenie_kuriera.setText("Wynagrodzenie kuriera: " + String.valueOf(wynagrodzenie_kuriera));
        tv_zysk_z_trasy.setText("Dochód z trasy: " + String.valueOf(zysk_z_trasy));

        return convertView;
    }
}
