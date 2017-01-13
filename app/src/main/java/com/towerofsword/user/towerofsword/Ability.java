package com.towerofsword.user.towerofsword;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Ability extends AppCompatActivity {

    private static GlobalVariable globalVariable ;
    TextView textViewHPNum1;
    TextView textViewHPNum2;
    TextView textViewATKNum1;
    TextView textViewATKNum2;
    TextView textViewDEFNum1;
    TextView textViewDEFNum2;
    TextView textViewAGINum1;
    TextView textViewAGINum2;
    TextView textViewLUCNum1;
    TextView textViewLUCNum2;
    TextView textView_LVnum;
    TextView textView_EXPnum1;
    TextView textViewAPNum;
    TextView textViewAPNum2;

    int hp;
    int atk;
    int def;
    int agi;
    int luc;
    int ap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ability);

        globalVariable =  (GlobalVariable) getApplicationContext();

        Button buttonBack = (Button)findViewById(R.id.buttonBack);
        Button buttonAddHPHalf = (Button)findViewById(R.id.buttonAddHPHalf);
        Button buttonAddHPAll = (Button)findViewById(R.id.buttonAddHPAll);
        Button buttonAddHP = (Button)findViewById(R.id.buttonAddHP);
        Button buttonAddATKHalf = (Button)findViewById(R.id.buttonAddATKHalf);
        Button buttonAddATKAll = (Button)findViewById(R.id.buttonAddATKAll);
        Button buttonAddATK = (Button)findViewById(R.id.buttonAddATK);
        Button buttonAddDEFHalf = (Button)findViewById(R.id.buttonAddDEFHalf);
        Button buttonAddDEFAll = (Button)findViewById(R.id.buttonAddDEFAll);
        Button buttonAddDEF = (Button)findViewById(R.id.buttonAddDEF);
        Button buttonAddAGIHalf = (Button)findViewById(R.id.buttonAddAGIHalf);
        Button buttonAddAGIAll = (Button)findViewById(R.id.buttonAddAGIAll);
        Button buttonAddAGI = (Button)findViewById(R.id.buttonAddAGI);
        Button buttonAddLUCHalf = (Button)findViewById(R.id.buttonAddLUCHalf);
        Button buttonAddLUCAll = (Button)findViewById(R.id.buttonAddLUCAll);
        Button buttonAddLUC = (Button)findViewById(R.id.buttonAddLUC);
        Button buttonConfirm = (Button)findViewById(R.id.buttonConfirm);
        Button buttonCancel = (Button)findViewById(R.id.buttonCancel);

        buttonAddHP.setOnClickListener(listenerHP);
        buttonAddATK.setOnClickListener(listenerATK);
        buttonAddDEF.setOnClickListener(listenerDEF);
        buttonAddAGI.setOnClickListener(listenerAGI);
        buttonAddLUC.setOnClickListener(listenerLUC);
        buttonAddHPHalf.setOnClickListener(listenerHPHalf);
        buttonAddATKHalf.setOnClickListener(listenerATKHalf);
        buttonAddDEFHalf.setOnClickListener(listenerDEFHalf);
        buttonAddAGIHalf.setOnClickListener(listenerAGIHalf);
        buttonAddLUCHalf.setOnClickListener(listenerLUCHalf);
        buttonAddHPAll.setOnClickListener(listenerHPAll);
        buttonAddATKAll.setOnClickListener(listenerATKAll);
        buttonAddDEFAll.setOnClickListener(listenerDEFAll);
        buttonAddAGIAll.setOnClickListener(listenerAGIAll);
        buttonAddLUCAll.setOnClickListener(listenerLUCAll);

        buttonBack.setOnClickListener(listenerBack);
        buttonConfirm.setOnClickListener(listenerConfirm);
        buttonCancel.setOnClickListener(listenerCancel);

        setTextView();

    }

    public void setTextView(){
        textViewHPNum1 = (TextView)findViewById(R.id.textViewHPNum1);
        textViewHPNum2 = (TextView)findViewById(R.id.textViewHPNum2);
        textViewATKNum1 = (TextView)findViewById(R.id.textViewATKNum1);
        textViewATKNum2 = (TextView)findViewById(R.id.textViewATKNum2);
        textViewDEFNum1 = (TextView)findViewById(R.id.textViewDEFNum1);
        textViewDEFNum2 = (TextView)findViewById(R.id.textViewDEFNum2);
        textViewAGINum1 = (TextView)findViewById(R.id.textViewAGINum1);
        textViewAGINum2 = (TextView)findViewById(R.id.textViewAGINum2);
        textViewLUCNum1 = (TextView)findViewById(R.id.textViewLUCNum1);
        textViewLUCNum2 = (TextView)findViewById(R.id.textViewLUCNum2);
        textView_LVnum = (TextView)findViewById(R.id.textView_LVnum);
        textView_EXPnum1 = (TextView)findViewById(R.id.textView_EXPnum1);
        textViewAPNum= (TextView)findViewById(R.id.textViewAPNum);
        textViewAPNum2= (TextView)findViewById(R.id.textViewAPNum2);


        textView_LVnum.setText(String.valueOf(globalVariable.lv));
        textView_EXPnum1.setText(String.valueOf(globalVariable.exp));
        textViewHPNum1.setText(String.valueOf(globalVariable.playerHP));
        textViewATKNum1.setText(String.valueOf(globalVariable.playerATK));
        textViewDEFNum1.setText(String.valueOf(globalVariable.playerDEF));
        textViewAGINum1.setText(String.valueOf(globalVariable.playerAGI));
        textViewLUCNum1.setText(String.valueOf(globalVariable.playerLUC));
        textViewAPNum.setText(String.valueOf(globalVariable.ap));
    }

    private Button.OnClickListener listenerHPHalf = new Button.OnClickListener(){
        public void onClick(View v) {
            if(ap<globalVariable.ap) {
                int apInvest = (int)(Math.round((double)(globalVariable.ap - ap)/2));
                hp += apInvest;
                ap += apInvest;
                String str1 = "+" + hp*10;
                String str2 = "-" + ap;
                textViewHPNum2.setText(str1);
                textViewAPNum2.setText(str2);
            }
        }
    };

    private Button.OnClickListener listenerATKHalf = new Button.OnClickListener(){
        public void onClick(View v) {
            if(ap<globalVariable.ap) {
                int apInvest = (int)(Math.round((double)(globalVariable.ap - ap)/2));
                atk += apInvest;
                ap += apInvest;
                String str1 = "+" + atk * 5;
                String str2 = "-" + ap;
                textViewATKNum2.setText(str1);
                textViewAPNum2.setText(str2);
            }
        }
    };
    private Button.OnClickListener listenerDEFHalf = new Button.OnClickListener(){
        public void onClick(View v) {
            if(ap<globalVariable.ap) {
                int apInvest = (int)(Math.round((double)(globalVariable.ap - ap)/2));
                def += apInvest;
                ap += apInvest;
                String str1 = "+" + def * 5;
                String str2 = "-" + ap;
                textViewDEFNum2.setText(str1);
                textViewAPNum2.setText(str2);
            }
        }
    };

    private Button.OnClickListener listenerAGIHalf = new Button.OnClickListener(){
        public void onClick(View v) {
            if(ap<globalVariable.ap) {
                int apInvest = (int)(Math.round((double)(globalVariable.ap - ap)/2));
                agi += apInvest;
                ap += apInvest;
                String str1 = "+" + agi;
                String str2 = "-" + ap;
                textViewAGINum2.setText(str1);
                textViewAPNum2.setText(str2);
            }
        }
    };

    private Button.OnClickListener listenerLUCHalf = new Button.OnClickListener(){
        public void onClick(View v) {
            if(ap<globalVariable.ap) {
                int apInvest = (int)(Math.round((double)(globalVariable.ap - ap)/2));
                luc += apInvest;
                ap += apInvest;
                String str1 = "+" + luc;
                String str2 = "-" + ap;
                textViewLUCNum2.setText(str1);
                textViewAPNum2.setText(str2);
            }
        }
    };

    private Button.OnClickListener listenerHPAll = new Button.OnClickListener(){
        public void onClick(View v) {
            if(ap<globalVariable.ap) {
                int apInvest = globalVariable.ap - ap;
                hp += apInvest;
                ap += apInvest;
                String str1 = "+" + hp*10;
                String str2 = "-" + ap;
                textViewHPNum2.setText(str1);
                textViewAPNum2.setText(str2);
            }
        }
    };

    private Button.OnClickListener listenerATKAll = new Button.OnClickListener(){
        public void onClick(View v) {
            if(ap<globalVariable.ap) {
                int apInvest = globalVariable.ap - ap;
                atk += apInvest;
                ap += apInvest;
                String str1 = "+" + atk * 5;
                String str2 = "-" + ap;
                textViewATKNum2.setText(str1);
                textViewAPNum2.setText(str2);
            }
        }
    };
    private Button.OnClickListener listenerDEFAll = new Button.OnClickListener(){
        public void onClick(View v) {
            if(ap<globalVariable.ap) {
                int apInvest = globalVariable.ap - ap;
                def += apInvest;
                ap += apInvest;
                String str1 = "+" + def * 5;
                String str2 = "-" + ap;
                textViewDEFNum2.setText(str1);
                textViewAPNum2.setText(str2);
            }
        }
    };

    private Button.OnClickListener listenerAGIAll = new Button.OnClickListener(){
        public void onClick(View v) {
            if(ap<globalVariable.ap) {
                int apInvest = globalVariable.ap - ap;
                agi += apInvest;
                ap += apInvest;
                String str1 = "+" + agi;
                String str2 = "-" + ap;
                textViewAGINum2.setText(str1);
                textViewAPNum2.setText(str2);
            }
        }
    };

    private Button.OnClickListener listenerLUCAll = new Button.OnClickListener(){
        public void onClick(View v) {
            if(ap<globalVariable.ap) {
                int apInvest = globalVariable.ap - ap;
                luc += apInvest;
                ap += apInvest;
                String str1 = "+" + luc;
                String str2 = "-" + ap;
                textViewLUCNum2.setText(str1);
                textViewAPNum2.setText(str2);
            }
        }
    };

    private Button.OnClickListener listenerHP = new Button.OnClickListener(){
        public void onClick(View v) {
            if(ap<globalVariable.ap) {
                hp++;
                ap++;
                String str1 = "+" + hp*10;
                String str2 = "-" + ap;
                textViewHPNum2.setText(str1);
                textViewAPNum2.setText(str2);
            }
        }
    };

    private Button.OnClickListener listenerATK = new Button.OnClickListener(){
        public void onClick(View v) {
            if(ap<globalVariable.ap) {
                atk++;
                ap++;
                String str1 = "+" + atk * 5;
                String str2 = "-" + ap;
                textViewATKNum2.setText(str1);
                textViewAPNum2.setText(str2);
            }
        }
    };
    private Button.OnClickListener listenerDEF = new Button.OnClickListener(){
        public void onClick(View v) {
            if(ap<globalVariable.ap) {
                def++;
                ap++;
                String str1 = "+" + def * 5;
                String str2 = "-" + ap;
                textViewDEFNum2.setText(str1);
                textViewAPNum2.setText(str2);
            }
        }
    };

    private Button.OnClickListener listenerAGI = new Button.OnClickListener(){
        public void onClick(View v) {
            if(ap<globalVariable.ap) {
                agi++;
                ap++;
                String str1 = "+" + agi;
                String str2 = "-" + ap;
                textViewAGINum2.setText(str1);
                textViewAPNum2.setText(str2);
            }
        }
    };

    private Button.OnClickListener listenerLUC = new Button.OnClickListener(){
        public void onClick(View v) {
            if(ap<globalVariable.ap) {
                luc++;
                ap++;
                String str1 = "+" + luc;
                String str2 = "-" + ap;
                textViewLUCNum2.setText(str1);
                textViewAPNum2.setText(str2);
            }
        }
    };

    private Button.OnClickListener listenerConfirm = new Button.OnClickListener(){
        public void onClick(View v) {

            globalVariable.playerHP += hp*10;
            globalVariable.playerATK += atk*5;
            globalVariable.playerDEF +=def*5;
            globalVariable.playerAGI +=agi;
            globalVariable.playerLUC +=luc;
            globalVariable.ap -=ap;

            hp = 0;
            atk = 0;
            def = 0;
            agi = 0;
            luc = 0;
            ap = 0;

            textViewHPNum1.setText(String.valueOf(globalVariable.playerHP));
            textViewATKNum1.setText(String.valueOf(globalVariable.playerATK));
            textViewDEFNum1.setText(String.valueOf(globalVariable.playerDEF));
            textViewAGINum1.setText(String.valueOf(globalVariable.playerAGI));
            textViewLUCNum1.setText(String.valueOf(globalVariable.playerLUC));
            textViewAPNum.setText(String.valueOf(globalVariable.ap));

            textViewHPNum2.setText("");
            textViewATKNum2.setText("");
            textViewDEFNum2.setText("");
            textViewAGINum2.setText("");
            textViewLUCNum2.setText("");
            textViewAPNum2.setText("");
        }
    };

    private Button.OnClickListener listenerCancel = new Button.OnClickListener(){
        public void onClick(View v) {

            hp = 0;
            atk = 0;
            def = 0;
            agi = 0;
            luc = 0;
            ap = 0;

            textViewHPNum2.setText("");
            textViewATKNum2.setText("");
            textViewDEFNum2.setText("");
            textViewAGINum2.setText("");
            textViewLUCNum2.setText("");
            textViewAPNum2.setText("");
        }
    };

    private Button.OnClickListener listenerBack = new Button.OnClickListener(){
        public void onClick(View v) {
            finish();
            overridePendingTransition(0, 0);
        }
    };





}


