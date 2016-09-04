package com.example.viking.tsx6;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by viking on 3/9/16.
 */
public class Net_hunt extends AppCompatActivity {
    String Username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.net_hunt);
        Intent intent = getIntent();
        String a = intent.getStringExtra("username");
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        intent.putExtra("username",Username);


    }
}
