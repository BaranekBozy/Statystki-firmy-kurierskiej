package com.example.dyp1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;
import com.muddzdev.styleabletoastlibrary.StyleableToast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class zaloguj extends AppCompatActivity {
    public TextView zarejestruj;
    public Button zaloguj;
    public EditText login,haslo;
    public String Pass,Log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zaloguj);
        zaloguj = (Button)findViewById(R.id.button2);
        zarejestruj = (TextView)findViewById(R.id.textView11);
        login = (EditText)findViewById(R.id.editText2);
        haslo = (EditText)findViewById(R.id.editText3);
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        zarejestruj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(zaloguj.this, zarejestruj.class);
                startActivity(i2);
            }
        });
zaloguj.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if(networkInfo == null && !networkInfo.isConnected())
        {
            StyleableToast.makeText(zaloguj.this,"Nie ma połączenia z internetem",Toast.LENGTH_SHORT,R.style.ToastTheme).show();
        }
        Log = login.getText().toString().trim();
        Pass = haslo.getText().toString().trim();
        final String login_url = "http://crisscross-speakers.000webhostapp.com/login.php";
        Thread threadLogin = new Thread(new Runnable() {
            @Override
            public void run() {
                try  {
                    if(!Log.isEmpty() && !Pass.isEmpty()) {
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, login_url, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //StyleableToast.makeText(zaloguj.this,response,Toast.LENGTH_SHORT,R.style.ToastTheme).show();
                                if (response.equals("Zalogowano pomyslnie")) {
                                    Intent i1 = new Intent(zaloguj.this, Core.class);
                                    startActivity(i1);
                                    finish();
                                }
                                else if (response.equals("nie udalo sie zalogowac")){
                                    StyleableToast.makeText(zaloguj.this,"Niepoprawny login lub hasło",Toast.LENGTH_SHORT,R.style.ToastTheme).show();
                                } }}, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                                NetworkResponse response = error.networkResponse;
                                if (error instanceof ServerError && response != null) {
                                    StyleableToast.makeText(zaloguj.this,"1",Toast.LENGTH_SHORT,R.style.ToastTheme).show();
                                    try {
                                        String res = new String(response.data,
                                                HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                                        StyleableToast.makeText(zaloguj.this,"2",Toast.LENGTH_SHORT,R.style.ToastTheme).show();
                                        // Now you can use any deserializer to make sense of data
                                        StyleableToast.makeText(zaloguj.this,res,Toast.LENGTH_SHORT,R.style.ToastTheme).show();

                                        JSONObject obj = new JSONObject(res);
                                        StyleableToast.makeText(zaloguj.this,"4",Toast.LENGTH_SHORT,R.style.ToastTheme).show();
                                    } catch (UnsupportedEncodingException e1) {
                                        // Couldn't properly decode data to string
                                        e1.printStackTrace();
                                        StyleableToast.makeText(zaloguj.this,"5",Toast.LENGTH_SHORT,R.style.ToastTheme).show();
                                    } catch (JSONException e2) {
                                        // returned data is not JSONObject?
                                        StyleableToast.makeText(zaloguj.this,"6",Toast.LENGTH_SHORT,R.style.ToastTheme).show();
                                        e2.printStackTrace();
                                    }
                                }
                            }})
                        {@Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String,String> dane_logowania = new HashMap<>();
                                dane_logowania.put("login",Log);
                                dane_logowania.put("password",Pass);
                                return dane_logowania;
                            }
                        };
                        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                        requestQueue.add(stringRequest);
                    }
                    else{
                        runOnUiThread(new Runnable()
                        {
                            public void run()
                            {
                                StyleableToast.makeText(zaloguj.this,"Uzupełnij wszystkie pola",Toast.LENGTH_SHORT,R.style.ToastTheme).show();
                            }
                        });

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } }
        });threadLogin.start();
    }
});
    }
}


