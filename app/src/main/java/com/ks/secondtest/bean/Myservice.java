package com.ks.secondtest.bean;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by F0519 on 2019/6/26.
 */

public interface Myservice {
    //https://gank.io/api/data/%E7%A6%8F%E5%88%A9/20/1
    public String fl="https://gank.io/api/data/";
    @GET("%E7%A6%8F%E5%88%A9/20/1")
    Observable<Wea> getWea();
    //http://news-at.zhihu.com/api/4/news/hot
    public String url="http://news-at.zhihu.com/";
    @GET("api/4/news/hot")
    Observable<Art> getart();
    //http://yun918.cn/study/public/file_upload.php
    public String Load="http://yun918.cn/study/";
    @POST("public/file_upload.php")
    @Multipart
    Observable<Aa> getAa(@Part("key")RequestBody key, @Part MultipartBody.Part file);
}
