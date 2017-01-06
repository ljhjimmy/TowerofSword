package com.towerofsword.user.towerofsword;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Typeface font_pixel = Typeface.createFromAsset(getAssets(), "fonts/manaspace.ttf");

        Button btnAdventure = (Button)findViewById(R.id.btnAdventure);
        btnAdventure.setTypeface(font_pixel);
        btnAdventure.setOnClickListener(listenerAdventure);

        Button btnRecord = (Button)findViewById(R.id.btnRecord);
        btnRecord.setTypeface(font_pixel);
        //btnRecord.setOnClickListener(listenerRecord);

        Button btnTalent = (Button)findViewById(R.id.btnTalent);
        btnTalent.setTypeface(font_pixel);
        //btnTalent.setOnClickListener(listenerTalent);

        Button btnEquipment = (Button)findViewById(R.id.btnEquipment);
        btnEquipment.setTypeface(font_pixel);
        //btnEquipment.setOnClickListener(listenerEquipment);
    }


    private Button.OnClickListener listenerAdventure = new Button.OnClickListener(){
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(Home.this, Adventure.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
            overridePendingTransition(0,0);
            finish();
        }
    };

    @Override
    public void onBackPressed() {

        new AlertDialog.Builder(Home.this)
                .setTitle("確認視窗")
                .setMessage("確定要結束應用程式嗎?")
                //.setIcon(R.drawable.ic_menu_info_details)
                .setPositiveButton("確定",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                finish();
                            }
                        })
                .setNegativeButton("取消",
                        null).show();
    }
}


