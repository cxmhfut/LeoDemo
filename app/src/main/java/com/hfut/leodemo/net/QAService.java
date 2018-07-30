package com.hfut.leodemo.net;

import com.hfut.leodemo.bean.QAResponse;
import com.hfut.leodemo.utils.URLUtil;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface QAService {

    /**
     * 问答系统
     *
     * @param query
     * @return
     */
    @GET(URLUtil.QASystem)
    Call<QAResponse> query(@Query("query") String query);

}
