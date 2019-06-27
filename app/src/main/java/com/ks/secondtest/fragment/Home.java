package com.ks.secondtest.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ks.secondtest.R;
import com.ks.secondtest.adapter.RclMy;
import com.ks.secondtest.adapter.RclMyadapter;
import com.ks.secondtest.bean.Art;
import com.ks.secondtest.bean.Bean;
import com.ks.secondtest.bean.Dbheap;
import com.ks.secondtest.model.Mymodelimpl;
import com.ks.secondtest.persent.Mypersentimpl;
import com.ks.secondtest.view.Myview;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment implements Myview {


    private View view;
    private RecyclerView mRv;
    private ArrayList<Art.RecentBean> mList;
    private RclMyadapter mRclMyadapter;
    private Mypersentimpl mMypersentimpl;

    public Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        mMypersentimpl = new Mypersentimpl(new Mymodelimpl(), this);
        mMypersentimpl.getData();
    }

    private void initView(View view) {
        mRv = (RecyclerView) view.findViewById(R.id.rv);
        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mList = new ArrayList<>();
        mRclMyadapter = new RclMyadapter(mList, getActivity());
        mRv.setAdapter(mRclMyadapter);
        mRclMyadapter.setOnClickListener(new RclMyadapter.OnClickListener() {
            @Override
            public void OnClick(int position, Art.RecentBean bean) {
                Bean bean1 = new Bean();
                bean1.setId(null);
                bean1.setThumbnail(bean.getThumbnail());
                bean1.setTitle(bean.getTitle());
                Dbheap.getDbheap().insert(bean1);
                Toast.makeText(getActivity(), "oK", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void OnSuccess(Art art) {
        if(art!=null){
            mList.addAll(art.getRecent());
            mRclMyadapter.notifyDataSetChanged();
        }
    }

    @Override
    public void OnFail(String msg) {
        Log.e("tag",msg);
    }
}
