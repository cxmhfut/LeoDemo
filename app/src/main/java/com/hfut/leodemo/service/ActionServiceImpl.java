package com.hfut.leodemo.service;

import com.hfut.leodemo.R;
import com.hfut.leodemo.app.LeoDemoApplication;

import java.util.regex.Pattern;

public class ActionServiceImpl implements ActionService {

    private static ActionServiceImpl instance;
    private static String actionPattern[];
    private static String actionResponse[];
    private static String actionCommand[];

    private ActionCallBack actionCallBack;

    public interface ActionCallBack {
        void onActionComplete(String actionName, String actionResponse);
    }

    private ActionServiceImpl() {

    }

    public static ActionServiceImpl getInstance() {
        if (instance == null) {
            synchronized (ActionServiceImpl.class) {
                if (instance == null) {
                    instance = new ActionServiceImpl();
                }
            }
        }

        return instance;
    }

    public void setActionCallBack(ActionCallBack actionCallBack) {
        this.actionCallBack = actionCallBack;
    }

    @Override
    public void loadResources() {
        if (actionPattern == null || actionResponse == null || actionCommand == null) {
            LeoDemoApplication app = LeoDemoApplication.getInstance();
            actionPattern = app.getResources().getStringArray(R.array.ACTION_PATTERN);
            actionResponse = app.getResources().getStringArray(R.array.ACTION_RESPONSE);
            actionCommand = app.getResources().getStringArray(R.array.ACTION_COMMAND);
        }
    }

    @Override
    public boolean doAction(String post) {

        loadResources();

        for (int i = 0; i < actionPattern.length; i++) {
            boolean isMatch = Pattern.matches(actionPattern[i], post);
            if (isMatch) {
                if (actionCallBack != null) {
                    actionCallBack.onActionComplete(actionCommand[i], actionResponse[i]);
                }
                return true;
            }
        }

        return false;
    }


}
