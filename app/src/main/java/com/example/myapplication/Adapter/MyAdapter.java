package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Model.InputFields;
import com.example.myapplication.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<InputFields> inputFields;

    Context context;


    public MyAdapter(List<InputFields> inputFields, Context context) {
        this.inputFields = inputFields;
        this.context = context;

    }


    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;

        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new MyAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyAdapter.MyViewHolder holder, int position) {
        final InputFields inputField = inputFields.get(holder.getAdapterPosition());

        holder.mobnum.setText(inputField.getMobileNumber());
        holder.email.setText(inputField.getEmailid());

        ////////decimal code///////


//


    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.mobno)
        TextView mobnum;


        @BindView(R.id.emailidtxt)
        TextView email;


        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }


    }


    @Override
    public int getItemCount() {
        return inputFields.size();
    }
}

