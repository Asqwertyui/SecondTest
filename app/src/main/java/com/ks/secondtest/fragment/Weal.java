package com.ks.secondtest.fragment;


import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import com.ks.secondtest.R;
import com.ks.secondtest.adapter.RclMyweal;
import com.ks.secondtest.Web;
import com.ks.secondtest.bean.Myservice;
import com.ks.secondtest.bean.Wea;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class Weal extends Fragment {


    private View view;
    private RecyclerView mRv;
    private ArrayList<Wea.ResultsBean> mResultsBeans;
    private RclMyweal mRclMyweal;
    private PopupWindow mpop;
    private LinearLayoutManager mManager;

    public Weal() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_weal, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Myservice.fl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        Myservice myservice = retrofit.create(Myservice.class);
        Observable<Wea> wea = myservice.getWea();
        wea.subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Wea>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Wea wea) {
                if(wea!=null){
                    List<Wea.ResultsBean> list = wea.getResults();
                    mResultsBeans.addAll(list);
                    mRclMyweal.notifyDataSetChanged();
                }
                    }

                    @Override
                    public void onError(Throwable e) {
                    Log.e("tga",e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.e("tga","onComplete");
                    }
                });
    }

    private void initView(View view) {
        mRv = (RecyclerView) view.findViewById(R.id.rv);
        mManager = new LinearLayoutManager(getActivity());
        mRv.setLayoutManager(mManager);
        mResultsBeans = new ArrayList<>();
        mRclMyweal = new RclMyweal(mResultsBeans, getActivity());


        mRv.setAdapter(mRclMyweal);
        mRclMyweal.setOnClickListener(new RclMyweal.OnClickListener() {
            @Override
            public void OnClick(int position, final Wea.ResultsBean bean) {
                View view1 = View.inflate(getContext(), R.layout.pop, null);
                mpop = new PopupWindow(view1, 400, 300);
                Button bt = view1.findViewById(R.id.bt);
                mpop.showAsDropDown(view1,Gravity.CENTER,300);
                mpop.setOutsideTouchable(true);
                mpop.setBackgroundDrawable(new ColorDrawable());
                view1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mpop.dismiss();
                    }
                });
                bt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mRclMyweal.delete();
                    }
                });
            }


            @Override
            public void OnLong(int position, Wea.ResultsBean bean) {
                String url = mResultsBeans.get(position).getUrl();
                Intent intent = new Intent(getActivity(),Web.class);
                intent.putExtra("url",url);
                startActivity(intent);

            }
        });
    }

    public void setManager(LinearLayoutManager manager) {
        mManager = manager;
        mRv.setLayoutManager(manager);
        mRclMyweal.notifyDataSetChanged();
    }

    public void setManager(StaggeredGridLayoutManager staggeredGridLayoutManager) {
        mRv.setLayoutManager(staggeredGridLayoutManager);
        mRclMyweal.notifyDataSetChanged();
    }
}
