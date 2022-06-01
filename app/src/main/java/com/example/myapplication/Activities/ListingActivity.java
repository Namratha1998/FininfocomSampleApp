package com.example.myapplication.Activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapter.MyAdapter;
import com.example.myapplication.Helpers.PrefManager;
import com.example.myapplication.Model.InputFields;
import com.example.myapplication.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListingActivity extends AppCompatActivity {
    String emailid;
     String mobilenumber;
    ArrayList<InputFields> inputfields=new ArrayList<>();





    @BindView(R.id.credentialrecyclerview)
    RecyclerView recyclerView;
    PrefManager prefManager;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listing_page);
        ButterKnife.bind(this);
        prefManager=PrefManager.getInstance();
         emailid = getIntent().getStringExtra("email");
        mobilenumber=getIntent().getStringExtra("mobilenumber");

        System.out.println("print this"+ emailid + mobilenumber);

        inputfields.add(new InputFields(prefManager.getemail(), prefManager.getMobileNumber()));


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        MyAdapter mAdapter = new MyAdapter(inputfields,getApplicationContext());
        recyclerView.setAdapter(mAdapter);

    }
    }
