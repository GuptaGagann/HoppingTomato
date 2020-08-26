package com.example.gagan.hoppingtomatoapp;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

class MyAdapter extends FragmentPagerAdapter {
    Context context;
    int totalTabs;
    public MyAdapter(Context c, FragmentManager fm, int totalTabs) {
        super(fm);
        context = c;
        this.totalTabs = totalTabs;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                AddMenu addMenuFragment = new AddMenu();
                return addMenuFragment;
            case 1:
                ViewMenu viewMenuFragment = new ViewMenu();
                return viewMenuFragment;
            case 2:
                Orders ordersFragment = new Orders();
                return ordersFragment;
            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return totalTabs;
    }
}
