package com.hfut.leodemo.service;

public interface SpeechService {
    /**
     * 问答方法
     *
     * @param post
     * @return
     */
    String ask(String post);

    /**
     * 服务器问答方法
     *
     * @param post
     */
    void query(String post);

    /**
     * 加载资源
     */
    void loadResources();
}
