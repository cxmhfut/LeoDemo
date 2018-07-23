package com.hfut.leodemo.service;

public interface ActionService {

    /**
     * 根据输入完成相关回复
     *
     * @param post
     * @return
     */
    boolean doAction(String post);

    /**
     * 加载资源
     */
    void loadResources();
}
