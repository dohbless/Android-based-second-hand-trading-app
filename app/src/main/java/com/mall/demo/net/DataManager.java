package com.mall.demo.net;

import com.google.gson.Gson;
import com.mall.demo.bean.ChatListBo;
import com.mall.demo.bean.LoginBo;
import com.mall.demo.utils.LogUtils;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class DataManager {

    private final ApiService apiService;

    public DataManager(){
        this.apiService = RetrofitUtils.get().retrofit().create(ApiService.class);
    }

    public Observable<BaseResponse<LoginBo>> postRegister(String name, String pwd, String role){
        HashMap<String, String> jsonMap = new HashMap();
        jsonMap.put("account", name);
        jsonMap.put("pwd", pwd);
        jsonMap.put("role", role);
        String json = new Gson().toJson(jsonMap);
        LogUtils.d("okhttp:==" + json);
        String contentType = "application/json;charset=UTF-8";
        RequestBody body = RequestBody.create(MediaType.parse(contentType), json);
        return apiService.postRegister(body);
    }

    public Observable<BaseResponse<LoginBo>> postLogin(String name, String pwd){
        HashMap<String, String> jsonMap = new HashMap();
        jsonMap.put("account", name);
        jsonMap.put("pwd", pwd);
        String json = new Gson().toJson(jsonMap);
        LogUtils.d("okhttp:==" + json);
        String contentType = "application/json;charset=UTF-8";
        RequestBody body = RequestBody.create(MediaType.parse(contentType), json);
        return apiService.postLogin(body);
    }

    public Observable<BaseResponse<List<ChatListBo>>> loadChatList(String account){
        return apiService.getChatList(account);
    }
}
