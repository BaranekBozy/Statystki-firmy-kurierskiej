package com.example.dyp1;

import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;


import android.os.AsyncTask;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.muddzdev.styleabletoastlibrary.StyleableToast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FragmentStaty extends Fragment implements AdapterView.OnItemSelectedListener
{ @Nullable
    public ListView listView;
    String str_kurier_wybor,str_data_od,str_data_do;
    public EditText kurier_wybor,data_od,data_do;
    public Spinner wyswietl;
    public RequestQueue requestQueue;
    public String text;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_staty,container,false);
        listView = (ListView) view.findViewById(R.id.listView);
        requestQueue = Volley.newRequestQueue(getActivity());
        kurier_wybor = (EditText) view.findViewById(R.id.ktory_kurier);
        data_od = (EditText) view.findViewById(R.id.data_od);
        new formatujDate(data_od);
        data_do = (EditText) view.findViewById(R.id.data_do);
        new formatujDate(data_do);
        wyswietl = (Spinner) view.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),R.array.statystyki_array, R.layout.custom_spinner);
        adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown);
        wyswietl.setAdapter(adapter);
        wyswietl.setOnItemSelectedListener(this);
        RelativeLayout relativeLayout = view.findViewById(R.id.staty_layout);
        AnimationDrawable animationDrawable = (AnimationDrawable) relativeLayout.getBackground();
        animationDrawable.setEnterFadeDuration(1000);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();

        return view;
    }
    private boolean idValid(){
        if(str_kurier_wybor.isEmpty())
        {
            return true;

        }
        else
        {
            if(!isNumeric(str_kurier_wybor))
            {
                kurier_wybor.setError("Niepoprawny identyfikator");
                return false;
            }else
            {
                kurier_wybor.setError(null);
                return true;
            }
        }
    }
    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
    public void StatystykiTrasy(String response){
        try{
            ArrayList<String> arrayList=new ArrayList<>();
            Log.i("tagconvertstr", "["+response+"]");
            ArrayList<Wprowadz_statystyki> statystyki_list = new ArrayList<>();
            JSONArray array = new JSONArray(response);
            for(int i = 0; i < array.length();i++){
                JSONObject object = array.getJSONObject(i);
                arrayList.add(object.getString("id"));
                arrayList.add(object.getString("id_kurier"));
                arrayList.add(object.getString("data"));
                arrayList.add(object.getString("ilosc_DOR"));
                arrayList.add(object.getString("przejechane_km"));
                arrayList.add(object.getString("paliwo_objetosc"));
                arrayList.add(object.getString("paliwo_koszt"));
                arrayList.add(object.getString("ilosc_odb"));
                arrayList.add(object.getString("ilosc_dni_odb"));
                arrayList.add(object.getString("kary_opis"));
                arrayList.add(object.getString("kary_koszt"));
                arrayList.add(object.getString("wynagrodzenie_z_trasy_DOR"));
                arrayList.add(object.getString("wynagrodzenie_z_trasy_ODB"));
                arrayList.add(object.getString("wynagrodzenie_kuriera"));
                arrayList.add(object.getString("zysk_z_trasy"));
                Wprowadz_statystyki wprowadz_statystyki = new Wprowadz_statystyki(object.getString("kary_opis"),object.getString("data"),object.getDouble("paliwo_objetosc"),
                        object.getDouble("paliwo_koszt"), object.getDouble("kary_koszt"),object.getDouble("wynagrodzenie_z_trasy_DOR"),object.getDouble("wynagrodzenie_z_trasy_ODB"),
                        object.getDouble("wynagrodzenie_kuriera"),object.getDouble("zysk_z_trasy"), object.getInt("id"),object.getInt("id_kurier"),
                        object.getInt("ilosc_DOR"),object.getInt("przejechane_km"),object.getInt("ilosc_odb"), object.getInt("ilosc_dni_odb"));
                statystyki_list.add(wprowadz_statystyki);
            }
            Log.i("tagconvertstr", "["+statystyki_list+"]");
            statystykiAdapter adapter = new statystykiAdapter(getContext(),R.layout.statyadapter,statystyki_list);
            listView.setAdapter(adapter);
        }
        catch (Exception e)
        {
            Toast.makeText(getContext(),":P",Toast.LENGTH_SHORT).show();
            Log.e("MYAPP", "exception", e);
        }
}
    public void DodatkowePrzychody(String response){
        try{
            ArrayList<String> arrayList=new ArrayList<>();
            Log.i("tagconvertstr", "["+response+"]");
            ArrayList<Dodatkowe_przychody> przychody_list = new ArrayList<>();
            JSONArray array = new JSONArray(response);
            for(int i = 0; i < array.length();i++){
                JSONObject object = array.getJSONObject(i);
                arrayList.add(object.getString("id"));
                arrayList.add(object.getString("numer_kuriera"));
                arrayList.add(object.getString("opis"));
                arrayList.add(object.getString("przychod"));
                arrayList.add(object.getString("data"));
                Dodatkowe_przychody dodatkowe_przychody = new Dodatkowe_przychody(object.getInt("id"),object.getInt("numer_kuriera"),object.getString("opis"),
                        object.getDouble("przychod"), object.getString("data"));
                przychody_list.add(dodatkowe_przychody);

            }
            Log.i("tagconvertstr", "["+przychody_list+"]");
            przychodyAdapter adapter = new przychodyAdapter(getContext(),R.layout.przychodadapter,przychody_list);
            listView.setAdapter(adapter);
        }
        catch (Exception e)
        {
            Toast.makeText(getContext(),":P",Toast.LENGTH_SHORT).show();
            Log.e("MYAPP", "exception", e);
        }
    }
    public void DodatkoweKoszta(String response){
        try{
            ArrayList<String> arrayList=new ArrayList<>();
            Log.i("tagconvertstr", "["+response+"]");
            ArrayList<Dodatkowe_koszta> koszta_list = new ArrayList<>();
            JSONArray array = new JSONArray(response);
            for(int i = 0; i < array.length();i++){
                JSONObject object = array.getJSONObject(i);
                arrayList.add(object.getString("id"));
                arrayList.add(object.getString("numer_kuriera"));
                arrayList.add(object.getString("opis"));
                arrayList.add(object.getString("koszt"));
                arrayList.add(object.getString("data"));
                Dodatkowe_koszta dodatkowe_koszta = new Dodatkowe_koszta(object.getInt("id"),object.getInt("numer_kuriera"),object.getString("opis"),
                        object.getDouble("koszt"), object.getString("data"));
                koszta_list.add(dodatkowe_koszta);

            }
            Log.i("tagconvertstr", "["+koszta_list+"]");
            kosztaAdapter adapter = new kosztaAdapter(getContext(),R.layout.kosztaadapter,koszta_list);
            listView.setAdapter(adapter);
        }
        catch (Exception e)
        {
            Toast.makeText(getContext(),":P",Toast.LENGTH_SHORT).show();
            Log.e("MYAPP", "exception", e);
        }
    }
    public void Samochody(String response){
        try{

            Log.i("tagconvertstr", "["+response+"]");
            ArrayList<Samochod> samochod_list = new ArrayList<>();
            JSONArray array = new JSONArray(response);
            for(int i = 0; i < array.length();i++){
                JSONObject object = array.getJSONObject(i);
                Samochod samochod = new Samochod(object.getInt("id"),object.getString("marka"),object.getString("model"),
                        object.getString("numer_rejestracyjny"), object.getDouble("koszt"));
                samochod_list.add(samochod);
            }
            Log.i("tagconvertstr", "["+samochod_list+"]");
            samochodAdapter adapter = new samochodAdapter(getContext(),R.layout.samochodadapter,samochod_list);
            listView.setAdapter(adapter);
        }
        catch (Exception e)
        {
            Log.e("MYAPP", "exception", e);
        }
    }
    public void Kurierzy(String response){
        try{
            ArrayList<String> arrayList=new ArrayList<>();
            Log.i("tagconvertstr", "["+response+"]");
            ArrayList<Kurier> kurier_list = new ArrayList<>();
            JSONArray array = new JSONArray(response);
            for(int i = 0; i < array.length();i++){
                JSONObject object = array.getJSONObject(i);
                arrayList.add(object.getString("id"));
                arrayList.add(object.getString("imie"));
                arrayList.add(object.getString("nazwisko"));
                arrayList.add(object.getString("numer_kuriera"));
                arrayList.add(object.getString("id_auto"));
                arrayList.add(object.getString("stawka_za_paczke"));
                arrayList.add(object.getString("stawka_za_odbior"));
                arrayList.add(object.getString("numer_kontaktowy"));

                Kurier kurier = new Kurier(object.getInt("id"),object.getString("imie"),object.getString("nazwisko"),
                        object.getInt("numer_kuriera"), object.getInt("id_auto"),object.getDouble("stawka_za_paczke"),object.getDouble("stawka_za_odbior"),
                        object.getInt("numer_kontaktowy"));
                kurier_list.add(kurier);

            }
            Log.i("tagconvertstr", "["+kurier_list+"]");
            kurierAdapter adapter = new kurierAdapter(getContext(),R.layout.kurieradapter,kurier_list);
            listView.setAdapter(adapter);
        }
        catch (Exception e)
        {
            Toast.makeText(getContext(),":P",Toast.LENGTH_SHORT).show();
            Log.e("MYAPP", "exception", e);
        }
    }
    public void Podsumowanie(String response){
        try{
            ArrayList<String> arrayList=new ArrayList<>();
            Log.i("tagconvertstr", "["+response+"]");
            ArrayList<Podsumowanie> podsumowanie_list = new ArrayList<>();
            JSONArray array = new JSONArray(response);
            Log.i("tagconvertstr", "["+array+"]");
            JSONObject object1 = array.getJSONObject(0);
            arrayList.add(object1.getString("SUM(zysk_z_trasy)"));
            JSONObject object2 = array.getJSONObject(1);
            arrayList.add(object2.getString("SUM(paliwo_objetosc)"));
            JSONObject object3 = array.getJSONObject(2);
            arrayList.add(object3.getString("SUM(paliwo_koszt)"));
            JSONObject object4 = array.getJSONObject(3);
            arrayList.add(object4.getString("SUM(ilosc_DOR)"));
            JSONObject object5 = array.getJSONObject(4);
            arrayList.add(object5.getString("SUM(ilosc_ODB)"));
            JSONObject object6 = array.getJSONObject(5);
            arrayList.add(object6.getString("SUM(kary_koszt)"));
            JSONObject object7 = array.getJSONObject(6);
            arrayList.add(object7.getString("SUM(przejechane_km)"));
            JSONObject object8 = array.getJSONObject(7);
            arrayList.add(object8.getString("ROUND(SUM(paliwo_objetosc)/SUM(przejechane_km)*100,2)"));
            JSONObject object9 = array.getJSONObject(8);
            arrayList.add(object9.getString("SUM(koszt)"));
            JSONObject object10 = array.getJSONObject(9);
            arrayList.add(object10.getString("SUM(przychod)"));
            JSONObject object11 = array.getJSONObject(10);
            arrayList.add(object11.getString("SUM(wynagrodzenie_kuriera)"));
            JSONObject object12 = array.getJSONObject(11);
            arrayList.add(object12.getString("SUM(koszt_auta)"));
            JSONObject object13 = array.getJSONObject(12);
            arrayList.add(object13.getString("SUM(wynagrodzenie_z_trasy_DOR)"));
            JSONObject object14 = array.getJSONObject(13);
            arrayList.add(object14.getString("SUM(wynagrodzenie_z_trasy_ODB)"));


            Podsumowanie podsumowanie = new Podsumowanie(object1.getDouble("SUM(zysk_z_trasy)"),object2.getDouble("SUM(paliwo_objetosc)"),object3.getDouble("SUM(paliwo_koszt)"),
                    object4.getDouble("SUM(ilosc_DOR)"), object5.getDouble("SUM(ilosc_ODB)"),object6.getDouble("SUM(kary_koszt)"),object7.getDouble("SUM(przejechane_km)"),
                    object8.getDouble("ROUND(SUM(paliwo_objetosc)/SUM(przejechane_km)*100,2)"),object9.getDouble("SUM(koszt)"),object10.getDouble("SUM(przychod)"),object11.getDouble("SUM(wynagrodzenie_kuriera)"),
                    object12.getDouble("SUM(koszt_auta)"),object13.getDouble("SUM(wynagrodzenie_z_trasy_DOR)"),object14.getDouble("SUM(wynagrodzenie_z_trasy_ODB)"));
            podsumowanie_list.add(podsumowanie);

            Log.i("tagconvertstr", "["+podsumowanie_list+"]");
            podsumowanieAdapter adapter = new podsumowanieAdapter(getContext(),R.layout.podsumowanieadapter,podsumowanie_list);
            listView.setAdapter(adapter);
        }
        catch (Exception e)
        {
            StyleableToast.makeText(getContext(),"Wprowadzono niepoprawne dane",Toast.LENGTH_SHORT,R.style.ToastTheme).show();
            Log.e("MYAPP", "exception", e);
        }
    }
    private void SendData(final String url) {
        str_kurier_wybor = kurier_wybor.getText().toString();
        str_data_od = data_od.getText().toString();
        str_data_do = data_do.getText().toString();
        Thread threadSendData = new Thread(new Runnable() {
                @Override
                public void run() {
                    try
                    {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if(url.equals("http://crisscross-speakers.000webhostapp.com/trasa.php")) {
                                StatystykiTrasy(response);
                            }else if(url.equals("http://crisscross-speakers.000webhostapp.com/przychody.php")){
                                DodatkowePrzychody(response);
                            }else if(url.equals("http://crisscross-speakers.000webhostapp.com/koszta.php")){
                                DodatkoweKoszta(response);
                            }else if(url.equals("http://crisscross-speakers.000webhostapp.com/samochody.php")){
                                Samochody(response);
                            }else if(url.equals("http://crisscross-speakers.000webhostapp.com/kurierzy.php")){
                                Kurierzy(response);
                            }else if(url.equals("http://crisscross-speakers.000webhostapp.com/statystyki_trasy.php")){
                                Podsumowanie(response);
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            StyleableToast.makeText(getContext(), error.toString().trim(), Toast.LENGTH_SHORT, R.style.ToastTheme).show();
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String> dane_dodaj = new HashMap<>();
                            dane_dodaj.put("kurier_wybor",str_kurier_wybor);
                            dane_dodaj.put("data_od",str_data_od);
                            dane_dodaj.put("data_do",str_data_do);
                            return dane_dodaj;
                        }
                    };
                        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
                        requestQueue.add(stringRequest);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                };
            });
            threadSendData.start();
        }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();

            if(text.equals("Dane"))
            {
              StyleableToast.makeText(getContext(),"Wybierz co chcesz wyświetlić",Toast.LENGTH_LONG,R.style.ToastTheme).show();
            }
            else if (text.equals("Podsumowanie miesiąca")) {
                SendData("http://crisscross-speakers.000webhostapp.com/trasa.php");
            } else if (text.equals("Dodatkowe przychody")) {
                SendData("http://crisscross-speakers.000webhostapp.com/przychody.php");
            } else if (text.equals("Dodatkowe koszta")) {
                SendData("http://crisscross-speakers.000webhostapp.com/koszta.php");
            } else if (text.equals("Samochody")) {
                SendData("http://crisscross-speakers.000webhostapp.com/samochody.php");
            } else if (text.equals("Kurierzy")) {
                SendData("http://crisscross-speakers.000webhostapp.com/kurierzy.php");
            } else if(text.equals("Statystyki trasy")){
                SendData("http://crisscross-speakers.000webhostapp.com/statystyki_trasy.php");
            }
        }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}

