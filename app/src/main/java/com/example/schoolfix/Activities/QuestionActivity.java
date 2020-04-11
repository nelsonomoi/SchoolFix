package com.example.schoolfix.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.schoolfix.Helpers.CustomProgressBar;
import com.example.schoolfix.Models.BodyParams.QuestionBodyParam;
import com.example.schoolfix.Models.QuestionsDTO;
import com.example.schoolfix.Networking.APIClient;
import com.example.schoolfix.Networking.ApiInterface;
import com.example.schoolfix.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class QuestionActivity extends AppCompatActivity {
    private  Context context=this;
    private CustomProgressBar progressBar=new CustomProgressBar();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_activity);
        Bundle bundle=getIntent().getExtras();

        TextView app_name=findViewById(R.id.toolbar_title);
        app_name.setText(bundle.getString("PAPERNAME"));

        QuestionBodyParam questionBodyParam=new QuestionBodyParam(
                Integer.parseInt(bundle.getString("SCHOOLID")),
                Integer.parseInt(bundle.getString("CLASSID")),
                Integer.parseInt(bundle.getString("SUBJECTID")),
                Integer.parseInt(bundle.getString("PAPERID"))
        );
        fetchQuestions(questionBodyParam);
        progressBar.show(context,"Loading Questions......");
    }

    private void fetchQuestions(QuestionBodyParam questionBodyParam) {
        Retrofit retrofit= APIClient.getAPIClient(context);
        ApiInterface apiInterface=retrofit.create(ApiInterface.class);

        Call<List<QuestionsDTO>> call=apiInterface.allQuestions(questionBodyParam);

        call.enqueue(new Callback<List<QuestionsDTO>>() {
           
            @Override
            public void onResponse(Call<List<QuestionsDTO>> call, Response<List<QuestionsDTO>> response) {
                progressBar.getDialog().dismiss();
                if (response.isSuccessful()){
                    
                }else{
                    Toast.makeText(QuestionActivity.this, "Loading Contents Failed.Royal Exams says Requested contents Not Found ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<QuestionsDTO>> call, Throwable t) {
                progressBar.getDialog().dismiss();
                Toast.makeText(context, "Oops!.Internal Server error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
