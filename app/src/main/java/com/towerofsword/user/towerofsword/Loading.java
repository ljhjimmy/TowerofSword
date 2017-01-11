package com.towerofsword.user.towerofsword;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class Loading extends AppCompatActivity {
    public static final String PREFS_NAME = "MyPrefsFile";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        GlobalVariable globalVariable = (GlobalVariable) getApplicationContext();
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        boolean isPlaying =  settings.getBoolean("isPlaying", false);

        if(isPlaying) {
            int soul = settings.getInt("soul", 0);
            globalVariable.soul = soul;
            int money = settings.getInt("money", 0);
            globalVariable.money = money;
            int currentFloor = settings.getInt("currentFloor", 0);
            globalVariable.currentFloor = currentFloor;
            int lv = settings.getInt("lv", 0);
            globalVariable.lv = lv;
            int stamina = settings.getInt("stamina", 0);
            globalVariable.stamina = stamina;
            int exp = settings.getInt("exp", 0);
            globalVariable.exp = exp;
            int ap = settings.getInt("ap", 0);
            globalVariable.ap = ap;

            globalVariable.playerATK= settings.getInt("playerATK", 0);
            globalVariable.playerDEF= settings.getInt("playerDEF", 0);
            globalVariable.playerHP= settings.getInt("playerHP", 0);
            globalVariable.playerAGI= settings.getInt("playerAGI", 0);
            globalVariable.playerLUC= settings.getInt("playerLUC", 0);
        }
        else{
            editor.clear();
            globalVariable.init();
        }

        Timer timer = new Timer(true);
        timer.schedule(new timerTask(),3000,2000);

    }

    public class timerTask extends TimerTask {

        public void run()
        {
            Intent intent = new Intent();
            intent.setClass(Loading.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
            overridePendingTransition(0,0);
            this.cancel();
            Loading.this.finish();
        }
    }
}
