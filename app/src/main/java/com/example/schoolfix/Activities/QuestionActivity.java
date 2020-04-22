package com.example.schoolfix.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.schoolfix.FixAdapters.QuestionsAdapter;
import com.example.schoolfix.FixAdapters.ResultsAdapter;
import com.example.schoolfix.Helpers.CustomProgressBar;
import com.example.schoolfix.Models.BodyParams.QueryDTO;
import com.example.schoolfix.Models.BodyParams.QuestionBodyParam;
import com.example.schoolfix.Models.BodyParams.SubmitDTO;
import com.example.schoolfix.Models.QuestionsDTO;
import com.example.schoolfix.Models.ResponseModels.ResultResponseDTO;
import com.example.schoolfix.Networking.APIClient;
import com.example.schoolfix.Networking.ApiInterface;
import com.example.schoolfix.R;
import com.google.android.material.button.MaterialButton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class QuestionActivity extends AppCompatActivity {
    private  Context context=this;
    private CustomProgressBar progressBar=new CustomProgressBar();


    private SubmitDTO submitDTO=new SubmitDTO();

    private  MaterialButton submitBtn;
    private RecyclerView recyclerView;
    private TextView app_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_activity);
        recyclerView=findViewById(R.id.questionsrecyclerview);

        Bundle bundle=getIntent().getExtras();

        app_name=findViewById(R.id.toolbar_title);
        app_name.setText(bundle.getString("PAPERNAME"));

        QuestionBodyParam questionBodyParam=new QuestionBodyParam(
                Integer.parseInt(bundle.getString("SCHOOLID")),
                Integer.parseInt(bundle.getString("CLASSID")),
                Integer.parseInt(bundle.getString("SUBJECTID")),
                Integer.parseInt(bundle.getString("PAPERID"))
        );
        fetchQuestions(questionBodyParam);
        progressBar.show(context,"Loading Questions......");
        submitBtn=findViewById(R.id.submit_answers);

    }

    private void fetchQuestions(QuestionBodyParam questionBodyParam) {
        Retrofit retrofit= APIClient.getAPIClient(context);
        ApiInterface apiInterface=retrofit.create(ApiInterface.class);

        Call<List<QuestionsDTO>> call=apiInterface.allQuestions(questionBodyParam);

        call.enqueue(new Callback<List<QuestionsDTO>>() {
           
            @Override
            public void onResponse(Call<List<QuestionsDTO>> call, Response<List<QuestionsDTO>> response) {
                progressBar.getDialog().dismiss();
                List<QuestionsDTO> questions=response.body();

                displayQuestions(questions);
            }

            @Override
            public void onFailure(Call<List<QuestionsDTO>> call, Throwable t) {
                progressBar.getDialog().dismiss();
                Toast.makeText(context, "Oops!.Internal Server error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void displayQuestions(List<QuestionsDTO> questions) {
        QuestionsAdapter questionsAdapter=new QuestionsAdapter(context,questions,R.layout.questions_list);

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(questionsAdapter);

        submitDTO=questionsAdapter.getResult();

        submitBtn.setOnClickListener(v -> {
            sendAnwers(questionsAdapter,questions);
        });
    }

    public  void sendAnwers(QuestionsAdapter questionsAdapter,List<QuestionsDTO> questions){
        submitDTO=questionsAdapter.getResult();
        submitDTO.setSchoolId(Integer.valueOf(getIntent().getExtras().getString("SCHOOLID")));
        submitDTO.setClassId(Integer.valueOf(getIntent().getExtras().getString("CLASSID")));
        submitDTO.setSubjectId(Integer.valueOf(getIntent().getExtras().getString("SUBJECTID")));
        submitDTO.setPaperId(Integer.valueOf(getIntent().getExtras().getString("PAPERID")));
        submitDTO.setKidUsername(getIntent().getExtras().getString("KIDUSERNAME"));

        questions.clear();
        questionsAdapter.notifyDataSetChanged();

        progressBar.show(context,"Please Wait");

        Retrofit retrofit=APIClient.getAPIClient(context);

        ApiInterface apiInterface=retrofit.create(ApiInterface.class);

        Call<List<ResultResponseDTO>> call=apiInterface.get_results(submitDTO);

       call.enqueue(new Callback<List<ResultResponseDTO>>() {
           @Override
           public void onResponse(Call<List<ResultResponseDTO>> call, Response<List<ResultResponseDTO>> response) {
               progressBar.getDialog().dismiss();
               List<ResultResponseDTO> responseDTOList=response.body();
               if (response.isSuccessful()){
                   displyResults(responseDTOList);
               }
           }

           @Override
           public void onFailure(Call<List<ResultResponseDTO>> call, Throwable t) {
               progressBar.getDialog().dismiss();
           }
       });

    }

    public  void displyResults(List<ResultResponseDTO> responseDTO){
        submitBtn.setVisibility(View.GONE);
        ResultsAdapter resultsAdapter=new ResultsAdapter(context,responseDTO,R.layout.results_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(resultsAdapter);
        recyclerView.getAdapter().notifyDataSetChanged();
    }

}
