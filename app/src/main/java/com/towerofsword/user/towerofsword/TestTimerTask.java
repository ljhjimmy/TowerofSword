package com.towerofsword.user.towerofsword;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.Timer;
import java.util.TimerTask;

public class TestTimerTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_loading);

        Timer timer = new Timer(true);
        timer.schedule(new timerTask(),3000,2000);

    }

    public class timerTask extends TimerTask {

        public void run()
        {
            Intent intent = new Intent();
            intent.setClass(TestTimerTask.this, MainActivity.class);

            startActivity(intent);
            this.cancel();
            TestTimerTask.this.finish();
        }
    }
}
