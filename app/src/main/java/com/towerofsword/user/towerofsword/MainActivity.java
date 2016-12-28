package com.towerofsword.user.towerofsword;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton start_button = (ImageButton)findViewById(R.id.start_button);
        ImageButton btntap = (ImageButton)findViewById(R.id.taptostart);

        btntap.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.alpha));

        btntap.setOnClickListener(start_button_listener);
        start_button.setOnClickListener(start_button_listener);
    }

    private Button.OnClickListener start_button_listener = new Button.OnClickListener(){
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, home.class);
            startActivity(intent);
        }
    };


}
