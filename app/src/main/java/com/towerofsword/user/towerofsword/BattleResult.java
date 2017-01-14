package com.towerofsword.user.towerofsword;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class BattleResult extends Activity {

    TextView textViewMoneyResultNum;
    TextView textViewSoulResultNum;
    TextView textViewExpResultNum;
    TextView textViewLvResultNum;
    TextView textViewLevelUp;
    TextView textViewStaminaResultNum;

    Intent intent;

    double exp;
    double lv;
    double lvup;
    double expEach;
    double lvEach;
    double expnum;
    String lvString;

    GlobalVariable globalVariable ;
    ResultThread resultthread;
    boolean canTouch = false;
    boolean playerWin = false;

    ImageView imageViewExpResultBorder;
    ImageView imageViewExpResult;
    private ViewGroup.LayoutParams params;
    double expLength;
    TextView textViewExpResultNum2;
    String expStr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_result);
        intent = this.getIntent();
        globalVariable = (GlobalVariable) getApplicationContext();
        canTouch = false;

        textViewMoneyResultNum = (TextView)findViewById(R.id.textViewMoneyResultNum);
        textViewSoulResultNum = (TextView)findViewById(R.id.textViewSoulResultNum);
        textViewExpResultNum = (TextView)findViewById(R.id.textViewExpResultNum);
        textViewLvResultNum = (TextView)findViewById(R.id.textViewLvResultNum);
        textViewLevelUp = (TextView)findViewById(R.id.textViewLevelUp);
        textViewStaminaResultNum = (TextView)findViewById(R.id.textViewStaminaResultNum);

        TextView textViewMoneyResult = (TextView)findViewById(R.id.textViewMoneyResult);
        TextView textViewSoulResult = (TextView)findViewById(R.id.textViewSoulResult);
        TextView textViewExpResult = (TextView)findViewById(R.id.textViewExpResult);
        TextView textViewLvResult = (TextView)findViewById(R.id.textViewLvResult);
        TextView textViewStaminaResult = (TextView)findViewById(R.id.textViewStaminaResult);
        TextView textViewResult = (TextView)findViewById(R.id.textViewResult);

        Typeface font_pixel = Typeface.createFromAsset(getAssets(), "fonts/manaspace.ttf");
        textViewMoneyResultNum.setTypeface(font_pixel);
        textViewSoulResultNum.setTypeface(font_pixel);
        textViewExpResultNum.setTypeface(font_pixel);
        textViewLvResultNum.setTypeface(font_pixel);
        textViewLevelUp.setTypeface(font_pixel);
        textViewStaminaResultNum.setTypeface(font_pixel);
        textViewMoneyResult.setTypeface(font_pixel);
        textViewSoulResult.setTypeface(font_pixel);
        textViewExpResult.setTypeface(font_pixel);
        textViewLvResult.setTypeface(font_pixel);
        textViewStaminaResult.setTypeface(font_pixel);
        textViewResult.setTypeface(font_pixel);

        boolean playerWin = intent.getBooleanExtra("playerWin",true);
        if(playerWin) {
            textViewMoneyResultNum.setVisibility(View.INVISIBLE);
            textViewSoulResultNum.setVisibility(View.INVISIBLE);
            textViewExpResultNum.setVisibility(View.INVISIBLE);
            textViewLvResultNum.setVisibility(View.INVISIBLE);
            textViewStaminaResultNum.setVisibility(View.INVISIBLE);
            textViewLevelUp.setVisibility(View.INVISIBLE);

            exp = (double) intent.getIntExtra("exp", 1);
            expnum = (exp + globalVariable.exp) % 50;
            lv = (exp + globalVariable.exp - expnum) / 50;
            expEach = exp / 100;
            lvEach = lv / 100;
            lvup = 0;

            resultthread = new ResultThread();
            resultthread.start();
        }
        else{
            canTouch = true;
            textViewMoneyResultNum.setText("0");
            textViewSoulResultNum.setText("0");
            textViewExpResultNum.setText("0");
            textViewLevelUp.setText("");
            textViewLvResultNum.setText(String.valueOf(globalVariable.lv));
            textViewStaminaResultNum.setText("-10");
        }

        imageViewExpResultBorder = (ImageView)findViewById(R.id.imageViewExpResultBorder);
        imageViewExpResult = (ImageView)findViewById(R.id.imageViewExpResult);

        params = imageViewExpResult.getLayoutParams();
        expLength = (double)globalVariable.exp;
        params.width = convertDpToPixel((int)(expLength*4),this);
        imageViewExpResult.setLayoutParams(params);

        textViewExpResultNum2 = (TextView)findViewById(R.id.textViewExpResultNum2);
        textViewExpResultNum2.setTypeface(font_pixel);
        String str = globalVariable.exp + "/50";
        textViewExpResultNum2.setText(str);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        if(playerWin) {
            resultthread.interrupt();
            if (resultthread.isAlive()){
                Log.v("test", "resultthread is alive .");
            }
            else{
                Log.v("test", "resultthread is dead.");
            }
        }
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    textViewMoneyResultNum.setText(String.valueOf(intent.getIntExtra("money",1)));
                    textViewMoneyResultNum.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    textViewSoulResultNum.setText(String.valueOf(intent.getIntExtra("soul",1)));
                    textViewSoulResultNum.setVisibility(View.VISIBLE);
                    break;
                case 3:
                    textViewExpResultNum.setText(String.valueOf(intent.getIntExtra("exp",1)));
                    textViewExpResultNum.setVisibility(View.VISIBLE);
                    break;
                case 4:
                    textViewLevelUp.setText("0 level up!");
                    textViewLevelUp.setVisibility(View.VISIBLE);
                    break;
                case 5:
                    exp = exp-expEach;
                    lvup +=lvEach;
                    lvString = String.valueOf((int)Math.round(lvup)) + " level up!";
                    textViewExpResultNum.setText(String.valueOf((int)(exp)));
                    textViewLevelUp.setText(lvString);

                    expLength += expEach;
                    if(expLength >= 50){
                        expLength -= 50;
                    }
                    params.width = convertDpToPixel((int)(expLength*4),BattleResult.this);
                    imageViewExpResult.setLayoutParams(params);

                    expStr = (int)expLength + "/50";
                    textViewExpResultNum2.setText(expStr);

                    break;
                case 6:
                    globalVariable.lv += (int)lv;
                    globalVariable.exp = (int)expnum;
                    globalVariable.ap += (int)lv*5;
                    globalVariable.money += intent.getIntExtra("money",1);
                    globalVariable.soul += intent.getIntExtra("soul",1);

                    textViewLvResultNum.setText(String.valueOf(globalVariable.lv));
                    textViewLvResultNum.setVisibility(View.VISIBLE);
                    textViewStaminaResultNum.setVisibility(View.VISIBLE);
                    break;

            }
        }
    };

    class ResultThread extends Thread {

        @Override
        public void run() {

            super.run();
            try {
                Thread.sleep(300);
                mHandler.sendEmptyMessage(1);
                Thread.sleep(300);
                mHandler.sendEmptyMessage(2);
                Thread.sleep(300);
                mHandler.sendEmptyMessage(3);
                Thread.sleep(300);
                mHandler.sendEmptyMessage(4);

                for(int i =0;i<100;i++){
                    Thread.sleep(20);
                    mHandler.sendEmptyMessage(5);
                }
                Thread.sleep(300);
                mHandler.sendEmptyMessage(6);
                canTouch = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(canTouch) {
            Intent returnIntent = new Intent();
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        }
        return super.onTouchEvent(event);
    }

    public static int convertDpToPixel(int dp, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        int px =(int)( dp * ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }
}