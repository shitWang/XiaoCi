package com.shannon.xiaoci.search.presenter;

import android.content.Context;
import android.database.Cursor;
import android.text.Editable;
import android.widget.Toast;

import com.shannon.xiaoci.main.view.activity.MainActivity;
import com.shannon.xiaoci.main.view.inter.SearchViewInter;
import com.shannon.xiaoci.search.model.WordModel;
import com.shannon.xiaoci.search.model.bean.Word;

/**
 * Created by Shannon on 2016/4/9.
 */
public class SearchPresenter {


    private SearchViewInter svi;

    public SearchPresenter(SearchViewInter svi) {
        this.svi = svi;
    }

    //根据输入的单词查找意思
    public void searchWord(String input){


        Word word = WordModel.searchFromDatabase((MainActivity)svi,input);
        if(word == null){

            Toast.makeText((MainActivity)svi,"本地数据库查询失败",Toast.LENGTH_SHORT).show();


        }else{

            Toast.makeText((MainActivity)svi,word.toString(),Toast.LENGTH_SHORT).show();

        }



    }


    //输入的过程中查询单词
    public void searchInput(String s){


        Cursor cursor = WordModel.getWordCursor((MainActivity)svi,s+"%");


        ((MainActivity) svi).showResultInput(cursor);




    }




}
