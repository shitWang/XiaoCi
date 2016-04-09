package com.shannon.xiaoci.main.view.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.shannon.xiaoci.R;
import com.shannon.xiaoci.main.view.fragment.DicBookFragment;
import com.shannon.xiaoci.main.view.fragment.MainFragment;
import com.shannon.xiaoci.main.view.fragment.SettingFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {



    private TabLayout tabs;
    private ViewPager container;
    private String[] tabs_title={"主页","单词本","设置"};
    private ArrayList<Fragment> al_fragments = new ArrayList<>();

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






}
