package com.ks.secondtest.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ks.secondtest.R;
import com.ks.secondtest.adapter.RclMy;
import com.ks.secondtest.bean.Bean;
import com.ks.secondtest.bean.Dbheap;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Collection extends Fragment {


    private View view;
    /**
     * Hello blank fragment
     */
    private RecyclerView mRv;
    private ArrayList<Bean>   mBeans = new ArrayList<>();
    private RclMy mRclMy;

    public Collection() {
        // Required empty public constructor
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            initData();
        }else {
            mBeans.clear();
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_collection, container, false);
        initView(view);
        return view;
    }

    private void initData() {
        List<Bean> list = Dbheap.getDbheap().queryall();
        mBeans.addAll(list);
        mRclMy.setBeans(mBeans);
        mRclMy.notifyDataSetChanged();
    }
    private void initView(View view) {
        mRv = (RecyclerView) view.findViewById(R.id.rv);
        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRclMy = new RclMy(mBeans, getActivity());
        mRv.setAdapter(mRclMy);
        mRclMy.setOnClickListener(new RclMy.OnClickListener() {
            @Override
            public void OnClick(int position, Bean bean) {
                Dbheap.getDbheap().delete(bean);
                mBeans.remove(bean);
                mRclMy.notifyDataSetChanged();
                Toast.makeText(getActivity(), "no", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
