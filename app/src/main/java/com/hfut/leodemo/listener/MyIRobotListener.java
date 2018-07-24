package com.hfut.leodemo.listener;

import com.leo.api.LeoRobot;
import com.leo.api.LeoSpeech;
import com.leo.api.abstracts.IRobotListener;

public class MyIRobotListener implements IRobotListener {
    @Override
    public void onTouch() {
        //摸头复位
        LeoRobot.doReset();
        LeoSpeech.startRecognize();
    }

    @Override
    public void getElect(int i) {

    }

    @Override
    public void onActionStop() {

    }

    @Override
    public void getWifiInfo(String s, String s1, int i) {

    }

    @Override
    public void getBoardVersion(String s) {

    }
}
