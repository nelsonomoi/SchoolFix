package com.example.schoolfix.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.schoolfix.Helpers.LoadingSpinner;
import com.example.schoolfix.Helpers.PreferenceManager;
import com.example.schoolfix.Models.BodyParams.LoginParam;
import com.example.schoolfix.Models.ResponseModels.LoginResponse;
import com.example.schoolfix.Networking.APIClient;
import com.example.schoolfix.Networking.ApiInterface;
import com.example.schoolfix.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Login extends AppCompatActivity {

    private final Context context=this;
    private static final String TAG = "TAG";

    PreferenceManager preferenceManager = new PreferenceManager(context);;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final TextInputEditText edemail=findViewById(R.id.edEmail);
        final TextInputEditText edpassword=findViewById(R.id.edpassword);
        final MaterialButton loginbtn=findViewById(R.id.loginbtn);

        final ProgressBar progressBar=findViewById(R.id.loading_spinner);

        final String Emailregex = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        final CharSequence email = edemail.getText();
        final Pattern pattern = Pattern.compile(Emailregex, Pattern.CASE_INSENSITIVE);
        

        edemail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Matcher matcher = pattern.matcher(email);
                if (matcher.matches()) {
                } else {
                    edemail.setError("Incorrect Email Address");
                }
            }
        });

        edpassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
               if (edpassword.getText().toString().length() >= 6){
                   loginbtn.setEnabled(true);
               }else {
                   edpassword.setError("Password lenght is too short.Should be atleast 6 characters.");
               }
            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadingSpinner loadingSpinner=new LoadingSpinner(progressBar);
                loadingSpinner.StartSpin();
                ValidateUser(edemail.getText().toString().trim(),edpassword.getText().toString().trim());
                loginbtn.setEnabled(false);
            }
        });

    }

    public void ValidateUser(String email,String password){
        final LoginParam loginParam=new LoginParam(email,password);
        Retrofit retrofit= APIClient.getAPIClient(context);

        ApiInterface apiInterface=retrofit.create(ApiInterface.class);

        Call<LoginResponse> call=apiInterface.isValidUser(loginParam);

        call.enqueue(new Callback<LoginResponse>() {
            LoadingSpinner loadingSpinner=new LoadingSpinner((ProgressBar) findViewById(R.id.loading_spinner));
            MaterialButton loginbutton=findViewById(R.id.loginbtn);
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse result=response.body();
                loadingSpinner.StopSpinner();
                if (response.isSuccessful()){
                    result.toString();
                    preferenceManager.saveAuthToken("AUTH_TOKEN",result.getToken());
                    Intent intent=new Intent(context,MainActivity.class);
                    startActivity(intent);

                }else{
                    Toast.makeText(Login.this, "Log in Failed check you email and password and try gain.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                loadingSpinner.StopSpinner();
                loginbutton.setEnabled(true);
                Toast.makeText(Login.this, "Unkown error occured while trying to login.Try again.", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }

}
