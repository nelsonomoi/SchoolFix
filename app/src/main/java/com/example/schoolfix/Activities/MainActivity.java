package com.example.schoolfix.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.schoolfix.CustomListeners.ClickListener;
import com.example.schoolfix.CustomListeners.OnItemClickListener;
import com.example.schoolfix.CustomListeners.ParentKidListener;
import com.example.schoolfix.CustomListeners.RecyclerItemOnClickListner;
import com.example.schoolfix.FixAdapters.KidsAdapter;
import com.example.schoolfix.Helpers.CustomProgressBar;
import com.example.schoolfix.Models.Kids;
import com.example.schoolfix.Models.User;
import com.example.schoolfix.Networking.APIClient;
import com.example.schoolfix.Networking.ApiInterface;
import com.example.schoolfix.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private Context context=this;

    private static CustomProgressBar progressBar = new CustomProgressBar();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fetchUserDetails();

    }

    public void fetchUserDetails(){
        progressBar.show(context,"Loading.....");
        Retrofit retrofit=APIClient.getAPIClient(context);
        ApiInterface apiInterface=retrofit.create(ApiInterface.class);

        Call<User> call=apiInterface.User();

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                userController(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });


    }

    public void userController(User user){
        if (user.getUser_type() == 'P'){
           Retrofit retrofit=APIClient.getAPIClient(context);
           ApiInterface apiInterface=retrofit.create(ApiInterface.class);

           Call<List<Kids>> call=apiInterface.kids();
           call.enqueue(new Callback<List<Kids>>() {
               @Override
               public void onResponse(Call<List<Kids>> call, Response<List<Kids>> response) {
                   progressBar.getDialog().dismiss();
                   List<Kids> kids=response.body();
                   if (response.isSuccessful()){
                       displayUserDetails(kids);
                   }

               }

               @Override
               public void onFailure(Call<List<Kids>> call, Throwable t) {

               }
           });

        }else{
           setContentView(R.layout.kid_page);
        }

    }

    public void displayUserDetails(final List<Kids> kids){
        setContentView(R.layout.parent_page);
        final Intent intent=new Intent(context,KidPage.class);
        final RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new KidsAdapter(context,kids,R.layout.parent_kids_adapter));
        recyclerView.addOnItemTouchListener(new RecyclerItemOnClickListner(context, recyclerView, new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Bundle bundle=new Bundle();
                bundle.putString("KIDUSERNAME",kids.get(position).getKid_username());
                bundle.putString("CLASSID",kids.get(position).getClass_id());
                bundle.putString("SCHOOLID",kids.get(position).getSchool_id());
                bundle.putString("LASTNAME",kids.get(position).getKid_lastname());
                bundle.putString("FIRSTNAME",kids.get(position).getKid_firstname());
                bundle.putString("SCHOOLNAME",kids.get(position).getSchool_name());
                intent.putExtras(bundle);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));
    }
}
