package com.towerofsword.user.towerofsword;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Record extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        Typeface font_pixel = Typeface.createFromAsset(getAssets(), "fonts/manaspace.ttf");

        Button buttonRecordBack = (Button)findViewById(R.id.buttonRecordBack);
        buttonRecordBack.setTypeface(font_pixel);
        buttonRecordBack.setOnClickListener(listenerBack);
        TextView TextViewRecordLv = (TextView)findViewById(R.id.TextViewRecordLv);
        TextViewRecordLv.setTypeface(font_pixel);
        TextView TextViewRecordFloor = (TextView)findViewById(R.id.TextViewRecordFloor);
        TextViewRecordFloor.setTypeface(font_pixel);
        TextView TextViewRecordLvNum = (TextView)findViewById(R.id.TextViewRecordLvNum);
        TextViewRecordLvNum.setTypeface(font_pixel);
        TextView TextViewRecordFloorNum = (TextView)findViewById(R.id.TextViewRecordFloorNum);
        TextViewRecordFloorNum.setTypeface(font_pixel);

        GlobalVariable globalVariable = (GlobalVariable) getApplicationContext();

        TextViewRecordLvNum.setText(String.valueOf(globalVariable.recordLv));
        TextViewRecordFloorNum.setText(String.valueOf(globalVariable.recordFloor));
    }

    private Button.OnClickListener listenerBack = new Button.OnClickListener(){
        public void onClick(View v) {
            finish();
            overridePendingTransition(0, 0);
        }
    };
}