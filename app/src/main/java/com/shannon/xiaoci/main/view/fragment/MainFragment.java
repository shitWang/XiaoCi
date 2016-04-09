package com.shannon.xiaoci.main.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shannon.xiaoci.R;

/**
 * Created by Administrator on 2016/4/9 0009.
 */
public class MainFragment extends Fragment{

    private RecyclerView fm_rlv_news;
    private String[] news = {"新闻一","新闻一","新闻一","新闻一","新闻一","新闻一","新闻一","新闻一","新闻一","新闻一"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main,null);
        fm_rlv_news = (RecyclerView) view.findViewById(R.id.fm_rlv_news);
        fm_rlv_news.setLayoutManager(new LinearLayoutManager(getContext()));
        fm_rlv_news.setAdapter(new HomeAdapter());
        return view;
    }


    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder>
    {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    getContext()).inflate(R.layout.view_item_news, parent,
                    false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position)
        {
            holder.tv.setText(news[position]);
        }

        @Override
        public int getItemCount()
        {
            return news.length;
        }

        class MyViewHolder extends RecyclerView.ViewHolder
        {

            TextView tv;

            public MyViewHolder(View view)
            {
                super(view);
                tv = (TextView) view.findViewById(R.id.textView2);
            }
        }
    }

}
