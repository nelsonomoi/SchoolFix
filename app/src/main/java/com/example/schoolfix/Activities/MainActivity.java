package com.example.schoolfix.Activities;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


import com.example.schoolfix.CustomListeners.OnItemClickListener;
import com.example.schoolfix.CustomListeners.RecyclerItemOnClickListner;
import com.example.schoolfix.FixAdapters.KidsAdapter;
import com.example.schoolfix.Helpers.CustomProgressBar;
import com.example.schoolfix.Models.Kids;
import com.example.schoolfix.Models.User;
import com.example.schoolfix.Networking.APIClient;
import com.example.schoolfix.Networking.ApiInterface;
import com.example.schoolfix.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private Context context=this;
    CompositeDisposable disposable=new CompositeDisposable();
    private KidsAdapter kidsAdapter;
    private List<Kids> kidsList=new ArrayList<>();
    private static CustomProgressBar progressBar = new CustomProgressBar();


    DrawerLayout mdrawer;
    ActionBarDrawerToggle toggle;

    FloatingActionButton add_kid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parent_page);

        Toolbar toolbar=findViewById(R.id.toolbar);

        add_kid=findViewById(R.id.add_kid);

        add_kid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,AddKid.class);
                startActivity(intent);
            }
        });
        fetchUserDetails();
    }

    public void fetchUserDetails(){
        progressBar.show(context,"Loading.....");
        Retrofit retrofitclient=APIClient.getAPIClient(context);
        ApiInterface apiInterface=retrofitclient.create(ApiInterface.class);

        Call<User> call=apiInterface.User();

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                userController(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                progressBar.getDialog().dismiss();
            }
        });

    }

    public void userController(User user){
        if (user.getUser_type() == 'P'){
            final RecyclerView recyclerView = findViewById(R.id.recyclerview);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            kidsAdapter=new KidsAdapter(context,kidsList,R.layout.parent_kids_adapter);
            recyclerView.setAdapter(kidsAdapter);

            final Intent intent=new Intent(context,KidPage.class);

            recyclerView.addOnItemTouchListener(new RecyclerItemOnClickListner(context, recyclerView, new OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Bundle bundle=new Bundle();
                    bundle.putString("KIDUSERNAME",kidsList.get(position).getKid_username());
                    bundle.putString("CLASSID",kidsList.get(position).getClass_id());
                    bundle.putString("SCHOOLID",kidsList.get(position).getSchool_id());
                    bundle.putString("LASTNAME",kidsList.get(position).getKid_lastname());
                    bundle.putString("FIRSTNAME",kidsList.get(position).getKid_firstname());
                    bundle.putString("SCHOOLNAME",kidsList.get(position).getSchool_name());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }

                @Override
                public void onItemLongClick(View view, int position) {

                }
            }));

            fetchParentKids();

        }else{
           setContentView(R.layout.kid_page);
        }

    }

    public void fetchParentKids(){
        Retrofit retrofitclient=APIClient.getAPIClient(context);
        ApiInterface apiInterface=retrofitclient.create(ApiInterface.class);

        disposable.add(
                apiInterface
                    .kids()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith( new DisposableSingleObserver<List<Kids>>(){

                        @Override
                        public void onSuccess(List<Kids> kids) {
                            progressBar.getDialog().dismiss();
                            kidsList.clear();
                            kidsList.addAll(kids);
                            kidsAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onError(Throwable e) {
                            progressBar.getDialog().dismiss();
                            Log.e("ERROR","PARENT KIDS"+e.getMessage());
                        }
                    })
        );
    }
}
