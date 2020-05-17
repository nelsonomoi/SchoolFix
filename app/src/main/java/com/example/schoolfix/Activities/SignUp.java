package com.example.schoolfix.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.schoolfix.Helpers.CustomProgressBar;
import com.example.schoolfix.Models.BodyParams.KidBodyParam;
import com.example.schoolfix.Networking.APIClient;
import com.example.schoolfix.Networking.ApiInterface;
import com.example.schoolfix.R;
import com.google.android.material.button.MaterialButton;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SignUp extends AppCompatActivity {
    Context context=this;
    CustomProgressBar progressBar=new CustomProgressBar();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        MaterialButton register=findViewById(R.id.registerbtn);
        EditText edfirstname=findViewById(R.id.edfirstname);
        EditText edlastname=findViewById(R.id.lastnamebtn);
        EditText edmobile=findViewById(R.id.mobilenobtn);
        EditText edidno=findViewById(R.id.id_nobtn);
        EditText edemail=findViewById(R.id.emailaddressbtn);
        EditText password=findViewById(R.id.passwordbtn);
        EditText edconfirmpassword=findViewById(R.id.confirm_password);

        KidBodyParam kidBodyParam=new KidBodyParam();

        Retrofit retrofit= APIClient.getAPIClient(context);
        ApiInterface apiInterface=retrofit.create(ApiInterface.class);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.show(context,"Saving");

                kidBodyParam.setFirst_name(edfirstname.getText().toString().trim());
                kidBodyParam.setLast_name(edlastname.getText().toString().trim());
                kidBodyParam.setEmail_address(edemail.getText().toString().trim());
                kidBodyParam.setPhone_number(edmobile.getText().toString().trim());
                kidBodyParam.setId_number(Integer.parseInt(edidno.getText().toString().trim()));
                kidBodyParam.setPassword(password.getText().toString().trim());
                kidBodyParam.setUser_type("P");

                Call<JSONObject> call=apiInterface.addKid(kidBodyParam);

                call.enqueue(new Callback<JSONObject>() {
                    @Override
                    public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                        progressBar.getDialog().dismiss();
                        if (response.isSuccessful()){
                            Toast.makeText(context, "Created Successfully", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<JSONObject> call, Throwable t) {
                        progressBar.getDialog().dismiss();
                        Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}
