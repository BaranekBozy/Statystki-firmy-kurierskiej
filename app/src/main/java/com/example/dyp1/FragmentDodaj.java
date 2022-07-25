package com.example.dyp1;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

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
import com.muddzdev.styleabletoastlibrary.StyleableToast;

import java.util.HashMap;
import java.util.Map;

public class FragmentDodaj extends Fragment {
    public EditText wpr_id_kurier,wpr_data,wpr_DOR,wpr_KM,wpr_paliwo_obj,wpr_paliwo_koszt,wpr_ODB,wpr_ODB_dni,wpr_kary_opis,wpr_kary_koszt,wpr_wynagrodzenie_DOR,wpr_wynagrodzenie_ODB;
    public Button zatwierdz;
    public String str_wpr_id_kurier,str_wpr_data,str_wpr_data_format,str_wpr_DOR,str_wpr_KM,str_wpr_paliwo_obj,str_wpr_paliwo_koszt,str_wpr_ODB,str_wpr_ODB_dni,str_wpr_kary_opis,str_wpr_kary_koszt,str_wynagrodzenie_DOR,str_wynagrodzenie_ODB;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dodaj,container,false);
        wpr_id_kurier = (EditText)view.findViewById(R.id.wpr_id_kurier);
        wpr_data = (EditText)view.findViewById(R.id.wpr_data);
        new formatujDate(wpr_data);
        wpr_DOR = (EditText)view.findViewById(R.id.wpr_DOR);
        wpr_KM = (EditText)view.findViewById(R.id.wpr_KM);
        wpr_paliwo_obj = (EditText)view.findViewById(R.id.wpr_paliwo_obj);
        wpr_paliwo_koszt = (EditText)view.findViewById(R.id.wpr_paliwo_koszt);
        wpr_ODB = (EditText)view.findViewById(R.id.wpr_ODB);
        wpr_ODB_dni = (EditText)view.findViewById(R.id.wpr_ODB_dni);
        wpr_kary_opis = (EditText)view.findViewById(R.id.wpr_kary_opis);
        wpr_kary_koszt = (EditText)view.findViewById(R.id.wpr_kary_koszt);
        wpr_wynagrodzenie_DOR = (EditText)view.findViewById(R.id.wpr_wynagrodzenie_DOR);
        wpr_wynagrodzenie_ODB = (EditText)view.findViewById(R.id.wpr_wynagrodzenie_ODB);
        zatwierdz = (Button)view.findViewById(R.id.zatwierdz);
        RelativeLayout relativeLayout = view.findViewById(R.id.dodaj_layout);
        AnimationDrawable animationDrawable = (AnimationDrawable) relativeLayout.getBackground();
        animationDrawable.setEnterFadeDuration(1000);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();
        zatwierdz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String dodaj_url = "http://crisscross-speakers.000webhostapp.com/wprowadz_dane.php";
                str_wpr_id_kurier = wpr_id_kurier.getText().toString();
                str_wpr_data = wpr_data.getText().toString();
                //str_wpr_data_format = "\'" + str_wpr_data + "\'";
                //Toast.makeText(getActivity(),str_wpr_data,Toast.LENGTH_SHORT).show();
                str_wpr_DOR = wpr_DOR.getText().toString();
                str_wpr_KM = wpr_KM.getText().toString();
                str_wpr_paliwo_obj = wpr_paliwo_obj.getText().toString();
                str_wpr_paliwo_koszt = wpr_paliwo_koszt.getText().toString();
                str_wpr_ODB = wpr_ODB.getText().toString();
                str_wpr_ODB_dni = wpr_ODB_dni.getText().toString();
                str_wpr_kary_opis = wpr_kary_opis.getText().toString();
                str_wpr_kary_koszt = wpr_kary_koszt.getText().toString();
                str_wynagrodzenie_DOR = wpr_wynagrodzenie_DOR.getText().toString();
                str_wynagrodzenie_ODB = wpr_wynagrodzenie_ODB.getText().toString();
                if(!dodajValid())
                {
                    StyleableToast.makeText(getActivity(),"Wprowadzono niepoprawne dane",Toast.LENGTH_SHORT,R.style.ToastTheme).show();
                }
                else
                {
                    Thread threadDodaj = new Thread(new Runnable() {

                        @Override
                        public void run() {
                            try{
                                StringRequest stringRequest = new StringRequest(Request.Method.POST, dodaj_url, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        if (response.equals("Dane zostaly wprowadzone")) {
                                            StyleableToast.makeText(getContext(),"Udało się poprawnie wprowadzić dane",Toast.LENGTH_SHORT,R.style.ToastTheme).show();
                                            wpr_data.getText().clear();
                                            wpr_DOR.getText().clear();
                                            wpr_id_kurier.getText().clear();
                                            wpr_kary_koszt.getText().clear();
                                            wpr_kary_opis.getText().clear();
                                            wpr_KM.getText().clear();
                                            wpr_ODB.getText().clear();
                                            wpr_ODB_dni.getText().clear();
                                            wpr_paliwo_koszt.getText().clear();
                                            wpr_paliwo_obj.getText().clear();
                                            wpr_wynagrodzenie_DOR.getText().clear();
                                            wpr_wynagrodzenie_ODB.getText().clear();
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
                                        Map<String,String> dane_dodaj = new HashMap<>();
                                        dane_dodaj.put("id_kurier",str_wpr_id_kurier);
                                        dane_dodaj.put("data",str_wpr_data);
                                        dane_dodaj.put("DOR",str_wpr_DOR);
                                        dane_dodaj.put("KM",str_wpr_KM);
                                        dane_dodaj.put("OBJ",str_wpr_paliwo_obj);
                                        dane_dodaj.put("paliwo_koszt",str_wpr_paliwo_koszt);
                                        dane_dodaj.put("ODB",str_wpr_ODB);
                                        dane_dodaj.put("dni_ODB",str_wpr_ODB_dni);
                                        dane_dodaj.put("opis",str_wpr_kary_opis);
                                        dane_dodaj.put("kary_koszt",str_wpr_kary_koszt);
                                        dane_dodaj.put("wynagrodzenie_z_trasy_DOR",str_wynagrodzenie_DOR);
                                        dane_dodaj.put("wynagrodzenie_z_trasy_ODB",str_wynagrodzenie_ODB);
                                        return dane_dodaj;
                                    }
                                };
                                RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
                                requestQueue.add(stringRequest);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });

                    threadDodaj.start();
                }
            }
        });
        return view;
    }
    private boolean dodajValid() {
        if(!dateValid() || !idValid() || !dorValid() || !kmValid() || !paliwo_kosztValid() || !paliwo_objetoscValid() || !odb_dniValid() || !odbValid() || !kary_kosztValid() || !kary_opisValid() || !wynagrodzenie_dorValid() || !wynagrodzenie_odbValid() )
        {
            dateValid();
            idValid();
            dorValid();
            kmValid();
            paliwo_kosztValid();
            paliwo_objetoscValid();
            odb_dniValid();
            odbValid();
            kary_kosztValid();
            kary_opisValid();
            wynagrodzenie_dorValid();
            wynagrodzenie_odbValid();
            return false;
        }
        else
        {
            return true;
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
    private boolean idValid(){
        if(str_wpr_id_kurier.isEmpty() || !isNumeric(str_wpr_id_kurier))
        {
            wpr_id_kurier.setError("Niepoprawny identyfikator");
            return false;
        }else
        {
            wpr_id_kurier.setError(null);
            return true;
        }
    }
    private boolean dateValid(){
        if(str_wpr_data.isEmpty())
        {
            wpr_data.setError("Pole nie może być puste");
            return false;
        }else
        {
            wpr_data.setError(null);
            return true;
        }
    }
    private boolean dorValid(){
        if(str_wpr_DOR.isEmpty() || !isNumeric(str_wpr_DOR))
        {
            wpr_DOR.setError("Niepoprawne dane");
            return false;
        }else
        {
            wpr_DOR.setError(null);
            return true;
        }
    }
    private boolean kmValid(){
        if(str_wpr_KM.isEmpty() || !isNumeric(str_wpr_KM))
        {
            wpr_KM.setError("Niepoprawne dane");
            return false;
        }else
        {
            wpr_KM.setError(null);
            return true;
        }
    }
    private boolean paliwo_objetoscValid(){
        if(str_wpr_paliwo_obj.isEmpty() || !isNumeric(str_wpr_paliwo_obj))
        {
            wpr_paliwo_obj.setError("Niepoprawne dane");
            return false;
        }else
        {
            wpr_paliwo_obj.setError(null);
            return true;
        }
    }
    private boolean paliwo_kosztValid(){
        if(str_wpr_paliwo_koszt.isEmpty() || !isNumeric(str_wpr_paliwo_koszt))
        {
            wpr_paliwo_koszt.setError("Niepoprawne dane");
            return false;
        }else
        {
            wpr_kary_koszt.setError(null);
            return true;
        }
    }
    private boolean odbValid(){
        if(str_wpr_ODB.isEmpty() || !isNumeric(str_wpr_ODB))
        {
            wpr_ODB.setError("Niepoprawne dane");
            return false;
        }else
        {
            wpr_ODB.setError(null);
            return true;
        }
    }
    private boolean odb_dniValid(){
        if(str_wpr_ODB_dni.isEmpty() || !isNumeric(str_wpr_ODB_dni))
        {
            wpr_ODB_dni.setError("Niepoprawne dane");
            return false;
        }else
        {
            wpr_ODB_dni.setError(null);
            return true;
        }
    }
    private boolean kary_opisValid(){
        if(str_wpr_kary_opis.isEmpty())
        {
            wpr_kary_opis.setError("Pole nie może być puste");
            return false;
        }else
        {
            wpr_kary_opis.setError(null);
            return true;
        }
    }
    private boolean kary_kosztValid(){
        if(str_wpr_kary_koszt.isEmpty() || !isNumeric(str_wpr_kary_koszt))
        {
            wpr_kary_koszt.setError("Niepoprawne dane");
            return false;
        }else
        {
            wpr_kary_koszt.setError(null);
            return true;
        }
    }
    private boolean wynagrodzenie_dorValid(){
        if(str_wynagrodzenie_DOR.isEmpty() || !isNumeric(str_wynagrodzenie_DOR))
        {
            wpr_wynagrodzenie_DOR.setError("Niepoprawne dane");
            return false;
        }else
        {
            wpr_wynagrodzenie_DOR.setError(null);
            return true;
        }
    }
    private boolean wynagrodzenie_odbValid(){
        if(str_wynagrodzenie_ODB.isEmpty() || !isNumeric(str_wynagrodzenie_ODB))
        {
            wpr_wynagrodzenie_ODB.setError("Niepoprawne dane");
            return false;
        }else
        {
            wpr_wynagrodzenie_ODB.setError(null);
            return true;
        }
    }

}
