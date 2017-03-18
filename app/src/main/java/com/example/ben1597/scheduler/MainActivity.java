package com.example.ben1597.scheduler;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ben1597.scheduler.showSchedule.ShowScheduleFragment;
import com.example.ben1597.scheduler.showSchedule.ShowSchedulePresenter;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private static FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setStatusBarBackground(R.color.colorPrimaryDark);

        fragmentManager = getSupportFragmentManager();

        if (null == savedInstanceState) {
            initFragment(ShowScheduleFragment.newInstance());
        }
    }

    private void initFragment(Fragment fragment) {
        // Add the NotesFragment to the layout
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.contentFrame, fragment);
        transaction.commit();
    }

    public static void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.contentFrame, fragment);
        transaction.commit();
    }
}





