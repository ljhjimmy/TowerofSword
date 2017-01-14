package com.towerofsword.user.towerofsword;

import android.app.Application;


public class GlobalVariable extends Application {
    int money = 0;
    int soul = 0;
    int currentFloor = 1;

    int stamina = 150;
    int lv = 1;
    int exp = 0;
    int ap = 0;

    int playerHP = 100;
    int playerATK = 50;
    int playerDEF = 50;
    int playerAGI = 0;
    int playerLUC = 0;

    int monsterWhich = 0;

    boolean isPlaying = false;
    boolean victory = false;
    boolean BossBattle = false;
    boolean[] bossDefeated = {false,false,false,false,false,false,false,false,false,false};

    int maxFloor = 1;
    int maxLv = 1;

    int recordFloor = 1;
    int recordLv = 1;


    public void init(){
        isPlaying = false;
        money = 0;
        soul = 0;
        currentFloor=1;

        stamina = 150;
        lv = 1;
        exp = 0;
        ap = 0;

        playerHP = 100;
        playerATK = 50;
        playerDEF = 50;
        playerAGI = 0;
        playerLUC = 0;

        for(int i=0;i<10;i++){
            bossDefeated[i] = false;
        }
    }
}
