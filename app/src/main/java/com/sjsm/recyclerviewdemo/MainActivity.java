package com.sjsm.recyclerviewdemo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private RecyclerView              mRecyclerView;
    private List<Map<String, String>> mData;
    private HomeAdapter               mAdapter;
    private Context                   mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext=this;
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        initData();
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
//        mRecyclerView.addItemDecoration(new DividerGridItemDecoration(mContext));
        mAdapter=new HomeAdapter();
        mRecyclerView.setAdapter(mAdapter);
        SpacesItemDecoration itemDecoration=new SpacesItemDecoration(20);
        mRecyclerView.addItemDecoration(itemDecoration);
    }

    private void initData() {
        mData = new ArrayList<>();
        Map<String, String> map = null;
        for (int i = 0; i < 100; i++) {
            map = new HashMap<>();
            map.put("text", "内容" + i);
            mData.add(map);
        }

    }

    private class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder=new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.list_item,parent,false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.tv_Text.setText(mData.get(position).get("text"));
            for (int i = 0; i < mData.size(); i++) {
                if(i==position){
                    holder.tv_Text.setHeight(i*10);
                }

            }
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

         class MyViewHolder extends RecyclerView.ViewHolder{
            TextView tv_Text;

            public MyViewHolder(View view) {
                super(view);
                tv_Text= (TextView) view.findViewById(R.id.id_text);
            }
        }
    }
}
