package com.hfut.leodemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hfut.leodemo.Listener.MyIResultProcessor;
import com.hfut.leodemo.Listener.MyIRobotListener;
import com.leo.api.LeoRobot;
import com.leo.api.LeoSpeech;
import com.leo.api.version.VersionManager;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //组件注入器与activity进行绑定
        ButterKnife.bind(this);
        //设置机器人版本
        VersionManager.setVersion(VersionManager.ONE);
        initLeo();//初始化Leo机器人
    }

    /**
     * 初始化Leo机器人
     */
    public void initLeo() {
        //初始化动作库服务 MyIRobotListener:动作执行回调监听类
        LeoRobot.init(this, new MyIRobotListener());
        //初始化语音服务 MyIResultProcessor:语音执行回调监听类
        LeoSpeech.init(this,new MyIResultProcessor());
    }

    @Override
    protected void onDestroy() {
        LeoRobot.doReset();
        LeoSpeech.release();
        super.onDestroy();
    }
}
