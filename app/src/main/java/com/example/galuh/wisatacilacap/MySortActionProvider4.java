package com.example.galuh.wisatacilacap;

import android.content.Context;
import android.support.v4.view.ActionProvider;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

/**
 * Created by OWNER on 8/8/2016.
 */
public class MySortActionProvider4 extends ActionProvider implements MenuItem.OnMenuItemClickListener{
    private Context mContext;
    public MySortActionProvider4(Context context)
    {
        super(context);
        mContext=context;
    }

    public View onCreateActionView()
    {
        return null;
    }
    public boolean hasSubMenu()
    {
        return true;
    }

    public  void onPrepareSubMenu(SubMenu subMenu)
    {
        subMenu.clear();
        subMenu.add("Sort in ascending").setOnMenuItemClickListener(this);
        MenuItem item1=subMenu.getItem(0);
        item1.setIcon(android.R.drawable.arrow_up_float);
        subMenu.add("Sort in descending").setOnMenuItemClickListener(this);
        MenuItem item2=subMenu.getItem(1);
        item2.setIcon(android.R.drawable.arrow_down_float);
    }


    public boolean onMenuItemClick(MenuItem item)
    {
        if(item.getTitle().equals("Sort in ascending"))
        {
            Alam.sortList(1);
        }
        else
        {
            Alam.sortList(-1);
        }
        return true;
    }
}
