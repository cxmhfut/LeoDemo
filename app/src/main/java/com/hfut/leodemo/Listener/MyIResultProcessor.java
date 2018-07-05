package com.hfut.leodemo.Listener;

import com.iflytek.business.speech.RecognizerResult;
import com.leo.api.LeoSpeech;
import com.leo.api.abstracts.IResultProcessor;
import com.leo.api.nlp.NLPResult;

public class MyIResultProcessor implements IResultProcessor {
    @Override
    public void onInit() {
        LeoSpeech.speak("语音服务初始化成功", null);
    }

    @Override
    public void onPartialResult(RecognizerResult recognizerResult) {

    }

    @Override
    public void onResult(NLPResult nlpResult) {

    }

    @Override
    public void handleResult(String s, String s1) {

    }

    @Override
    public void handleCmd(String s) {

    }

    @Override
    public String onLoaclRecResult(String s) {
        return null;
    }

    @Override
    public void onError(int i) {

    }

    @Override
    public void clearTask() {

    }

    @Override
    public void onSwitchOK() {

    }

    @Override
    public boolean isBusy() {
        return false;
    }

    @Override
    public void reset() {

    }
}
