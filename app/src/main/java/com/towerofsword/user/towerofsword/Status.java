package com.towerofsword.user.towerofsword;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Status extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        Button btnBack = (Button)findViewById(R.id.buttonBack);
        btnBack.setOnClickListener(listenerBack);

        GlobalVariable globalVariable = (GlobalVariable) getApplicationContext();
        TextView textViewCoin = (TextView) findViewById(R.id.textViewCoin);
        textViewCoin.setText(Integer.toString(globalVariable.money)) ;
        TextView textViewSoul = (TextView) findViewById(R.id.textViewSoul);
        textViewSoul.setText(Integer.toString(globalVariable.soul)) ;
    }

    private Button.OnClickListener listenerBack = new Button.OnClickListener(){
        public void onClick(View v) {
            finish();
            overridePendingTransition(0, 0);
        }
    };



}


