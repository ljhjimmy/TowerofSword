package com.towerofsword.user.towerofsword;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class Loading extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);




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
