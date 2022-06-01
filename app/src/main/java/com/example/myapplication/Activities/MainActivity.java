package com.example.myapplication.Activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Helpers.PrefManager;
import com.example.myapplication.R;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements Validator.ValidationListener{


    @BindView(R.id.et_mobilrnumber)
    @NotEmpty
    EditText mobilenumber;

    @BindView(R.id.et_mailid)
    @NotEmpty
    EditText mailid;

    @BindView(R.id.login_btn)
    Button login_btn;
    Validator validator;
PrefManager prefManager;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupUI(findViewById(R.id.login_activity));
        prefManager = PrefManager.getInstance();

        mailid.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);


        mobilenumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()==10){
                    login_btn.setBackgroundResource(R.drawable.create_btn_drawable);

                    hideKeyboardFormUser();

                }else{

                }

            }
        });




        validator = new Validator(this);
        validator.setValidationListener(this);

    }
    public void hideKeyboardFormUser(){
        View view = getCurrentFocus();
        InputMethodManager hideKeyboard  = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);

        if (view!=null) {
            hideKeyboard.hideSoftInputFromWindow( view.getWindowToken(), 0);
        }

    }


    public void setupUI(View view) {
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    return false;
                }
            });
        }
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView);
            }
        }
    }


    //email validation//
    private boolean isValidEmailId(String email){

        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }


    @Override
    public void onValidationSucceeded() {
        if(isValidEmailId(mailid.getText().toString().trim())){
//            Toast.makeText(getApplicationContext(), "Valid Email Address.", Toast.LENGTH_SHORT).show();
            prefManager.setIsLoggedinStatus(true);

            movetoListingPage();

        }else{
            Toast.makeText(getApplicationContext(), "InValid Email Address.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this.getApplicationContext());

            // Display error messages ;)
            if (view instanceof EditText) {
                Drawable errorIcon = getResources().getDrawable(R.drawable.error_icon);
                errorIcon.setBounds(new Rect(-10, 5, 70, 70));
                ((EditText) view).setError(message, errorIcon);

            } else {
//                displaySnackBarUtil(message);
                Toast.makeText(this.getApplicationContext(), message, Toast.LENGTH_LONG).show();
            }



        }

    }



    @OnClick(R.id.login_btn)
    public void onLoginClicked(){
        validator.validate();


    }

     public  void movetoListingPage(){
         prefManager.setemail(mailid.getText().toString());
         prefManager.setMobileNumber(mobilenumber.getText().toString());
         Intent intent=new Intent(this, ListingActivity.class);
         intent.putExtra("email", mailid.getText().toString());
         intent.putExtra("mobilenumber", mobilenumber.getText().toString());

         startActivity(intent);
         finish();
     }
}