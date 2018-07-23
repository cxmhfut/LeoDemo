package com.hfut.leodemo.service;

import com.hfut.leodemo.R;
import com.hfut.leodemo.app.LeoDemoApplication;

import java.util.regex.Pattern;

public class SpeechServiceImpl implements SpeechService {

    private static SpeechServiceImpl instance;
    private String postPattern[];
    private String response[];

    private SpeechServiceImpl() {

    }

    public static SpeechServiceImpl getInstance() {
        if (instance == null) {
            synchronized (SpeechServiceImpl.class) {
                if (instance == null) {
                    instance = new SpeechServiceImpl();
                }
            }
        }

        return instance;
    }

    @Override
    public void loadResources() {
        if (postPattern == null || response == null) {
            LeoDemoApplication app = LeoDemoApplication.getInstance();
            postPattern = app.getResources().getStringArray(R.array.POST_PATTERN);
            response = app.getResources().getStringArray(R.array.RESPONSE);
        }
    }

    @Override
    public String ask(String post) {
        loadResources();

        for (int i = 0; i < postPattern.length; i++) {
            boolean isMatch = Pattern.matches(postPattern[i], post);
            if (isMatch) {
                return response[i];
            }
        }

        return null;
    }
}
