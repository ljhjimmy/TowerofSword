package com.towerofsword.user.towerofsword;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Battle extends AppCompatActivity {

    int currentFloor;
    int playerHPPercentage = 100;
    int monsterHPPercentage = 100;
    int playerHP = 100;
    int playerHPMax = 100;
    int monsterHP = 300;
    int monsterHPMax = 300;
    int playerDEF = 50;
    int playerDEFPercentage = 0;
    int playerATK = 50;
    int monsterATK = 10;
    int playerDMG = 0;
    int monsterDMG = 0;

    int money = 100;
    int soul = 100;
    int exp = 1599;

    double[] HPCoefficient = {1.0 ,1.15, 1.3, 1.5, 2.0, 2.5};
    double[] ATKCoefficient = {1.0 ,1.15, 1.3, 1.5, 2.0, 2.5};
    double[] moneyCoefficient = {1.0 ,1.2, 1.4, 1.6, 2.0, 2.5};
    double[] soulCoefficient = {1.0 ,1.2, 1.4, 1.6, 2.0, 2.5};
    double[] expCoefficient = {1.0 ,1.3, 1.6, 2.0, 2.0, 2.5};

    int monsterWhich;

    private static  AnimationDrawable ani;
    private static  AnimationDrawable ani2;
    private static int aniDuration;
    private static int ani2Duration;
    private static boolean touched = false;
    private static boolean battleIsEnd = false;
    private static boolean playerWin = false;

    Thread AnimationThread ;
    private Typeface font_pixel;
    private TextView textViewPlayerDamage;
    private TextView textViewMonsterDamage;
    private ImageView imageViewPlayerHp;
    private ViewGroup.LayoutParams params;
    private ImageView imageViewMonsterHP;
    private ViewGroup.LayoutParams params2;
    private TextView textViewPlayerHPNum;

    private static GlobalVariable globalVariable ;
    ImageView imageViewMonster;
    private static final int[] idMonsters = {R.drawable.monster1, R.drawable.monster2, R.drawable.monster3, R.drawable.monster4,R.drawable.slime_small,R.drawable.slime_big};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);
        globalVariable = (GlobalVariable) getApplicationContext();
        battleIsEnd = false;
        touched = false;
        playerWin = false;
        ani = (AnimationDrawable)getResources().getDrawable(R.drawable.anim_attack);
        ani2 = (AnimationDrawable)getResources().getDrawable(R.drawable.anim_monster_attack);
        aniDuration = ani.getNumberOfFrames()*ani.getDuration(0);
        ani2Duration = ani2.getNumberOfFrames()*ani2.getDuration(0);

        init();

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

        imageViewMonster = (ImageView)findViewById(R.id.imageViewMonster);
        imageViewMonster.setImageResource(idMonsters[monsterWhich]);

        textViewPlayerHPNum.setText(playerHP + "/" + playerHPMax );
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                battleEnd();
            }
        }
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
                        Thread.sleep(500);
                        battleResult();
                        break;
                    }

                    mHandler.sendEmptyMessage(2);
                    Thread.sleep(ani2Duration);
                    if(battleIsEnd){
                        Thread.sleep(500);
                        battleResult();
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void init(){
        playerHP = globalVariable.playerHP;
        playerHPMax = playerHP;
        playerATK = globalVariable.playerATK;
        playerDEF = globalVariable.playerDEF;
        monsterWhich = globalVariable.monsterWhich;
        currentFloor = globalVariable.currentFloor;

        if(playerDEF > currentFloor*20){
            int remainDEF = playerDEF - currentFloor*20;
            if(remainDEF <= 100){
                playerDEFPercentage =10 +(int) ((double)(remainDEF)*10/100);
            }
            else if(remainDEF > 100 && remainDEF <=500){
                playerDEFPercentage =20 +(int) ((double)(remainDEF-100)*10/400);
            }
            else if(remainDEF > 500 && remainDEF <=1500){
                playerDEFPercentage =30 +(int) ((double)(remainDEF-500)*10/1000);
            }
            else if(remainDEF > 1500 && remainDEF <= 5000){
                playerDEFPercentage =40  +(int) ((double)(remainDEF-1500)*10/3500);
            }
            else{
                playerDEFPercentage =50;
            }
        }
        else{
            playerDEFPercentage = 0;
        }

        monsterHP = (int) Math.round(currentFloor * (200 + (int)((double)currentFloor/5)*50 + (int)((double)currentFloor/10)*100 + (int)((double)currentFloor/11)*200 + (int)((double)currentFloor/15)*300)
                * (1.2 - Math.random()*0.41) * HPCoefficient[monsterWhich]);
        monsterHPMax = monsterHP;

        money = (int) Math.round(currentFloor * 10 * (1.1 - Math.random()*0.21) * moneyCoefficient[monsterWhich]);
        soul = (int) Math.round(currentFloor * 10 * (1.1 - Math.random()*0.21) * soulCoefficient[monsterWhich]);
        exp = (int) Math.round(currentFloor * 55 * (1.1 - Math.random()*0.21) * expCoefficient[monsterWhich]);
    }

    private void battle(int who){
        int[] damageRotation = {-10,0,10};
        int randomNum = (int)(Math.random()*3);

        if(who==1) {
            playerDMG =  (int) Math.round(playerATK *(1.2 - Math.random()*0.41));
            textViewPlayerDamage.setText(String.valueOf(playerDMG));
            monsterHP = monsterHP - playerDMG;
            monsterHPPercentage = (int) ((double) monsterHP * 100 / monsterHPMax);

            hitMonsterAnim();
            textViewPlayerDamage.setRotation(damageRotation[randomNum]);
            textViewPlayerDamage.startAnimation(AnimationUtils.loadAnimation(Battle.this, R.anim.anim_damage));

            if (monsterHP <= 0) {
                params2.width = 0;
                battleIsEnd = true;
                playerWin = true;

                imageViewMonster.startAnimation(AnimationUtils.loadAnimation(Battle.this, R.anim.anim_monster_vanish));
                ImageView imageViewMonsterHPBorder = (ImageView)findViewById(R.id.imageViewMonsterHPBorder);
                imageViewMonsterHPBorder.setVisibility(View.INVISIBLE);

            }
            else{
                params2.width = convertDpToPixel((int) (monsterHPPercentage * 2.5), this);
            }
            imageViewMonsterHP.setLayoutParams(params2);
        }
        else if (who ==2){
            monsterATK = (int) Math.round(currentFloor * (8 + (int)((double)currentFloor/5)*5 + (int)((double)currentFloor/10)*8 + (int)((double)currentFloor/11)*10 + (int)((double)currentFloor/15)*10 )
                    * (1.2 - Math.random()*0.41) * ATKCoefficient[monsterWhich]);
            monsterDMG = (int) Math.round(monsterATK * (1 - playerDEFPercentage*0.01));
            textViewMonsterDamage.setText(String.valueOf(monsterDMG));
            playerHP = playerHP - monsterDMG;
            playerHPPercentage = (int)((double)playerHP*100/playerHPMax);
            textViewPlayerHPNum.setText(playerHP + "/" + playerHPMax );

            hitCharacterAnim();
            textViewMonsterDamage.setRotation(damageRotation[randomNum]);
            textViewMonsterDamage.startAnimation(AnimationUtils.loadAnimation(Battle.this, R.anim.anim_damage));

            if(playerHP<=0){
                textViewPlayerHPNum.setText("0/" + playerHPMax );
                params.width = 0;
                battleIsEnd = true;
                playerWin = false;
            }
            else{
                params.width= convertDpToPixel(playerHPPercentage*2,this);
            }
            imageViewPlayerHp.setLayoutParams(params);
        }

    }

    private void battleResult(){
        Intent intent = new Intent();
        intent.setClass(Battle.this,BattleResult.class);
        intent.putExtra("money",money);
        intent.putExtra("soul",soul);
        intent.putExtra("exp",exp);
        intent.putExtra("playerWin",playerWin);
        startActivityForResult(intent, 1);
        overridePendingTransition(0,0);
    }

    private void battleEnd(){
        boolean result = playerWin;
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result",result);
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
        overridePendingTransition(0,0);
   }

    public static int convertDpToPixel(int dp, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        int px =(int)( dp * ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }
}


