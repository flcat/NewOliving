package com.solgae.newoliving.adapter;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.solgae.newoliving.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MQ on 2016/11/11.
 */

public class ViewPagerAdapter extends PagerAdapter {
    private List<GridView> gridList;
    private ViewPager view_pager;

    private ArrayList<String> hash = new ArrayList<String>();

    public ViewPagerAdapter() {
        gridList = new ArrayList<>();
    }

    public void add(List<GridView> datas) {
        if (gridList.size() > 0) {
            gridList.clear();
        }
        gridList.addAll(datas);
        notifyDataSetChanged();
    }

  @Override
        public int getCount() {
        Log.d("겟카운트 호출 디버그", gridList.size() + " ");
        return gridList.size();
   }

    @Override
    public int getItemPosition(Object object) {
        Log.d("겟 아이템 포지션 호출 디버그", gridList.size() + " ");
        return POSITION_NONE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        Log.d("is view from object 디버그", gridList.size() + " ");
        Log.d("is view from object 디버그", object.hashCode() + " ");
        return view == object;
    };

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Log.d("instantiateITem 디버그", gridList.size() + " ");
        container.addView(gridList.get(position));
        return gridList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        Log.d("디스트로이 호출 디버그", gridList.size() + " ");
        container.removeView((View) object);
    }


}
