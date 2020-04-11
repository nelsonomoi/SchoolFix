package com.example.schoolfix.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.example.schoolfix.CustomListeners.OnItemClickListener;
import com.example.schoolfix.CustomListeners.RecyclerItemOnClickListner;
import com.example.schoolfix.FixAdapters.PapersAdapter;
import com.example.schoolfix.Helpers.CustomProgressBar;
import com.example.schoolfix.Models.BodyParams.PapersBodyParam;
import com.example.schoolfix.Models.PapersDTO;
import com.example.schoolfix.Networking.APIClient;
import com.example.schoolfix.Networking.ApiInterface;
import com.example.schoolfix.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PapersPage extends AppCompatActivity {

    private  Context context=this;
    private static CustomProgressBar progressBar=new CustomProgressBar();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_papers_page);

        Bundle bundle=getIntent().getExtras();
        TextView apptitle=findViewById(R.id.toolbar_title);
        apptitle.setText(bundle.getString("SUBJECT"));
        progressBar.show(context,"Loading.........");
        fetchPapers(Integer.parseInt(bundle.getString("SCHOOLID")),Integer.parseInt(bundle.getString("CLASSID")),Integer.parseInt(bundle.getString("SUBJECTID")));
    }

    private void fetchPapers(int schoolid, int classid, int subjectid) {
        PapersBodyParam papersBodyParam=new PapersBodyParam(schoolid,classid,subjectid);
        Retrofit retrofit=APIClient.getAPIClient(context);
        ApiInterface apiInterface=retrofit.create(ApiInterface.class);

        Call<List<PapersDTO>> call=apiInterface.getPapers(papersBodyParam);

        call.enqueue(new Callback<List<PapersDTO>>() {
            @Override
            public void onResponse(Call<List<PapersDTO>> call, Response<List<PapersDTO>> response) {
                if (response.isSuccessful()){
                    List<PapersDTO> papersDTOS=response.body();
                    displayPapers(papersDTOS);
                }
            }

            @Override
            public void onFailure(Call<List<PapersDTO>> call, Throwable t) {

            }
        });
    }

    private void displayPapers(final List<PapersDTO> papersDTOS) {
        final RecyclerView recyclerView=findViewById(R.id.papersrecyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new PapersAdapter(context,papersDTOS,R.layout.papers_list));
        recyclerView.addOnItemTouchListener(new RecyclerItemOnClickListner(context, recyclerView, new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Bundle bundle=new Bundle();
                bundle.putString("PAPERNAME",papersDTOS.get(position).getPaperName());
                bundle.putString("SCHOOLID",String.valueOf(papersDTOS.get(position).getClassId()));
                bundle.putString("CLASSID",String.valueOf(papersDTOS.get(position).getSchoolId()));
                bundle.putString("SUBJECTID",String.valueOf(papersDTOS.get(position).getSubjectId()));
                bundle.putString("PAPERID",String.valueOf(papersDTOS.get(position).getPaperId()));
                Intent intent=new Intent(context,QuestionActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));
    }
}
