package com.towerofsword.user.towerofsword;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Battle extends AppCompatActivity {

    int playerHPPercentage = 100;
    int monsterHPPercentage = 100;
    int playerHP = 100;
    int playerHPMax = 100;
    int monsterHP = 300;
    int monsterHPMax = 300;
    int playerATK = 90;
    int monsterATK = 10;
    int playerDMG = 0;
    int monsterDMG = 0;
    private static  AnimationDrawable ani;
    private static  AnimationDrawable ani2;
    private static int aniDuration;
    private static int ani2Duration;
    private static boolean touched = false;
    private static boolean battleIsEnd = false;

    Thread AnimationThread ;
    private Typeface font_pixel;
    private TextView textViewPlayerDamage;
    private TextView textViewMonsterDamage;
    private ImageView imageViewPlayerHp;
    private ViewGroup.LayoutParams params;
    private ImageView imageViewMonsterHP;
    private ViewGroup.LayoutParams params2;
    private TextView textViewPlayerHPNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);
        battleIsEnd = false;
        touched = false;
        ani = (AnimationDrawable)getResources().getDrawable(R.drawable.anim_attack);
        ani2 = (AnimationDrawable)getResources().getDrawable(R.drawable.anim_monster_attack);
        aniDuration = ani.getNumberOfFrames()*ani.getDuration(0);
        ani2Duration = ani2.getNumberOfFrames()*ani2.getDuration(0);

        font_pixel = Typeface.createFromAsset(getAssets(), "fonts/manaspace.ttf");
        textViewPlayerDamage = (TextView)findViewById(R.id.textViewPlayerDamage);
        textViewPlayerDamage.setTypeface(font_pixel);
        textViewMonsterDamage = (TextView)findViewById(R.id.textViewMonsterDamage);
        textViewMonsterDamage.setTypeface(font_pixel);

        imageViewPlayerHp = (ImageView)findViewById(R.id.imageViewPlayerHP);
        params = imageViewPlayerHp.getLayoutParams();
        imageViewMonsterHP = (ImageView)findViewById(R.id.imageViewMonsterHP);
        params2 = imageViewMonsterHP.getLayoutParams();
        textViewPlayerHPNum = (TextView)findViewById(R.id.textViewPlayerHPNum);
        textViewPlayerHPNum.setTypeface(font_pixel);


    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        AnimationThread.interrupt();
        if (AnimationThread.isAlive()){
            Log.v("test", "it is alive .");
        }
        else{
            Log.v("test", "it is dead.");
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(!touched) {
            AnimationThread = new AnimationThread();
            AnimationThread.start();
            touched = true;
        }
        return super.onTouchEvent(event);
    }

    private void hitMonsterAnim(){
        ImageView imageViewMonsterAnim = (ImageView)findViewById(R.id.imageViewMonsterAnim);
        imageViewMonsterAnim.setImageDrawable(null);
        imageViewMonsterAnim.setImageDrawable(ani);
        ani.setAlpha(230);
        ani.start();

    }

    private void hitCharacterAnim(){
        ImageView imageViewCharacterAnim = (ImageView)findViewById(R.id.imageViewCharacterAnim);
        imageViewCharacterAnim.setImageDrawable(null);
        imageViewCharacterAnim.setImageDrawable(ani2);
        ani2.setAlpha(230);
        ani2.start();

    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    battle(1);
                    break;
                case 2:
                    battle(2);
                    break;
            }
        }
    };

    class AnimationThread extends Thread {

        @Override
        public void run() {

            super.run();
            try {
                while(!battleIsEnd) {
                    mHandler.sendEmptyMessage(1);
                    Thread.sleep(aniDuration);

                    if(battleIsEnd){
                        finish();
                        break;
                    }

                    mHandler.sendEmptyMessage(2);
                    Thread.sleep(ani2Duration);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void battle(int who){
        int[] damageRotation = {-10,0,10};
        int randomNum = (int)(Math.random()*3);

        if(who==1) {
            playerDMG = playerATK;
            textViewPlayerDamage.setText(String.valueOf(playerDMG));
            monsterHP = monsterHP - playerDMG;
            monsterHPPercentage = (int) ((double) monsterHP * 100 / monsterHPMax);

            hitMonsterAnim();
            textViewPlayerDamage.setRotation(damageRotation[randomNum]);
            textViewPlayerDamage.startAnimation(AnimationUtils.loadAnimation(Battle.this, R.anim.anim_damage));

            if (monsterHP <= 0) {
                params2.width = 0;
                battleIsEnd = true;
            }
            else{
                params2.width = convertDpToPixel((int) (monsterHPPercentage * 2.5), this);
            }
            imageViewMonsterHP.setLayoutParams(params2);
        }
        else if (who ==2){
            monsterDMG = monsterATK;
            textViewMonsterDamage.setText(String.valueOf(monsterDMG));
            playerHP = playerHP - monsterDMG;
            playerHPPercentage = (int)((double)playerHP*100/playerHPMax);
            textViewPlayerHPNum.setText(playerHP + "/" + playerHPMax );

            hitCharacterAnim();
            textViewMonsterDamage.setRotation(damageRotation[randomNum]);
            textViewMonsterDamage.startAnimation(AnimationUtils.loadAnimation(Battle.this, R.anim.anim_damage));

            if(playerHP<=0){
                params.width = 0;
                battleIsEnd = true;
            }
            else{
                params.width= convertDpToPixel(playerHPPercentage*2,this);
            }
            imageViewPlayerHp.setLayoutParams(params);
        }

    }

    public static int convertDpToPixel(int dp, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        int px =(int)( dp * ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }
}


