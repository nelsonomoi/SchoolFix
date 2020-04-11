package com.example.schoolfix.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.example.schoolfix.R;



public class QuestionActivity extends AppCompatActivity {
    private  Context context=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_activity);
        Bundle bundle=getIntent().getExtras();

        TextView app_name=findViewById(R.id.toolbar_title);
        app_name.setText(bundle.getString("PAPERNAME"));


    }
}
