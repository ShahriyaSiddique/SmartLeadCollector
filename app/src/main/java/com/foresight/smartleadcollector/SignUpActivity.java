package com.foresight.smartleadcollector;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.foresight.smartleadcollector.Api.RetrofitClient;
import com.foresight.smartleadcollector.models.DefaultResponse;
import com.foresight.smartleadcollector.storage.SharedPrefManager;
import com.rengwuxian.materialedittext.MaterialEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    private Button signUpBtn;
    private MaterialEditText emailET;
    private MaterialEditText phoneET;
    private MaterialEditText nameET;
    private MaterialEditText passwordET;
    private MaterialEditText rePasswordET;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initializeFields();

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userSignUp();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(SharedPrefManager.getInstance(this).isLoggedIn()){
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    private void initializeFields() {
        signUpBtn = findViewById(R.id.sign_up_btn);
        emailET=findViewById(R.id.sign_up_email_et);
        phoneET=findViewById(R.id.sign_up_phone_et);
        nameET=findViewById(R.id.sign_up_name_et);
        passwordET= findViewById(R.id.sign_up_password_et);
        rePasswordET= findViewById(R.id.sign_up_password_repeat_et);
    }


    private void userSignUp() {
        String email = emailET.getText().toString().trim();
        String phone = phoneET.getText().toString().trim();
        String name = nameET.getText().toString().trim();
        String password = passwordET.getText().toString().trim();
        String rePassword = rePasswordET.getText().toString().trim();

        if (email.isEmpty()) {
            emailET.setError("Email is required");
            emailET.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailET.setError("Enter a valid email");
            emailET.requestFocus();
            return;
        }
        if (phone.isEmpty()) {
            phoneET.setError("Phone number required");
            phoneET.requestFocus();
            return;
        }

        if (phone.length()<11) {
            phoneET.setError("Phone number required");
            phoneET.requestFocus();
            return;
        }


        if (name.isEmpty()) {
            nameET.setError("Name required");
            nameET.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            passwordET.setError("Password required");
            passwordET.requestFocus();
            return;
        }

        if (password.length() < 6) {
            passwordET.setError("Password should be at least 6 character long");
            passwordET.requestFocus();
            return;
        }
        if (!rePassword.equals(password)) {
            rePasswordET.setError("Password doesn't match");
            rePasswordET.requestFocus();
            return;
        }



        Call<DefaultResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .createUser(email, password, name, phone);


        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                if (response.code() == 201) {

                    DefaultResponse dr = response.body();
                    Toast.makeText(SignUpActivity.this, dr.getMsg(), Toast.LENGTH_LONG).show();

                } else if (response.code() == 422) {
                    Toast.makeText(SignUpActivity.this, "User already exist", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {

                Toast.makeText(SignUpActivity.this,"onFailure"+t.getMessage() , Toast.LENGTH_LONG).show();

            }

        });
        //signUpToMain();

    }
    private void signUpToMain() {

        Intent intent = new Intent(SignUpActivity.this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("userName","user");
        startActivity(intent);
        finish();
    }
}
