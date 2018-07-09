package com.hfut.leodemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.leo.api.LeoRobot;
import com.leo.api.LeoSpeech;
import com.leo.api.abstracts.ISpeakListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BaseDemoActivity extends AppCompatActivity {

    @BindView(R.id.et_action)
    EditText mEtAction;
    @BindView(R.id.et_speak)
    EditText mEtSpeak;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basedemo);
        ButterKnife.bind(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_action: {
                String action = mEtAction.getText().toString();
                LeoRobot.doAction(action);
                break;
            }
            case R.id.btn_speak: {
                LeoRobot.doEyesOn();
                String word = mEtSpeak.getText().toString();
                LeoSpeech.speak(word, new ISpeakListener() {

                    @Override
                    public void onSpeakOver(int arg0) {
                        LeoRobot.doEyesOff();
                        Log.d("demo", "speak over");
                    }
                });
                break;
            }
            case R.id.btn_rcgn: {
                LeoSpeech.speakAndRestartRecognise("开始识别");
                break;
            }
            case R.id.btn_sleep:
                LeoRobot.doSleep();
                break;
            case R.id.btn_reset:
                LeoRobot.doReset();
                break;
            case R.id.btn_mouth_on:
                LeoRobot.doMouthOn();
                break;
            case R.id.btn_mouth_off:
                LeoRobot.doMouthOff();
                break;
            case R.id.btn_eyes_on:
                LeoRobot.doEyesOn();
                break;
            case R.id.btn_eyes_blink:
                LeoRobot.doEyesBlink();
                break;
            case R.id.btn_eyes_dizzy:
                LeoRobot.doEyesDizzy();
                break;
            case R.id.btn_eyes_off:
                LeoRobot.doEyesOff();
                break;
            case R.id.btn_turn_gear:
                LeoRobot.turnGear(2, 90, 1000);
                break;
            case R.id.btn_forward:
                LeoRobot.doAction("forward");
                break;
        }
    }
}
