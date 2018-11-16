package com.yc.overlaprecyclerview.more2fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.yc.overlaprecyclerview.R;

import java.util.ArrayList;

public class MoreFagmentActivity extends AppCompatActivity implements View.OnClickListener {
    private ArrayList<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_fagment);

        Button bt1 = findViewById(R.id.bt1);
        bt1.setOnClickListener(this);
        Button bt2 = findViewById(R.id.bt2);
        bt2.setOnClickListener(this);
        Button bt3 = findViewById(R.id.bt3);
        bt3.setOnClickListener(this);
        Button bt4 = findViewById(R.id.bt4);
        bt4.setOnClickListener(this);
        Button bt5 = findViewById(R.id.bt5);
        bt5.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.bt1:

                createFragments(fragments.size());
                showFragment(fragments.size() - 1);

                //                //Activity用来管理它包含的Frament，通过getFramentManager()获取
                //                FragmentManager fgManager = getFragmentManager();
                //
                //                //获取Framgent事务
                //
                //                FragmentTransaction fragmentTransaction = fgManager.beginTransaction();
                //
                //                //添加
                //
                //                fragmentTransaction.add(R.id.fl, new FirstFragment());
                //
                //
                //                //指定动画，可以自己添加
                //
                //                //如果需要，添加到back栈中
                //
                //                //提交事务
                //                fragmentTransaction.commit();


                break;

            case R.id.bt2:
                deletFragment(fragments.size() - 1);
                //                showFragment(fragments.size() - 1);

                //                //Activity用来管理它包含的Frament，通过getFramentManager()获取
                //                FragmentManager fgManager2 = getFragmentManager();
                //                //获取Framgent事务
                //                FragmentTransaction fragmentTransaction2 = fgManager2.beginTransaction();
                //                //删除一个Fragment之前，先通过FragmentManager的findFragmemtById()，找到对应的Fragment
                //                Fragment fragment2 = fgManager2.findFragmentByTag();
                //                //删除获取到的Fragment
                //                //指定动画，可以自己添加
                //                fragmentTransaction2.remove(fragment2);
                //                //                String tag = null;
                //                //                //如果需要，添加到back栈中
                //                //                fragmentTransaction.addToBackStack(tag);
                //                //提交事务
                //                fragmentTransaction2.commit();


                break;

            case R.id.bt3:
                int num = (int) (Math.random() * (fragments.size() - 1));
                showFragment(num);
                break;

            case R.id.bt4:
                int num2 = (int) (Math.random() * (fragments.size() - 1));
                deletFragment(num2);
                break;
            case R.id.bt5:
                showFragment(fragments.size() - 1);
                break;


        }

    }

    private void createFragments(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        FirstFragment firstFragment = new FirstFragment();
        firstFragment.setArguments(bundle);
        fragments.add(firstFragment);
    }

    public void showFragment(int position) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        //先隐藏其他的
        for (int i = 0; i < fragments.size(); i++) {
            Fragment fragment = fragments.get(i);
            if (i == position) {
                if (fragment.isAdded()) {
                    transaction.show(fragment);
                } else {
                    //add
                    transaction.add(R.id.fl, fragment);
                }
            } else {
                if (fragment.isAdded()) {
                    transaction.hide(fragment);
                }
            }
        }
        //commit
        //        transaction.commitAllowingStateLoss();
        transaction.commit();

    }

    public void deletFragment(int position) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        //先隐藏其他的
        for (int i = 0; i < fragments.size(); i++) {
            if (i == position) {
                Fragment fragment = fragments.get(i);
                //                Fragment fragment = fragmentManager.findFragmentByTag(position + "");
                transaction.remove(fragment);
                fragments.remove(fragment);
            }
        }
        //commit
        transaction.commit();

    }
}
