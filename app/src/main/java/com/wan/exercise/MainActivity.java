package com.wan.exercise;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import com.wan.library.base.Constants;
import com.wan.project.ProjectFragment;
import com.wan.system.SystemFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Fragment> fragmentList;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private RadioGroup radioGroup;

//    private String[] ROUTE = new String[]{
//            Constants.ROUTE_HOME,
//            Constants.ROUTE_PROJECT,
//            Constants.ROUTE_SYSTEM,
//            Constants.ROUTE_MINE
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        initView();
        initData();
    }

    private void initData() {
        fragmentList = new ArrayList<>();

        fragmentList.add(new Fragment());
        fragmentList.add(new ProjectFragment());
        fragmentList.add(new SystemFragment());
        fragmentList.add(new Fragment());

        fragmentTransaction = fragmentManager.beginTransaction();
        for (Fragment fragment : fragmentList) {
            fragmentTransaction.add(R.id.frame_layout, fragment);
        }
        fragmentTransaction.commit();

        radioGroup.check(R.id.rb_project);
        showPage(1);
    }

    private void initView() {
        radioGroup = findViewById(R.id.radio_group);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:
                        showPage(0);
                        break;
                    case R.id.rb_project:
                        showPage(1);
                        break;
                    case R.id.rb_system:
                        showPage(2);
                        break;
                    case R.id.rb_mine:
                        showPage(3);
                        break;
                    default:
                        break;
                }

            }
        });
    }

    private void showPage(int index) {
        fragmentTransaction = fragmentManager.beginTransaction();
        for (int i = 0; i < fragmentList.size(); i++) {
            if (i == index) {
                fragmentTransaction.show(fragmentList.get(i));
            } else {
                fragmentTransaction.hide(fragmentList.get(i));
            }
        }
        fragmentTransaction.commit();
    }
}
