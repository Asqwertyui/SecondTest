package com.ks.secondtest.fragment;


import android.Manifest;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.ks.secondtest.MyReceiver;
import com.ks.secondtest.R;
import com.ks.secondtest.bean.Aa;
import com.ks.secondtest.bean.Myservice;

import java.io.File;
import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class Load extends Fragment implements View.OnClickListener {


    private View view;
    /**
     * ok上传
     */
    private Button mBtup;
    /**
     * re上传
     */
    private Button mBtdown;
    /**
     * 广播
     */
    private Button mGb;
    private ImageView mIv;
    private MyReceiver mMyReceiver;

    public Load() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_load, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mBtup = (Button) view.findViewById(R.id.btup);
        mBtup.setOnClickListener(this);
        mBtdown = (Button) view.findViewById(R.id.btdown);
        mBtdown.setOnClickListener(this);
        mGb = (Button) view.findViewById(R.id.gb);
        mGb.setOnClickListener(this);
        mIv = (ImageView) view.findViewById(R.id.iv);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btup:
                OK();
                break;
            case R.id.btdown:
                Re();
                break;
            case R.id.gb:
                Intent ss = new Intent("ss");
                ss.putExtra("aa","今天天气很好");
                getActivity().sendBroadcast(ss);
                break;
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        mMyReceiver = new MyReceiver();
        IntentFilter ss = new IntentFilter("ss");
        getActivity().registerReceiver(mMyReceiver,ss);
    }
    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(mMyReceiver);
    }


    private void Re() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            Reopen();
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 200);
        }
    }


    private void OK() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            OKopen();
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (requestCode == 100) {
                OKopen();
            } else if (requestCode == 200) {
                Reopen();
            }

        }
    }

    private void Reopen() {
        File file = null;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File sd = Environment.getExternalStorageDirectory();
            file = new File(sd, "a.png");
        }
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Myservice.Load)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Myservice myservice = retrofit.create(Myservice.class);
        RequestBody body = RequestBody.create(MediaType.parse("image/png"), file);
        RequestBody body1 = MultipartBody.create(MediaType.parse("application/octet-stream"), "qq");
        MultipartBody.Part data = MultipartBody.Part.createFormData("file", file.getName(), body);
        Observable<Aa> aa = myservice.getAa(body1, data);
        aa.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Aa>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Aa aa) {
                        if (aa != null) {
                            String s = aa.getData().getUrl();
                            Glide.with(getActivity()).load(s).into(mIv);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("tag", e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.e("tga", "onComplete");
                    }
                });

    }

    private void OKopen() {
        File file = null;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File sd = Environment.getExternalStorageDirectory();
            file = new File(sd, "follow.png");
        }
        OkHttpClient client = new OkHttpClient.Builder()
                .build();
        RequestBody body1 = RequestBody.create(MediaType.parse("image/png"), file);
        MultipartBody body = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", file.getName(), body1)
                .addFormDataPart("key", "ss")
                .build();
        Request request = new Request.Builder()
                .url("http://yun918.cn/study/public/file_upload.php")
                .post(body)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("tga", e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String s = response.body().string();
                Gson gson = new Gson();
                Aa aa = gson.fromJson(s, Aa.class);
                final String url = aa.getData().getUrl();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.with(getActivity()).load(url).into(mIv);
                    }
                });
            }
        });
    }
}
