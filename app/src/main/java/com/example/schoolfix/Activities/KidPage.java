package com.example.schoolfix.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.schoolfix.CustomListeners.OnItemClickListener;
import com.example.schoolfix.CustomListeners.RecyclerItemOnClickListner;
import com.example.schoolfix.FixAdapters.SubjectsAdapter;
import com.example.schoolfix.Helpers.CustomProgressBar;
import com.example.schoolfix.Models.BodyParams.SubjectBodyParam;
import com.example.schoolfix.Models.ResponseModels.SubjectResponse;
import com.example.schoolfix.Networking.APIClient;
import com.example.schoolfix.Networking.ApiInterface;
import com.example.schoolfix.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class KidPage extends AppCompatActivity {
    private Context context=this;

    private static CustomProgressBar progressBar = new CustomProgressBar();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kid_page);

        Bundle bundle=getIntent().getExtras();

//        TextView kid_name=findViewById(R.id.kid_name);
//        TextView school_name=findViewById(R.id.school);

//        String name=bundle.getString("LASTNAME") +" "+ bundle.getString("FIRSTNAME");
//        kid_name.setText(name);
//        school_name.setText(bundle.getString("SCHOOLNAME"));
        fetchSubjects(bundle.getString("CLASSID"),bundle.getString("SCHOOLID"),bundle.getString("KIDUSERNAME"));
        progressBar.show(context,"Fetching Subjects ...");
    }

    public void fetchSubjects(String classid,String schoolid,String kidusername){
        SubjectBodyParam subjectBodyParam=new SubjectBodyParam(schoolid,classid,kidusername);
        Retrofit retrofit= APIClient.getAPIClient(context);

        ApiInterface apiInterface=retrofit.create(ApiInterface.class);

        Call<List<SubjectResponse>> call=apiInterface.allSubjects(subjectBodyParam);

        call.enqueue(new Callback<List<SubjectResponse>>() {
            @Override
            public void onResponse(Call<List<SubjectResponse>> call, Response<List<SubjectResponse>> response) {
                    progressBar.getDialog().dismiss();
                    if (response.isSuccessful()){
                        List<SubjectResponse> subjectResponseList=response.body();
                        displaySubjects(subjectResponseList);
                    }
            }

            @Override
            public void onFailure(Call<List<SubjectResponse>> call, Throwable t) {
                progressBar.getDialog().dismiss();
                Toast.makeText(KidPage.this, "Unkown error occured while fetching the subjects.Try gain", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(context,MainActivity.class));
            }
        });
    }

    public void displaySubjects(final List<SubjectResponse> subjects){
        final RecyclerView recyclerView=findViewById(R.id.kidsRecyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new SubjectsAdapter(context,subjects,R.layout.kid_page));
        recyclerView.addOnItemTouchListener(new RecyclerItemOnClickListner(context, recyclerView, new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Bundle bundle=new Bundle();
                bundle.putString("SUBJECT",subjects.get(position).getSubject().getDescription());
                bundle.putString("SCHOOLID",String.valueOf(subjects.get(position).getSubject().getSchoolId()));
                bundle.putString("CLASSID",String.valueOf(subjects.get(position).getSubject().getClassId()));
                bundle.putString("SUBJECTID",String.valueOf(subjects.get(position).getSubject().getSubjectId()));
                Intent intent=new Intent(context,PapersPage.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));

    }
}
