package com.hfut.leodemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hfut.leodemo.Listener.MyIResultProcessor;
import com.hfut.leodemo.Listener.MyIRobotListener;
import com.leo.api.LeoRobot;
import com.leo.api.LeoSpeech;
import com.leo.api.version.VersionManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_base_demo)
    Button btnBaseDemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //组件注入器与activity进行绑定
        ButterKnife.bind(this);
        //设置机器人版本
        VersionManager.setVersion(VersionManager.ONE);
        initLeo();//初始化Leo机器人

        btnBaseDemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToBaseDemo();
            }
        });
    }

    /**
     * 初始化Leo机器人
     */
    public void initLeo() {
        //初始化动作库服务 MyIRobotListener:动作执行回调监听类
        LeoRobot.init(this, new MyIRobotListener());
        //初始化语音服务 MyIResultProcessor:语音执行回调监听类
        LeoSpeech.init(this, new MyIResultProcessor());
    }

    /**
     * 跳转到BaseDemoActivity
     */
    public void goToBaseDemo() {
        Intent intent = new Intent(MainActivity.this, BaseDemoActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        LeoRobot.doReset();
        LeoSpeech.release();
        super.onDestroy();
    }
}
