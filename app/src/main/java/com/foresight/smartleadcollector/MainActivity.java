package com.foresight.smartleadcollector;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String current = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        current = getIntent().getStringExtra("userName");

    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.i("this is ", "onStart: " + current);
        if (current==null) {
            mainToWelcome();

        }
        else {
            Toast.makeText(this, "This is the main activity", Toast.LENGTH_SHORT).show();
        }
    }

    private void mainToWelcome() {
        Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
