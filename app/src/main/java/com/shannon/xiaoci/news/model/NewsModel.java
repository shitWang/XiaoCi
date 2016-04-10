package com.shannon.xiaoci.news.model;

import android.content.Context;

import com.shannon.xiaoci.news.model.bean.News;
import com.shannon.xiaoci.news.model.dao.NewsDao;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Shannon on 2016/4/10.
 */
public class NewsModel {


    private static final String NEWS_URL = "http://www.iciba.com/index.php?c=index&a=getArticlePage&type=2&page=";

    //从网络获取新闻
    public static ArrayList<News> getNews(Context context , int page){


        ArrayList<News> al_news = new ArrayList<>();
        Request request = new Request.Builder().url(NEWS_URL + page).build();
        OkHttpClient ohc = new OkHttpClient();
        try {

            Response response = ohc.newCall(request).execute();
            if(response.isSuccessful()){


                String result = response.body().string();
                JSONObject jo1 = new JSONObject(result);
                JSONObject jo2 = jo1.getJSONObject("message");
                JSONArray ja1 = jo2.getJSONArray("data");


                NewsDao nd = new NewsDao();

                //获取新闻列表
                for(int i=0 ; i<ja1.length() ; i++){

                    JSONObject jo3 = (JSONObject) ja1.get(i);
                    News news = new News();

                    news.setTitle(jo3.getString("title"));
                    news.setDescrible(jo3.getString("description"));
                    news.setCover_url(jo3.getString("thumb"));
                    news.setContent_url(jo3.getString("url"));



                    al_news.add(news);
                    /*
                    News news1 = nd.select(context,jo3.getString("url"));
                    if(news1!=null){

                        al_news.add(news1);

                    }else{


                        nd.insert(context,news);
                        al_news.add(news);


                    }*/




                }



                System.out.println("第" + page + "页的新闻数目为："+ja1.length());



            }


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.toString());
            return null;
        }

        return al_news;


    }




}
