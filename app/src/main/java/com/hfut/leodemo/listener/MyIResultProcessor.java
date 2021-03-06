package com.hfut.leodemo.listener;

import com.hfut.leodemo.service.ActionServiceImpl;
import com.hfut.leodemo.service.SpeechServiceImpl;
import com.iflytek.business.speech.RecognizerResult;
import com.leo.api.LeoRobot;
import com.leo.api.LeoSpeech;
import com.leo.api.abstracts.IResultProcessor;
import com.leo.api.abstracts.ISpeakListener;
import com.leo.api.nlp.NLPResult;

public class MyIResultProcessor implements IResultProcessor {
    @Override
    public void onInit() {
        LeoSpeech.speak("Hi~，来跟我玩吧", new ISpeakListener() {
            @Override
            public void onSpeakOver(int i) {
                LeoRobot.doReset();//机器人复位
                LeoRobot.doMouthOn();//打开嘴灯
                LeoSpeech.startRecognize();//开始识别
            }
        });
    }

    @Override
    public void onPartialResult(RecognizerResult recognizerResult) {

    }

    @Override
    public void onResult(NLPResult nlpResult) {
        String post = nlpResult.getRawtext();
        LeoRobot.doMouthOff();//识别完成，关闭嘴灯

        SpeechServiceImpl speechService = SpeechServiceImpl.getInstance();
        ActionServiceImpl actionService = ActionServiceImpl.getInstance();

        actionService.setActionCallBack(new ActionServiceImpl.ActionCallBack() {
            @Override
            public void onActionComplete(String actionName, String actionResponse) {
                //动作处理完后回调函数
                LeoRobot.doAction(actionName);
                LeoSpeech.speak(actionResponse, new ISpeakListener() {
                    @Override
                    public void onSpeakOver(int i) {
                        LeoRobot.doReset();//使机器人复位
                        LeoRobot.doMouthOn();
                        LeoSpeech.startRecognize();
                    }
                });
            }
        });

        if (!actionService.doAction(post)) {
            speechService.setQueryListener(new SpeechServiceImpl.QueryListener() {
                @Override
                public void onResponse(String response) {
                    if (response.equals(SpeechServiceImpl.SHUT_UP_RESPONSE)) {
                        LeoSpeech.speak(SpeechServiceImpl.SHUT_UP_RESPONSE, null);
                    } else {
                        LeoSpeech.speak(response, new ISpeakListener() {
                            @Override
                            public void onSpeakOver(int i) {
                                LeoRobot.doMouthOn();
                                LeoSpeech.startRecognize();
                            }
                        });
                    }
                }

                @Override
                public void onFailure() {
                    LeoSpeech.speak(SpeechServiceImpl.NET_WORK_ERROR, null);
                }
            });
            speechService.query(post);
        }
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
        LeoRobot.doMouthOff();
        LeoSpeech.speak("Hi,来跟我玩吧", new ISpeakListener() {
            @Override
            public void onSpeakOver(int i) {
                LeoRobot.doMouthOn();
                LeoSpeech.startRecognize();
            }
        });
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
