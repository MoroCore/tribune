package org.moro.tribune.provider;


import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.moro.tribune.bean.AccessTokenDTO;
import org.moro.tribune.bean.GitHubUser;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/*
*
*
*
* */
@Slf4j
@Component
public class GithubProvider {


    public String getAccessToken(AccessTokenDTO accessTokenDTO) throws IOException {

        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(20000, TimeUnit.SECONDS).readTimeout(20000,TimeUnit.SECONDS).build();

        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));



        System.out.println(JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
//            String token = string.split("&")[0].split("=")[1];
            return string;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public GitHubUser getUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user")
                .header("Authorization", "token " + accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            GitHubUser githubUser = JSON.parseObject(string, GitHubUser.class);
            return githubUser;
        } catch (Exception e) {
            log.error("getUser error,{}", accessToken, e);
        }
        return null;
    }

}
//public class GithubProvider {


//    public String getAccessToken(AccessTokenDTO accessTokenDTO) throws IOException {
//
//        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
//
//        OkHttpClient client = new OkHttpClient();
//
//        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
//        Request request = new Request.Builder()
//                .url("https://github.com/login/oauth/access_token")
//                .post(body)
//                .build();
//            try (Response response = client.newCall(request).execute()){
//                String string = response.body().toString();
//                System.out.println(string);
//                return string;
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//            return null;
//    }


