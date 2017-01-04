package com.towerofsword.user.towerofsword;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

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
    private static GlobalVariable globalVariable ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adventure);
        globalVariable = (GlobalVariable) getApplicationContext();

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

        Button btnStatus = (Button)findViewById(R.id.btnStatus);
        btnStatus.setOnClickListener(listenerStatus);
    }

    public void initItem(){
        int count = 0;
        int num;

        for(int i=0;i<25;i++){
            itemIsWhat[i] = EMPTY;
        }
        itemIsWhat[2] = PORTAL;
        itemIsWhat[22] = GATE;

        while(count < 5){
            num = (int) (Math.random()*25);
            if(itemIsWhat[num] == EMPTY){
                itemIsWhat[num] = MONSTER;
                item[num].setImageResource(R.drawable.monster1);
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
    }

    private ImageButton.OnClickListener listenerItem = new ImageButton.OnClickListener(){
        public void onClick(View v) {
            int index = -1;

            for(int i=0;i<25;i++) {
                if(item[i].equals(v)){
                    index = i;
                    break;
                }
            }

            switch(itemIsWhat[index]){
                case SOUL:
                    globalVariable.soul += globalVariable.currentFloor*10;
                    v.setVisibility(View.INVISIBLE);
                    break;
                case MONEY:
                    globalVariable.money += globalVariable.currentFloor*10;
                    v.setVisibility(View.INVISIBLE);
                    break;
                case MONSTER:
                    v.setVisibility(View.INVISIBLE);
                    break;
                case PORTAL:

                    break;
                case GATE:

                    break;
                default:
            }
        }
    };

    private ImageButton.OnClickListener listenerBlock = new ImageButton.OnClickListener(){
        public void onClick(View v) {
            int index = -1;
            int[] changearray;
            v.setVisibility(View.INVISIBLE);

            for(int i=0;i<25;i++) {
                if(block[i].equals(v)){
                    index = i;
                    break;
                }
            }

            switch(index%5){
                case 0:
                    changearray = chageBlockIndexArrayLeft;
                    break;
                case 4:
                    changearray = chageBlockIndexArrayRight;
                    break;
                default:
                    changearray = chageBlockIndexArray;
            }

            for(int i=0;i<changearray.length;i++) {
                if(index+changearray[i]>=0 && index+changearray[i]<25) {
                    block[index + changearray[i]].setImageResource(R.drawable.block1);
                    block[index + changearray[i]].setClickable(true);
                }
            }
        }
    };


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

}


