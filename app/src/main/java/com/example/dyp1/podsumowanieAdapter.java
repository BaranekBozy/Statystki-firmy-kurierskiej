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

class podsumowanieAdapter extends ArrayAdapter<Podsumowanie> {
    private static final String TAG = "przychodyAdapter";
    private Context mContext;
    int mResource;

    public podsumowanieAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Podsumowanie> objects) {
        super(context, resource, objects);
        //this.mContext = mContext;
        mContext = context;
        mResource = resource;


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        double sum_zysk_z_trasy = getItem(position).getSum_zysk_z_trasy();
        double sum_paliwo_objetosc = getItem(position).getSum_paliwo_objetosc();
        double sum_paliwo_koszt = getItem(position).getSum_paliwo_koszt();
        double sum_ilosc_dor = getItem(position).getSum_ilosc_dor();
        double sum_ilosc_odb = getItem(position).getSum_ilosc_odb();
        double sum_kary_koszt = getItem(position).getSum_kary_koszt();
        double sum_przejechane_km = getItem(position).getSum_przejechane_km();
        double round_srednie_spalanie = getItem(position).getRound_srednie_spalanie();
        double sum_dodatkowe_koszta = getItem(position).getSum_dodatkowe_koszta();
        double sum_dodatkowe_przychody = getItem(position).getSum_dodatkowe_przychody();
        double sum_wynagrodzenia_kurierow = getItem(position).getSum_wynagrodzenia_kurierow();
        double sum_koszt_auta = getItem(position).getSum_koszt_auta();
        double sum_wynagrodzenie_z_trasy_DOR = getItem(position).getSum_wynagrodzenie_z_trasy_DOR();
        double sum_wynagrodzenie_z_trasy_ODB = getItem(position).getSum_wynagrodzenie_z_trasy_ODB();

        Podsumowanie podsumowanie = new Podsumowanie(sum_zysk_z_trasy,sum_paliwo_objetosc,sum_paliwo_koszt,sum_ilosc_dor,sum_ilosc_odb,sum_kary_koszt,
                sum_przejechane_km,round_srednie_spalanie,sum_dodatkowe_koszta,sum_dodatkowe_przychody,sum_wynagrodzenia_kurierow,sum_koszt_auta,sum_wynagrodzenie_z_trasy_DOR,sum_wynagrodzenie_z_trasy_ODB);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource,parent,false);

        TextView tv_sum_zysk_z_trasy = (TextView) convertView.findViewById(R.id.tv_sum_zysk_z_trasy);
        TextView tv_sum_paliwo_objetosc = (TextView) convertView.findViewById(R.id.tv_sum_paliwo_objetosc);
        TextView tv_sum_paliwo_koszt = (TextView) convertView.findViewById(R.id.tv_sum_paliwo_koszt);
        TextView tv_sum_ilosc_dor = (TextView) convertView.findViewById(R.id.tv_sum_ilosc_dor);
        TextView tv_sum_ilosc_odb = (TextView) convertView.findViewById(R.id.tv_sum_ilosc_odb);
        TextView tv_sum_kary_koszt = (TextView) convertView.findViewById(R.id.tv_sum_kary_koszt);
        TextView tv_sum_przejechane_km = (TextView) convertView.findViewById(R.id.tv_sum_przejechane_km);
        TextView tv_round_srednie_spalanie = (TextView) convertView.findViewById(R.id.tv_round_srednie_spalanie);
        TextView tv_sum_dodatkowe_koszta = (TextView) convertView.findViewById(R.id.tv_sum_dodatkowe_koszta);
        TextView tv_sum_dodatkowe_przychody = (TextView) convertView.findViewById(R.id.tv_sum_dodatkowe_przychody);
        TextView tv_sum_wynagrodzenia_kurierow = (TextView) convertView.findViewById(R.id.tv_sum_wynagrodzenia_kurierow);
        TextView tv_sum_koszt_auta = (TextView) convertView.findViewById(R.id.tv_sum_koszt_auta);
        TextView tv_sum_wynagrodzenie_z_trasy_DOR = (TextView) convertView.findViewById(R.id.tv_sum_wynagrodzenie_z_trasy_DOR);
        TextView tv_sum_wynagrodzenie_z_trasy_ODB = (TextView) convertView.findViewById(R.id.tv_sum_wynagrodzenie_z_trasy_ODB);

        tv_sum_zysk_z_trasy.setText("Zysk z trasy: " + String.valueOf(sum_zysk_z_trasy));
        tv_sum_paliwo_objetosc.setText("Paliwo(L): " + String.valueOf(sum_paliwo_objetosc));
        tv_sum_paliwo_koszt.setText("Paliwo(PLN): " + String.valueOf(sum_paliwo_koszt));
        tv_sum_ilosc_dor.setText("Ilość doręczonych przesyłek: " + String.valueOf(sum_ilosc_dor));
        tv_sum_ilosc_odb.setText("Ilość odebranych przesyłek: " + String.valueOf(sum_ilosc_odb));
        tv_sum_kary_koszt.setText("Koszt kar: " + String.valueOf(sum_kary_koszt));
        tv_sum_przejechane_km.setText("Przejechane KM: " + String.valueOf(sum_przejechane_km));
        tv_round_srednie_spalanie.setText("Srenie spalanie: " + String.valueOf(round_srednie_spalanie));
        tv_sum_dodatkowe_koszta.setText("Dodatkowe koszta: " + String.valueOf(sum_dodatkowe_koszta));
        tv_sum_dodatkowe_przychody.setText("Dodatkowe przychody: " + String.valueOf(sum_dodatkowe_przychody));
        tv_sum_wynagrodzenia_kurierow.setText("Wynagrodzenie kurierów: " + String.valueOf(sum_wynagrodzenia_kurierow));
        tv_sum_koszt_auta.setText("Koszt aut: " + String.valueOf(sum_koszt_auta));
        tv_sum_wynagrodzenie_z_trasy_DOR.setText("Przychód z doręczonych paczek: " + String.valueOf(sum_wynagrodzenie_z_trasy_DOR));
        tv_sum_wynagrodzenie_z_trasy_ODB.setText("Przychód z odebranych paczek: " + String.valueOf(sum_wynagrodzenie_z_trasy_ODB));

        return convertView;
    }
}
