package com.towerofsword.user.towerofsword;

import android.content.Intent;
import android.graphics.Typeface;
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

        Typeface font_pixel = Typeface.createFromAsset(getAssets(), "fonts/manaspace.ttf");

        Button btnBack = (Button)findViewById(R.id.btnBack);
        btnBack.setTypeface(font_pixel);
        btnBack.setOnClickListener(listenerBack);

        Button btnAb = (Button)findViewById(R.id.btnAb);
        btnAb.setTypeface(font_pixel);
        btnAb.setOnClickListener(listenerAbility);

        Button btnEquipment = (Button)findViewById(R.id.btnEquipment);
        btnEquipment.setTypeface(font_pixel);
        //btnEquipment.setOnClickListener(listenerEquipment);

        GlobalVariable globalVariable = (GlobalVariable) getApplicationContext();
        TextView textViewCoin = (TextView) findViewById(R.id.textViewCoin);
        textViewCoin.setTypeface(font_pixel);
        textViewCoin.setText(Integer.toString(globalVariable.money)) ;
        TextView textViewSoul = (TextView) findViewById(R.id.textViewSoul);
        textViewSoul.setTypeface(font_pixel);
        textViewSoul.setText(Integer.toString(globalVariable.soul)) ;
    }

    private Button.OnClickListener listenerBack = new Button.OnClickListener(){
        public void onClick(View v) {
            finish();
            overridePendingTransition(0, 0);
        }
    };

    private Button.OnClickListener listenerAbility = new Button.OnClickListener(){
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(Status.this, Ability.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
            overridePendingTransition(0,0);
        }
    };


}


