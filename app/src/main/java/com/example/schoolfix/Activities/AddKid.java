package com.example.schoolfix.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.schoolfix.Helpers.CustomProgressBar;
import com.example.schoolfix.Models.BodyParams.KidBodyParam;
import com.example.schoolfix.Models.Kids;
import com.example.schoolfix.Networking.APIClient;
import com.example.schoolfix.Networking.ApiInterface;
import com.example.schoolfix.R;

import org.json.JSONObject;

import java.util.List;
import java.util.Random;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AddKid extends AppCompatActivity {

    Context context=this;
    CompositeDisposable disposable;
    KidBodyParam kidBodyParam=new KidBodyParam();
    CustomProgressBar customProgressBar=new CustomProgressBar();

    private    Button   addkid;
    private EditText edfirstname,edlastname,edclassname,edschoolname,edlocation,edemail,edidno,edphoneno,edcontactno;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_kid);

        edfirstname=findViewById(R.id.firstname);
        edlastname=findViewById(R.id.lastname);
        edclassname=findViewById(R.id.classname);
        edschoolname=findViewById(R.id.schoolname);
        edlocation=findViewById(R.id.location);
        addkid=findViewById(R.id.addkid);
        edemail=findViewById(R.id.email_address);
        edidno=findViewById(R.id.id_number);
        edphoneno=findViewById(R.id.phone_number);
        edcontactno=findViewById(R.id.contact_mobile_no);

        addkid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (CheckIfEmpty()){
                   postKid();
               }
            }
        });
    }

    public  boolean CheckIfEmpty(){
        boolean empty= false;
        if (TextUtils.isEmpty(edfirstname.getText())){
            edfirstname.setError("Firstname Should not be Empty");
        }else if(TextUtils.isEmpty(edlastname.getText())){
            edlastname.setError("Lastname Should not be Empty");
        }else if(TextUtils.isEmpty(edclassname.getText())){
            edclassname.setError("Class  name should not be Empty");
        }else if(TextUtils.isEmpty(edschoolname.getText())){
            edschoolname.setError("School name is Empty");
        }else if (TextUtils.isEmpty(edlocation.getText())){
            edlocation.setError("Location is Empty");
        }else {
            empty=true;
        }
        return  empty;
    }

    public void postKid(){
        Random id=new Random();
        int class_id=id.nextInt(1000-10) - 10;
        int school_id=id.nextInt(10000-100) - 100;
        kidBodyParam.setFirst_name(edfirstname.getText().toString().trim());
        kidBodyParam.setLast_name(edlastname.getText().toString().trim());
        kidBodyParam.setClass_name(edclassname.getText().toString().trim());
        kidBodyParam.setSchool_name(edschoolname.getText().toString().trim());
        kidBodyParam.setLocation(edlocation.getText().toString().trim());
        kidBodyParam.setClass_id(1);
        kidBodyParam.setSchool_id(2);
        kidBodyParam.setUser_type("K");
        kidBodyParam.setEmail_address(edemail.getText().toString().trim());
        kidBodyParam.setId_number(Integer.parseInt(edidno.getText().toString().trim()));
        kidBodyParam.setPhone_number(edphoneno.getText().toString().trim());
        kidBodyParam.setUser_name("testuser");
        kidBodyParam.setContact_mobile_no(edcontactno.getText().toString().trim());

        customProgressBar.show(context,"");
        Retrofit retrofit= APIClient.getAPIClient(context);

        ApiInterface apiInterface=retrofit.create(ApiInterface.class);

        Call<JSONObject> call=apiInterface.addKid(kidBodyParam);
        
        call.enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                customProgressBar.getDialog().dismiss();
                if (response.isSuccessful()){
                    Toast.makeText(AddKid.this, "Success", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(context,MainActivity.class));
                }
            }

            @Override
            public void onFailure(Call<JSONObject> call, Throwable t) {
                customProgressBar.getDialog().dismiss();
                Toast.makeText(AddKid.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
