package com.example.myapplication.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Helpers.PrefManager;
import com.example.myapplication.R;

import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (PrefManager.getInstance().getIsLoggedinStatus()) {
                    redirectToHomePage();
                } else{
                    redirectToLoginPage();
                }
            }
        }, 500);

    }

    private void redirectToHomePage() {
        Intent intent = new Intent(this, ListingActivity.class);
        startActivity(intent);
        finish();
    }

    private void redirectToLoginPage() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
