package com.example.dyp1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.util.Patterns;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;
import com.muddzdev.styleabletoastlibrary.StyleableToast;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class zarejestruj extends AppCompatActivity {
    public TextView zaloguj;
    public EditText login, password, password2, mail, tel;
    public Button zarejestruj;
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[a-zA-Z])" +
                    "(?=.*[0-9])" +
                    "(?=\\S+$)" +
                    ".{8,}" +
                    "$");
    String str_login;
    String str_password;
    String str_password2;
    String str_mail;
    String str_tel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zarejestruj);
        zaloguj = (TextView) findViewById(R.id.textView12);
        login = (EditText) findViewById(R.id.editText5);
        password = (EditText) findViewById(R.id.editText);
        password2 = (EditText) findViewById(R.id.editText4);
        mail = (EditText) findViewById(R.id.editText6);
        tel = (EditText) findViewById(R.id.editText9);
        zarejestruj = (Button) findViewById(R.id.button4);
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        zaloguj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(zarejestruj.this, zaloguj.class);
                startActivity(i1);
                finish();
            }
        });

        zarejestruj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(networkInfo == null && !networkInfo.isConnected())
                {
                    StyleableToast.makeText(zarejestruj.this,"Nie ma połączenia z internetem",Toast.LENGTH_SHORT,R.style.ToastTheme).show();
                }
                str_login = login.getText().toString();
                str_password = password.getText().toString();
                str_password2 = password2.getText().toString();
                str_mail = mail.getText().toString();
                str_tel = tel.getText().toString();
                final String register_url = "http://crisscross-speakers.000webhostapp.com/register.php";
                if (!validateEmail() | !validateUsername() | !validatePassword() | !validatePhone()) {
                    StyleableToast.makeText(zarejestruj.this,"Wprowadzono niepoprawne dane",Toast.LENGTH_SHORT,R.style.ToastTheme).show();
                }
                else
                {
                    Thread threadRegister = new Thread(new Runnable() {

                        @Override
                        public void run() {
                            try{
                                StringRequest stringRequest = new StringRequest(Request.Method.POST, register_url, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        if (response.equals("Rejestracja pomyslna")) {
                                            Intent i1 = new Intent(zarejestruj.this, zaloguj.class);
                                            startActivity(i1);
                                            finish();
                                        }
                                        else if (response.equals("Rejestracja niepomyslna")){
                                            StyleableToast.makeText(zarejestruj.this,"Nie udało się zarejestrować, spróbuj ponownie",Toast.LENGTH_SHORT,R.style.ToastTheme).show();
                                        }
                                        }
                                    }, new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            StyleableToast.makeText(zarejestruj.this,error.toString().trim(),Toast.LENGTH_SHORT,R.style.ToastTheme).show();
                                        }
                                    })
                                    {
                                        @Override
                                        protected Map<String, String> getParams() throws AuthFailureError {
                                            Map<String,String> dane_rejestracji = new HashMap<>();
                                            dane_rejestracji.put("user",str_login);
                                            dane_rejestracji.put("pass",str_password);
                                            dane_rejestracji.put("mail",str_mail);
                                            dane_rejestracji.put("tel",str_tel);
                                            return dane_rejestracji;
                                        }
                                    };
                                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                                    requestQueue.add(stringRequest);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    threadRegister.start();
                }
            }
        });

    }
    private boolean validateEmail() {
        String emailInput = mail.getText().toString().trim();

        if (emailInput.isEmpty()) {
            mail.setError("Pole nie może być puste");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            mail.setError("Niepoprawny e-mail");
            return false;
        } else {
            mail.setError(null);
            return true;
        }
    }
    private boolean validatePhone() {
        String telInput = tel.getText().toString().trim();

        if (telInput.isEmpty()) {
            tel.setError("Pole nie może być puste");
            return false;
        } else if (!Patterns.PHONE.matcher(telInput).matches()) {
            tel.setError("Niepoprawny numer telefonu");
            return false;
        } else {
            tel.setError(null);
            return true;
        }
    }
    private boolean validateUsername() {
        String usernameInput = login.getText().toString().trim();

        if (usernameInput.isEmpty()) {
            login.setError("Pole nie może być puste");
            return false;
        } else if (usernameInput.length() > 15) {
            login.setError("Nazwa użytkownika jest za długa");
            return false;
        }else if (usernameInput.length() < 5) {
            login.setError("Nazwa użytkownika jest za krótka");
            return false;
        } else {
            login.setError(null);
            return true;
        }
    }
    private boolean validatePassword() {
        String passwordInput = password.getText().toString().trim();
        String passwordInput2 = password2.getText().toString().trim();
        if (passwordInput.isEmpty()) {
            password.setError("Pole nie może być puste");
            return false;
        }
        if (passwordInput2.isEmpty()) {
            password2.setError("Pole nie może być puste");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            password.setError("Hasło musi zawierać co najmniej 8 znaków");
            return false;
        } else if (!passwordInput.equals(passwordInput2)) {
            password2.setError("Hasła muszą być identyczne");
        } else {
            password.setError(null);
            return true;
        }
        return true;
    }





}
