package com.example.dyp1;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.muddzdev.styleabletoastlibrary.StyleableToast;

import java.util.HashMap;
import java.util.Map;

public class FragmentDodatkowe extends Fragment {
    public Button zatwierdz;
    public EditText nr_kurier,Opis,Przychod_koszt,data;
    public ToggleButton wybor;
    public String str_nr_kurier,str_Opis,str_Przychod_koszt,str_data,operacja = "";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dodatkowe,container,false);
        nr_kurier = (EditText)view.findViewById(R.id.nr_kurier);
        Opis = (EditText)view.findViewById(R.id.Opis);
        Przychod_koszt = (EditText)view.findViewById(R.id.Przychod_koszt);
        data = (EditText)view.findViewById(R.id.data);
        new formatujDate(data);
        wybor = (ToggleButton) view.findViewById(R.id.toggleButton);
        zatwierdz = (Button) view.findViewById(R.id.zatwierdz);
        RelativeLayout relativeLayout = view.findViewById(R.id.dodatkowe_layout);
        AnimationDrawable animationDrawable = (AnimationDrawable) relativeLayout.getBackground();
        animationDrawable.setEnterFadeDuration(1000);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();
        zatwierdz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    final String dodatkowe_url = "http://crisscross-speakers.000webhostapp.com/dodatkowe.php";
                    str_nr_kurier = nr_kurier.getText().toString();
                    str_Opis = Opis.getText().toString();
                    str_Przychod_koszt = Przychod_koszt.getText().toString();
                    str_data = data.getText().toString();
                    if(!dodatkoweValid())
                    {
                        StyleableToast.makeText(getContext(),"Wprowadzono niepoprawne dane",Toast.LENGTH_SHORT,R.style.ToastTheme).show();
                    }
                    else
                    {
                        Thread threadDodatkowePrzychody = new Thread(new Runnable() {

                            @Override
                            public void run() {
                                try{
                                    StringRequest stringRequest = new StringRequest(Request.Method.POST, dodatkowe_url, new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            if (response.equals("Dane zostaly wprowadzone")) {
                                                StyleableToast.makeText(getContext(),"Udało się poprawnie wprowadzić dane",Toast.LENGTH_SHORT,R.style.ToastTheme).show();
                                                nr_kurier.getText().clear();
                                                Opis.getText().clear();
                                                Przychod_koszt.getText().clear();
                                                data.getText().clear();
                                                operacja = "";
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
                                            Map<String,String> dane_zasoby_przychod = new HashMap<>();
                                            dane_zasoby_przychod.put("nr_kurier",str_nr_kurier);
                                            dane_zasoby_przychod.put("Opis",str_Opis);
                                            dane_zasoby_przychod.put("Przychod_koszt",str_Przychod_koszt);
                                            dane_zasoby_przychod.put("data",str_data);
                                            if(wybor.isChecked()) {
                                            operacja = "przychod";
                                            }
                                            else
                                            {
                                                operacja = "koszta";
                                            }
                                            dane_zasoby_przychod.put("operacja",operacja);
                                            return dane_zasoby_przychod;
                                        }
                                    };
                                    RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
                                    requestQueue.add(stringRequest);

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        threadDodatkowePrzychody.start();
                    }
            }
        });

        return view;
    }
    private boolean dodatkoweValid(){
        if(!opisValid() || !nr_kurieraValid() || !przychod_kosztValid() || !dataValid())
        {
            opisValid();
            nr_kurieraValid();
            przychod_kosztValid();
            dataValid();
            return false;
        }
        else
        {
            return true;
        }
    }
    private boolean opisValid() {
        if(str_Opis.isEmpty())
        {
            Opis.setError("Pole nie może być puste");
            return false;
        }else
        {
            Opis.setError(null);
            return true;
        }
    }
    private boolean nr_kurieraValid() {
        if(str_nr_kurier.isEmpty() || !isNumeric(str_nr_kurier))
        {
            nr_kurier.setError("Niepoprawne dane");
            return false;
        }else
        {
            nr_kurier.setError(null);
            return true;
        }
    }
    private boolean przychod_kosztValid() {
        if(str_Przychod_koszt.isEmpty() || !isNumericDouble(str_Przychod_koszt))
        {
            Przychod_koszt.setError("Niepoprawne dane");
            return false;
        }else
        {
            Przychod_koszt.setError(null);
            return true;
        }
    }
    private boolean dataValid(){
        if(str_data.isEmpty())
        {
            data.setError("Pole nie może być puste");
            return false;
        }else
        {
            data.setError(null);
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
    public static boolean isNumericDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

}
