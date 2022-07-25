package com.example.dyp1;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.muddzdev.styleabletoastlibrary.StyleableToast;

import java.util.HashMap;
import java.util.Map;

public class FragmentZasoby extends Fragment {
    public Button zatwierdz;
    public EditText marka,model,rejestracja,koszt,imie,nazwisko,numer_kuriera,id_auto,stawka_za_paczke,stawka_za_odbior,numer_kontaktowy;
    public ToggleButton wybor;
    public String str_marka,str_model,str_rejestracja,str_koszt,str_imie,str_nazwisko,str_numer_kuriera,str_id_auto,str_stawka_za_paczke,str_stawka_za_odbior,str_numer_kontaktowy;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zasoby,container,false);
        marka = (EditText)view.findViewById(R.id.marka);
        model = (EditText)view.findViewById(R.id.model);
        rejestracja = (EditText)view.findViewById(R.id.rejestracja);
        koszt = (EditText)view.findViewById(R.id.koszt);
        imie = (EditText)view.findViewById(R.id.imie);
        nazwisko = (EditText)view.findViewById(R.id.nazwisko);
        numer_kontaktowy = (EditText)view.findViewById(R.id.numer_kontaktowy);
        numer_kuriera = (EditText)view.findViewById(R.id.numer_kuriera);
        id_auto = (EditText)view.findViewById(R.id.id_auto);
        stawka_za_paczke = (EditText)view.findViewById(R.id.stawka_za_paczke);
        stawka_za_odbior = (EditText)view.findViewById(R.id.stawka_za_odbior);
        wybor = (ToggleButton) view.findViewById(R.id.toggleButton);
        zatwierdz = (Button) view.findViewById(R.id.zatwierdz);
        visibility_manager();
        RelativeLayout relativeLayout = view.findViewById(R.id.zasoby_layout);
        AnimationDrawable animationDrawable = (AnimationDrawable) relativeLayout.getBackground();
        animationDrawable.setEnterFadeDuration(1000);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();
        wybor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                visibility_manager();

            }
        });
        zatwierdz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(wybor.isChecked()){
                    final String zasoby_auto_url = "http://crisscross-speakers.000webhostapp.com/wprowadz_dane_auta.php";
                    str_marka = marka.getText().toString();
                    str_model = model.getText().toString();
                    str_rejestracja = rejestracja.getText().toString();
                    str_koszt = koszt.getText().toString();
                    if(!zasoby_autoValid())
                    {
                        StyleableToast.makeText(getContext(),"Wprowadzono niepoprawne dane",Toast.LENGTH_SHORT,R.style.ToastTheme).show();
                    }
                    else
                    {
                        Thread threadZasobyAuto = new Thread(new Runnable() {

                            @Override
                            public void run() {
                                try{
                                    StringRequest stringRequest = new StringRequest(Request.Method.POST, zasoby_auto_url, new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            if (response.equals("Dane auta zostaly wprowadzone")) {
                                                StyleableToast.makeText(getContext(),"Udało się poprawnie wprowadzić dane",Toast.LENGTH_SHORT,R.style.ToastTheme).show();
                                                marka.getText().clear();
                                                model.getText().clear();
                                                rejestracja.getText().clear();
                                                koszt.getText().clear();
                                            }
                                            else if (response.equals("Nie udalo sie wprowadzic danych")){
                                                StyleableToast.makeText(getContext(),"Nie udało się wprowadzić danych",Toast.LENGTH_SHORT,R.style.ToastTheme).show();
                                            }
                                        }
                                    }, new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            StyleableToast.makeText(getContext(),error.toString().trim(),Toast.LENGTH_SHORT,R.style.ToastTheme).show();
                                        }
                                    })
                                    {
                                        @Override
                                        protected Map<String, String> getParams() throws AuthFailureError {
                                            Map<String,String> dane_zasoby_auto = new HashMap<>();
                                            dane_zasoby_auto.put("marka",str_marka);
                                            dane_zasoby_auto.put("model",str_model);
                                            dane_zasoby_auto.put("numer_rejestracyjny",str_rejestracja);
                                            dane_zasoby_auto.put("koszt",str_koszt);
                                            return dane_zasoby_auto;
                                        }
                                    };
                                    RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
                                    requestQueue.add(stringRequest);

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        threadZasobyAuto.start();
                    }
                    //String Type = "dodaj_auto";
                    //background.execute(Type,str_marka,str_model,str_rejestracja,str_koszt);
                }
                else{
                    final String zasoby_pracownik_url = "http://crisscross-speakers.000webhostapp.com/wprowadz_dane_kuriera.php";
                    str_imie = imie.getText().toString();
                    str_nazwisko = nazwisko.getText().toString();
                    str_numer_kontaktowy = numer_kontaktowy.getText().toString();
                    str_numer_kuriera = numer_kuriera.getText().toString();
                    str_id_auto = id_auto.getText().toString();
                    str_stawka_za_paczke = stawka_za_paczke.getText().toString();
                    str_stawka_za_odbior = stawka_za_odbior.getText().toString();
                    if(!zasoby_pracownikValid())
                    {
                        StyleableToast.makeText(getContext(),"Wprowadzono niepoprawne dane",Toast.LENGTH_SHORT,R.style.ToastTheme).show();
                    }
                    else
                    {
                        Thread threadZasobyPracownik = new Thread(new Runnable() {

                            @Override
                            public void run() {
                                try{
                                    StringRequest stringRequest = new StringRequest(Request.Method.POST, zasoby_pracownik_url, new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            if (response.equals("Dane pracownika zostaly wprowadzone")) {
                                                Toast.makeText(getContext(),"Udało się poprawnie wprowadzić dane",Toast.LENGTH_SHORT).show();
                                                imie.getText().clear();
                                                nazwisko.getText().clear();
                                                numer_kontaktowy.getText().clear();
                                                numer_kuriera.getText().clear();
                                                id_auto.getText().clear();
                                                stawka_za_paczke.getText().clear();
                                                stawka_za_odbior.getText().clear();
                                            }
                                            else if (response.equals("Nie udalo sie wprowadzic danych")){
                                                StyleableToast.makeText(getContext(),"Nie udało się wprowadzić danych",Toast.LENGTH_SHORT,R.style.ToastTheme).show();
                                            }
                                        }
                                    }, new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            StyleableToast.makeText(getContext(),error.toString().trim(),Toast.LENGTH_SHORT,R.style.ToastTheme).show();
                                        }
                                    })
                                    {
                                        @Override
                                        protected Map<String, String> getParams() throws AuthFailureError {
                                            Map<String,String> dane_zasoby_pracownik = new HashMap<>();
                                            dane_zasoby_pracownik.put("imie",str_imie);
                                            dane_zasoby_pracownik.put("nazwisko",str_nazwisko);
                                            dane_zasoby_pracownik.put("numer_kuriera",str_numer_kuriera);
                                            dane_zasoby_pracownik.put("id_auto",str_id_auto);
                                            dane_zasoby_pracownik.put("stawka_za_paczke",str_stawka_za_paczke);
                                            dane_zasoby_pracownik.put("stawka_za_odbior",str_stawka_za_odbior);
                                            dane_zasoby_pracownik.put("numer_kontaktowy",str_numer_kuriera);
                                            return dane_zasoby_pracownik;
                                        }
                                    };
                                    RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
                                    requestQueue.add(stringRequest);

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        threadZasobyPracownik.start();
                    }
                    //String Type = "dodaj_kuriera";
                    //background.execute(Type,str_imie,str_nazwisko,str_numer_kuriera,str_id_auto,str_stawka_za_paczke,str_stawka_za_odbior,str_numer_kontaktowy);

                }
            }
        });
    return view;
    }
    private boolean zasoby_pracownikValid() {
        if(!imieValid() || !nazwiskoValid() || !validatePhone() || !numerValid() || !id_autoValid() || !stawka_za_paczkeValid() || !stawka_za_odbiorValid())
        {

            imieValid();
            nazwiskoValid();
            validatePhone();
            numerValid();
            id_autoValid();
            stawka_za_odbiorValid();
            stawka_za_paczkeValid();


            return false;
        }
        else
        {
            return true;
        }
    }
    private boolean zasoby_autoValid() {
        if(!markaValid() || !modelValid() || !rejestracjaValid() || !kosztValid())
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    private boolean stawka_za_odbiorValid() {
        if(str_stawka_za_odbior.isEmpty() || !isNumericDouble(str_stawka_za_odbior))
        {
            stawka_za_odbior.setError("Niepoprawne dane");
            return false;
        }else
        {
            stawka_za_odbior.setError(null);
            return true;
        }
    }
    private boolean stawka_za_paczkeValid() {
        if(str_stawka_za_paczke.isEmpty() || !isNumericDouble(str_stawka_za_paczke))
        {
            stawka_za_paczke.setError("Niepoprawne dane");
            return false;
        }else
        {
            stawka_za_paczke.setError(null);
            return true;
        }
    }
    private boolean id_autoValid() {
        if(str_id_auto.isEmpty() || !isNumeric(str_id_auto))
        {
            id_auto.setError("Niepoprawne dane");
            return false;
        }else
        {
            id_auto.setError(null);
            return true;
        }
    }
    private boolean numerValid() {
        if(str_numer_kuriera.isEmpty() || !isNumeric(str_numer_kuriera))
        {
            numer_kuriera.setError("Niepoprawne dane");
            return false;
        }else
        {
            numer_kuriera.setError(null);
            return true;
        }
    }
    private boolean validatePhone() {
        String telInput = numer_kontaktowy.getText().toString().trim();
        if (telInput.isEmpty()) {
            numer_kontaktowy.setError("Pole nie może być puste");
            return false;
        } else if (!Patterns.PHONE.matcher(telInput).matches()) {
            numer_kontaktowy.setError("Niepoprawny numer telefonu");
            return false;
        } else {
            numer_kontaktowy.setError(null);
            return true;
        }
    }
    private boolean nazwiskoValid() {
        if(str_nazwisko.isEmpty())
        {
            nazwisko.setError("Pole nie może być puste");
            return false;
        }else
        {
            nazwisko.setError(null);
            return true;
        }
    }
    private boolean imieValid() {
        if(str_imie.isEmpty())
        {
            imie.setError("Pole nie może być puste");
            return false;
        }else
        {
            imie.setError(null);
            return true;
        }
    }
    private boolean kosztValid() {
        if(str_koszt.isEmpty() || !isNumeric(str_koszt))
        {
            koszt.setError("Niepoprawne dane");
            return false;
        }else
        {
            koszt.setError(null);
            return true;
        }
    }
    private boolean rejestracjaValid() {
        if(str_rejestracja.isEmpty())
        {
            rejestracja.setError("Pole nie może być puste");
            return false;
        }else
        {
            rejestracja.setError(null);
            return true;
        }
    }
    private boolean markaValid() {
        if(str_marka.isEmpty())
        {
            marka.setError("Pole nie może być puste");
            return false;
        }else
        {
            marka.setError(null);
            return true;
        }
    }
    private boolean modelValid() {
        if(str_model.isEmpty())
        {
            model.setError("Pole nie może być puste");
            return false;
        }else
        {
            model.setError(null);
            return true;
        }
    }
    private void visibility_manager(){
        if(wybor.isChecked()){
            marka.setVisibility(View.VISIBLE);
            model.setVisibility(View.VISIBLE);
            rejestracja.setVisibility(View.VISIBLE);
            koszt.setVisibility(View.VISIBLE);
            imie.setVisibility(View.INVISIBLE);
            nazwisko.setVisibility(View.INVISIBLE);
            id_auto.setVisibility(View.INVISIBLE);
            numer_kontaktowy.setVisibility(View.INVISIBLE);
            numer_kuriera.setVisibility(View.INVISIBLE);
            id_auto.setVisibility(View.INVISIBLE);
            stawka_za_paczke.setVisibility(View.INVISIBLE);
            stawka_za_odbior.setVisibility(View.INVISIBLE);

        }else{
            marka.setVisibility(View.INVISIBLE);
            model.setVisibility(View.INVISIBLE);
            rejestracja.setVisibility(View.INVISIBLE);
            koszt.setVisibility(View.INVISIBLE);
            imie.setVisibility(View.VISIBLE);
            nazwisko.setVisibility(View.VISIBLE);
            id_auto.setVisibility(View.VISIBLE);
            numer_kontaktowy.setVisibility(View.VISIBLE);
            numer_kuriera.setVisibility(View.VISIBLE);
            id_auto.setVisibility(View.VISIBLE);
            stawka_za_paczke.setVisibility(View.VISIBLE);
            stawka_za_odbior.setVisibility(View.VISIBLE);

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
    public static boolean isNumericDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}
