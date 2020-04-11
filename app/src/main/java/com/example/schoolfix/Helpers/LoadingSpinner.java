package com.example.schoolfix.Helpers;

import android.view.View;
import android.widget.ProgressBar;

public class LoadingSpinner {
    private  ProgressBar loading_spinner;

    public LoadingSpinner(ProgressBar spinner){
        this.loading_spinner=spinner;
    }

    public void  StartSpin(){
        loading_spinner.setVisibility(View.VISIBLE);
    }

    public void StopSpinner(){
        loading_spinner.setVisibility(View.INVISIBLE);
    }
}
