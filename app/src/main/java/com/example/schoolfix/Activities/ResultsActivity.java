package com.example.schoolfix.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.schoolfix.FixAdapters.QuestionsAdapter;
import com.example.schoolfix.FixAdapters.ResultsAdapter;
import com.example.schoolfix.Helpers.CustomProgressBar;
import com.example.schoolfix.Models.BodyParams.SubmitDTO;
import com.example.schoolfix.Models.QuestionsDTO;
import com.example.schoolfix.Models.ResponseModels.ResultResponseDTO;
import com.example.schoolfix.Networking.APIClient;
import com.example.schoolfix.Networking.ApiInterface;
import com.example.schoolfix.R;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ResultsActivity extends AppCompatActivity {
    private Context context=this;
    private CustomProgressBar progressBar=new CustomProgressBar();

    private SubmitDTO submitDTO=new SubmitDTO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        TextView app_name=findViewById(R.id.toolbar_title);
        app_name.setText("Score");

        Bundle bundle=getIntent().getExtras();
        submitDTO= (SubmitDTO) bundle.getSerializable("submitBtn");
        Log.v("Check",submitDTO.getAnswer().toString());
//        List<QuestionsDTO> questions= (List<QuestionsDTO>) bundle.getSerializable("questions");
//        QuestionsAdapter questionsAdapter=new QuestionsAdapter(getApplicationContext(),questions,R.layout.questions_list);

//        submitDTO=questionsAdapter.getResult();
        submitDTO.setSchoolId(Integer.valueOf(getIntent().getExtras().getString("SCHOOLID")));
        submitDTO.setClassId(Integer.valueOf(getIntent().getExtras().getString("CLASSID")));
        submitDTO.setSubjectId(Integer.valueOf(getIntent().getExtras().getString("SUBJECTID")));
        submitDTO.setPaperId(Integer.valueOf(getIntent().getExtras().getString("PAPERID")));
        submitDTO.setKidUsername(getIntent().getExtras().getString("KIDUSERNAME"));

        progressBar.show(context,"Please Wait..");

        Retrofit retrofit= APIClient.getAPIClient(context);

        ApiInterface apiInterface=retrofit.create(ApiInterface.class);

        Call<List<ResultResponseDTO>> call=apiInterface.get_results(submitDTO);

       call.enqueue(new Callback<List<ResultResponseDTO>>() {
           @Override
           public void onResponse(Call<List<ResultResponseDTO>> call, Response<List<ResultResponseDTO>> response) {
               progressBar.getDialog().dismiss();
               List<ResultResponseDTO> resultResponseDTOList=response.body();
               if (response.isSuccessful()){
                   displyaResults(resultResponseDTOList);
               }
           }

           @Override
           public void onFailure(Call<List<ResultResponseDTO>> call, Throwable t) {
               progressBar.getDialog().dismiss();
           }
       });
    }

    public  void displyaResults(List<ResultResponseDTO> responseDTO){
        final RecyclerView recyclerView=findViewById(R.id.resultsrecycler);
        ResultsAdapter resultsAdapter=new ResultsAdapter(context,responseDTO,R.layout.results_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(resultsAdapter);
    }
}
