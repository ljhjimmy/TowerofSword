package com.towerofsword.user.towerofsword;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Adventure extends AppCompatActivity {

    private static final int[] idBlockArray = {R.id.block1, R.id.block2, R.id.block3, R.id.block4, R.id.block5, R.id.block6, R.id.block7, R.id.block8, R.id.block9, R.id.block10,
            R.id.block11, R.id.block12, R.id.block13, R.id.block14, R.id.block15, R.id.block16, R.id.block17, R.id.block18, R.id.block19, R.id.block20,
            R.id.block21, R.id.block22, R.id.block23, R.id.block24, R.id.block25};

    private static final int[] idItemArray = {R.id.item1, R.id.item2, R.id.item3, R.id.item4, R.id.item5, R.id.item6, R.id.item7, R.id.item8, R.id.item9, R.id.item10,
            R.id.item11, R.id.item12, R.id.item13, R.id.item14, R.id.item15, R.id.item16, R.id.item17, R.id.item18, R.id.item19, R.id.item20,
            R.id.item21, R.id.item22, R.id.item23, R.id.item24, R.id.item25};

    private static final int[] chageBlockIndexArray = {-5,-1,1,5};
    private static final int[] chageBlockIndexArrayLeft = {-5,1,5};
    private static final int[] chageBlockIndexArrayRight = {-5,-1,5};

    private ImageButton[] block = new ImageButton[idBlockArray.length];
    private ImageButton[] item = new ImageButton[idItemArray.length];

    public static final int EMPTY = 0;
    public static final int SOUL = 1;
    public static final int MONEY = 2;
    public static final int MONSTER = 3;
    public static final int PORTAL = 10;
    public static final int GATE = 11;

    private int[] itemIsWhat = new int[25];

    private static final int[] idDynamicTextViewArray = {R.id.dynamicTextViewId1, R.id.dynamicTextViewId2, R.id.dynamicTextViewId3, R.id.dynamicTextViewId4, R.id.dynamicTextViewId5,
            R.id.dynamicTextViewId6, R.id.dynamicTextViewId7, R.id.dynamicTextViewId8, R.id.dynamicTextViewId9, R.id.dynamicTextViewId10};

    private static final int[] idDynamicImageViewArray = {R.id.dynamicImageViewId1, R.id.dynamicImageViewId2, R.id.dynamicImageViewId3, R.id.dynamicImageViewId4, R.id.dynamicImageViewId5,
            R.id.dynamicImageViewId6, R.id.dynamicImageViewId7, R.id.dynamicImageViewId8, R.id.dynamicImageViewId9, R.id.dynamicImageViewId10};

    private static int currentDynamicTextViewIndex = 0;
    private static int currentDynamicImageViewIndex = 0;
    private Typeface font_pixel;
    private static GlobalVariable globalVariable ;
    public static final String PREFS_NAME = "MyPrefsFile";

    TextView textViewStaminaNum;
    TextView textViewFloorNum;
    TextView textViewLevelNum;
    private int steps = 0;

    int monsterIndex = -1;
    private static final int[] idMonsters = {R.drawable.monster1, R.drawable.monster2, R.drawable.monster3, R.drawable.monster4,};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adventure);

        Button btnStatus = (Button)findViewById(R.id.btnStatus);
        font_pixel = Typeface.createFromAsset(getAssets(), "fonts/manaspace.ttf");
        btnStatus.setTypeface(font_pixel);
        btnStatus.setOnClickListener(listenerStatus);
        TextView textViewStamina = (TextView)findViewById(R.id.textViewStamina);
        textViewStamina.setTypeface(font_pixel);
        textViewStaminaNum = (TextView)findViewById(R.id.textViewStaminaNum);
        textViewStaminaNum.setTypeface(font_pixel);
        textViewFloorNum  = (TextView)findViewById(R.id.textViewFloorNum);
        textViewFloorNum.setTypeface(font_pixel);
        TextView textViewLevel = (TextView)findViewById(R.id.textViewLevel);
        textViewLevel.setTypeface(font_pixel);
        textViewLevelNum = (TextView)findViewById(R.id.textViewLevelNum);
        textViewLevelNum.setTypeface(font_pixel);

        globalVariable = (GlobalVariable) getApplicationContext();
        initVariable();

        for (int i=0; i<idBlockArray.length; i++) {
            block[i] = (ImageButton)findViewById(idBlockArray[i]);
            item[i] = (ImageButton)findViewById(idItemArray[i]);
        }
        for (int i=0; i<idBlockArray.length; i++) {
            block[i].setOnClickListener(listenerBlock) ;
            item[i].setOnClickListener(listenerItem) ;
        }
        initBlock();
        initItem();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                boolean result=data.getBooleanExtra("result",false);
                if(result){
                    globalVariable.stamina =  globalVariable.stamina-3;
                    textViewStaminaNum.setText(String.valueOf(globalVariable.stamina));
                    textViewLevelNum.setText(String.valueOf(globalVariable.lv));

                    if(monsterIndex==17){
                        item[22].setImageResource(R.drawable.door_open);
                        item[22].setClickable(true);
                    }
                    item[monsterIndex].setVisibility(View.INVISIBLE);
                }
                else{
                    globalVariable.stamina =  globalVariable.stamina-10;
                    textViewStaminaNum.setText(String.valueOf(globalVariable.stamina));
                }
            }
        }
    }

    private void gainItemAnim(int num, int item){
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.adventure_layout);
        RelativeLayout.LayoutParams params;
        RelativeLayout.LayoutParams params2;
        TextView tv;
        ImageView im;

        tv = new TextView(this);
        tv.setId(idDynamicTextViewArray[currentDynamicTextViewIndex]);
        tv.setText("+" + String.valueOf(num) );
        tv.setTextColor(Color.WHITE);
        tv.setTypeface(font_pixel);
        tv.setPadding(0,5,0,0);
        tv.setGravity(Gravity.CENTER|Gravity.END);
        tv.setTextSize(20);
        tv.setElevation(10);

        params = new RelativeLayout.LayoutParams(310, 80);
        params.addRule(RelativeLayout.ALIGN_START, R.id.btnStatus);
        params.addRule(RelativeLayout.ABOVE, R.id.btnStatus);
        relativeLayout.addView(tv, params);

        im = new ImageView(this);
        im.setId(idDynamicImageViewArray[currentDynamicImageViewIndex]);
        im.setElevation(10);
        switch(item) {
            case MONEY:
                im.setImageResource(R.drawable.coin01);
                break;
            case SOUL:
                im.setImageResource(R.drawable.soul01);
                break;
        }

        params2 = new RelativeLayout.LayoutParams(80, 80);
        params2.addRule(RelativeLayout.END_OF, idDynamicTextViewArray[currentDynamicTextViewIndex]);
        params2.addRule(RelativeLayout.ALIGN_BOTTOM, idDynamicTextViewArray[currentDynamicTextViewIndex]);
        relativeLayout.addView(im, params2);

        currentDynamicTextViewIndex++;
        currentDynamicImageViewIndex++;
        tv.startAnimation(AnimationUtils.loadAnimation(Adventure.this, R.anim.anim_gain));
        im.startAnimation(AnimationUtils.loadAnimation(Adventure.this, R.anim.anim_gain));

    }

    private void initVariable(){
        currentDynamicTextViewIndex = 0;
        currentDynamicImageViewIndex = 0;
        steps = 0;

        String floor = "FLOOR " + String.valueOf(globalVariable.currentFloor);
        textViewFloorNum.setText(floor);
        textViewStaminaNum.setText(String.valueOf(globalVariable.stamina));
        textViewLevelNum.setText(String.valueOf(globalVariable.lv));
    }

    private int monsterProbability(){
        int num = (int)(Math.random()*100);
        int result=0;
        if(num<40){
            result = 0;
        }
        else if(num>=40 && num<70){
            result = 1;
        }
        else if(num>=70 && num<90){
            result = 2;
        }
        else if(num>=90){
            result = 3;
        }
        return result;
    }

    private void initItem(){
        int count = 0;
        int num;
        int randomNum = monsterProbability();

        for(int i=0;i<25;i++){
            itemIsWhat[i] = EMPTY;
        }
        itemIsWhat[2] = PORTAL;
        itemIsWhat[22] = GATE;
        item[22].setImageResource(R.drawable.door);

        itemIsWhat[17] = MONSTER;
        item[17].setImageResource(idMonsters[randomNum]);
        while(count < 4){
            num = (int) (Math.random()*25);
            randomNum = monsterProbability();
            if(itemIsWhat[num] == EMPTY){
                itemIsWhat[num] = MONSTER;
                item[num].setImageResource(idMonsters[randomNum]);
                count++;
            }
        }

        count = 0;
        while(count < 2){
            num = (int) (Math.random()*25);
            if(itemIsWhat[num] == EMPTY){
                itemIsWhat[num] = SOUL;
                item[num].setImageResource(R.drawable.soul02);
                count++;
            }
        }

        count = 0;
        while(count < 2){
            num = (int) (Math.random()*25);
            if(itemIsWhat[num] == EMPTY){
                itemIsWhat[num] = MONEY;
                item[num].setImageResource(R.drawable.coin02);
                count++;
            }
        }

        for(int i=0;i<25;i++){
            item[i].setClickable(false);
            if(itemIsWhat[i] != EMPTY){
                item[i].setVisibility(View.VISIBLE);
            }
            else{
                item[i].setVisibility(View.INVISIBLE);
            }
        }
        item[2].setClickable(true);
    }

    private ImageButton.OnClickListener listenerItem = new ImageButton.OnClickListener(){
        public void onClick(View v) {
            int index = -1;
            int gainValue;

            for(int i=0;i<25;i++) {
                if(item[i].equals(v)){
                    index = i;
                    break;
                }
            }

            switch(itemIsWhat[index]){
                case SOUL:
                    gainValue = (int) Math.round(globalVariable.currentFloor*10 * (1.1 - Math.random()*0.21));
                    globalVariable.soul += gainValue;
                    gainItemAnim(gainValue,SOUL);
                    v.setVisibility(View.INVISIBLE);
                    break;
                case MONEY:
                    gainValue = (int) Math.round(globalVariable.currentFloor*10 * (1.1 - Math.random()*0.21));
                    globalVariable.money += gainValue;
                    gainItemAnim(gainValue,MONEY);
                    v.setVisibility(View.INVISIBLE);
                    break;
                case MONSTER:
                    monsterIndex = index;
                    for(int i=0;i<idMonsters.length;i++) {
                        if(item[index].getDrawable().getConstantState().equals
                                (getResources().getDrawable(idMonsters[i]).getConstantState())){
                            globalVariable.monsterWhich = i;
                            break;
                        }
                    }

                    Intent intent = new Intent();
                    intent.setClass(Adventure.this, Battle.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivityForResult(intent, 1);
                    overridePendingTransition(0,0);
                    break;
                case PORTAL:

                    break;
                case GATE:
                    nextFloor(1);
                    break;
                default:
            }
        }
    };

    private ImageButton.OnClickListener listenerBlock = new ImageButton.OnClickListener(){
        public void onClick(View v) {
            int index = -1;
            int[] changeArray;

            for(int i=0;i<25;i++) {
                if(block[i].equals(v)){
                    index = i;
                    break;
                }
            }

            switch(index%5){
                case 0:
                    changeArray = chageBlockIndexArrayLeft;
                    break;
                case 4:
                    changeArray = chageBlockIndexArrayRight;
                    break;
                default:
                    changeArray = chageBlockIndexArray;
            }

            for(int i=0;i<changeArray.length;i++) {
                if(index+changeArray[i]>=0 && index+changeArray[i]<25) {
                    block[index + changeArray[i]].setImageResource(R.drawable.block1);
                    block[index + changeArray[i]].setClickable(true);
                }
            }
            v.setVisibility(View.INVISIBLE);
            item[index].setClickable(true);

            steps++;
            checkSteps();
        }
    };

    private void checkSteps(){
        if (steps>=3){
            steps = steps - 3;
            globalVariable.stamina  = globalVariable.stamina-1;
            textViewStaminaNum.setText(String.valueOf(globalVariable.stamina));
        }
    }

    private Button.OnClickListener listenerStatus = new Button.OnClickListener(){
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(Adventure.this, Status.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
            overridePendingTransition(0,0);
        }
    };

    private void initBlock(){
        for(int i=0;i<block.length;i++) {
            block[i].setImageResource(R.drawable.block1_dark);
            block[i].setVisibility(View.VISIBLE);
            block[i].setClickable(false);
        }
        block[1].setImageResource(R.drawable.block1);
        block[3].setImageResource(R.drawable.block1);
        block[7].setImageResource(R.drawable.block1);
        block[1].setClickable(true);
        block[3].setClickable(true);
        block[7].setClickable(true);
        block[2].setVisibility(View.INVISIBLE);
        block[22].setVisibility(View.INVISIBLE);
    }

    private void nextFloor(int num){
        globalVariable.currentFloor += num;

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("soul", globalVariable.soul);
        editor.putInt("money", globalVariable.money);
        editor.putInt("currentFloor", globalVariable.currentFloor);
        editor.putInt("lv", globalVariable.lv);
        editor.putInt("stamina", globalVariable.stamina);
        editor.putInt("exp", globalVariable.exp);
        editor.putInt("ap", globalVariable.ap);

        editor.commit();

        initVariable();
        initBlock();
        initItem();
    }

}


