package com.shannon.xiaoci.main.view.activity;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.shannon.xiaoci.R;
import com.shannon.xiaoci.main.view.adapter.DictionaryAdapter;
import com.shannon.xiaoci.main.view.fragment.DicBookFragment;
import com.shannon.xiaoci.main.view.fragment.MainFragment;
import com.shannon.xiaoci.main.view.fragment.SettingFragment;
import com.shannon.xiaoci.main.view.inter.SearchViewInter;
import com.shannon.xiaoci.search.model.WordModel;
import com.shannon.xiaoci.search.model.bean.Word;
import com.shannon.xiaoci.search.presenter.SearchPresenter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SearchViewInter,TextWatcher,AdapterView.OnItemClickListener{



    private TabLayout tabs;
    private ViewPager container;
    private String[] tabs_title={"主页","单词本","设置"};
    private ArrayList<Fragment> al_fragments = new ArrayList<>();
    private AutoCompleteTextView am_acet_input;
    private SearchPresenter sp = new SearchPresenter(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();

    }

    private void initData() {

        al_fragments.add(new MainFragment());
        al_fragments.add(new DicBookFragment());
        al_fragments.add(new SettingFragment());



    }

    private void initView() {

        tabs = (TabLayout) findViewById(R.id.tabs);
        container = (ViewPager) findViewById(R.id.container);
        am_acet_input = (AutoCompleteTextView) findViewById(R.id.am_acet_input);

        am_acet_input.addTextChangedListener(this);
        am_acet_input.setOnItemClickListener(this);

        container.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return al_fragments.get(position);
            }

            @Override
            public int getCount() {
                return al_fragments.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return tabs_title[position];
            }
        });

        tabs.setupWithViewPager(container);







    }


    @Override
    public void showResult(Word word) {

    }

    @Override
    public void showResultInput(Cursor cursor) {

        if(cursor!=null){

            DictionaryAdapter da = new DictionaryAdapter(this,cursor);
            am_acet_input.setAdapter(da);


        }

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {




    }

    @Override
    public void afterTextChanged(Editable s) {


        sp.searchInput(s.toString());




    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        TextView tv = (TextView) view;
        String input = tv.getText().toString().trim();
        Word wd = WordModel.searchFromDatabase(this,input);
        if(wd!=null){

            Toast.makeText(this,wd.toString(),Toast.LENGTH_SHORT).show();

        }else{

            Toast.makeText(this,"未查询到",Toast.LENGTH_SHORT).show();
        }

    }
}
