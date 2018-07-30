package com.hfut.leodemo.service;

import com.hfut.leodemo.R;
import com.hfut.leodemo.app.LeoDemoApplication;
import com.hfut.leodemo.bean.QAResponse;
import com.hfut.leodemo.net.QAService;
import com.hfut.leodemo.utils.URLUtil;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SpeechServiceImpl implements SpeechService {

    public static final String SHUT_UP_RESPONSE = "好的，我用胶带把嘴封上，要想恢复请摸摸我的头";
    public static final String NET_WORK_ERROR = "网络连接失败，请检查网络";

    private static SpeechServiceImpl instance;
    private String postPattern[];
    private String response[];
    private Retrofit retrofit;
    private QueryListener queryListener;

    public interface QueryListener {
        void onResponse(String response);

        void onFailure();
    }

    public void setQueryListener(QueryListener queryListener) {
        this.queryListener = queryListener;
    }

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

    private Retrofit getRetrofitInstace() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(URLUtil.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
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

    @Override
    public void query(String post) {
        String response = ask(post);

        if (post.contains("闭嘴")) {
            queryListener.onResponse(SHUT_UP_RESPONSE);
        } else if (response != null) {
            queryListener.onResponse(response);
        } else {
            QAService service = getRetrofitInstace().create(QAService.class);
            Call<QAResponse> call = service.query(post);
            call.enqueue(new Callback<QAResponse>() {
                @Override
                public void onResponse(Call<QAResponse> call, Response<QAResponse> response) {
                    String data = response.body().getData();
                    queryListener.onResponse(data);
                }

                @Override
                public void onFailure(Call<QAResponse> call, Throwable t) {
                    queryListener.onFailure();
                }
            });
        }
    }
}
