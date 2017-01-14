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
    public static final String PREFS_NAME_RECORD = "MyPrefsFile2";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        GlobalVariable globalVariable = (GlobalVariable) getApplicationContext();
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        boolean isPlaying =  settings.getBoolean("isPlaying", false);

        if(isPlaying) {
            globalVariable.soul = settings.getInt("soul", 0);
            globalVariable.money = settings.getInt("money", 0);
            globalVariable.currentFloor = settings.getInt("currentFloor", 0);
            globalVariable.lv = settings.getInt("lv", 0);
            globalVariable.stamina = settings.getInt("stamina", 0);
            globalVariable.exp = settings.getInt("exp", 0);
            globalVariable.ap = settings.getInt("ap", 0);

            globalVariable.playerATK= settings.getInt("playerATK", 0);
            globalVariable.playerDEF= settings.getInt("playerDEF", 0);
            globalVariable.playerHP= settings.getInt("playerHP", 0);
            globalVariable.playerAGI= settings.getInt("playerAGI", 0);
            globalVariable.playerLUC= settings.getInt("playerLUC", 0);

            globalVariable.bossDefeated[0] = settings.getBoolean("bossDefeated1", false);
            globalVariable.bossDefeated[1] = settings.getBoolean("bossDefeated2", false);
            globalVariable.bossDefeated[2] = settings.getBoolean("bossDefeated3", false);
            globalVariable.bossDefeated[3] = settings.getBoolean("bossDefeated4", false);
            globalVariable.bossDefeated[4] = settings.getBoolean("bossDefeated5", false);
            globalVariable.bossDefeated[5] = settings.getBoolean("bossDefeated6", false);
            globalVariable.bossDefeated[6] = settings.getBoolean("bossDefeated7", false);
            globalVariable.bossDefeated[7] = settings.getBoolean("bossDefeated8", false);
            globalVariable.bossDefeated[8] = settings.getBoolean("bossDefeated9", false);
            globalVariable.bossDefeated[9] = settings.getBoolean("bossDefeated10", false);
        }
        else{
            editor.clear();
            globalVariable.init();
        }
        SharedPreferences settingsRecord = getSharedPreferences(PREFS_NAME_RECORD, 0);
        globalVariable.recordLv = settingsRecord.getInt("recordLv", 0);
        globalVariable.recordFloor = settingsRecord.getInt("recordFloor", 0);

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
