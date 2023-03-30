package com.solgae.newoliving.adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentPagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.solgae.newoliving.R;
import com.solgae.newoliving.base.BaseActivity;
import com.solgae.newoliving.base.InfintiApplication;
import com.solgae.newoliving.busevent.BusProvider;
import com.solgae.newoliving.busevent.PushEvent;
import com.solgae.newoliving.vo.MachineColummInfo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.auth.BasicUserPrincipal;

public class MainGridAdapter extends BaseAdapter {

    private List<Object> dataList = new ArrayList<>();
    boolean clicked = false;
    Context context;
    private OnMyItemOutNow myItemoutNow;
    private OnMyItemCheckedChanged mOnMyItemCheckedChanged;
    int page = 0;

    public interface OnMyItemCheckedChanged { public void onItemCheckedChanged(Object bean, View v, int position);}

    public void setOnMyItemCheckedChanged(OnMyItemCheckedChanged  onMyItemCheckedChanged){ this.mOnMyItemCheckedChanged = onMyItemCheckedChanged; }

    public interface OnMyItemOutNow{ public void onItemoutNow(Object bean, View v);}

    public void setOnMyItemoutNow(OnMyItemOutNow onMyItemoutNow){ this.myItemoutNow = onMyItemoutNow; }

    public MainGridAdapter(List<Object> datas, Context context, String productType) {
        Log.d("CATEGRID", productType + " === " + datas.size());
//        this.dataList = datas;
        this.context = context;
        for(int i=0;i<datas.size();i++){
            dataList.add(datas.get(i));
        }

        Log.d("CATEGRID", dataList.size()+"");
    }


    @Override
    public int getCount() {
        Log.d("yoon", dataList.size()+" get COUNT");
        return dataList.size();
    }

    @Override
    public Object getItem(int i) {
        Log.d("yoon", dataList.get(i)+" get ITEM");
        return dataList.get(i);
    }

    @Override
    public long getItemId(int i) {
        Log.d("yoon", i+" item ID 값");

        return i;
    }



    private LinearLayout normalLayout;

    @Override
    public View getView(int i, View itemView, ViewGroup viewGroup) {

        final ViewHolder mHolder;
        final MachineColummInfo machineColummInfo;

        Log.d("yoon", i+" 인덱스 값");
//        if(viewGroup.getChildAt(i)!=null){
//            View item = (View)viewGroup.getChildAt(i);
//            return item;
//        }
        BusProvider.getInstance().post(new PushEvent("SCROOL_DATA", ""));
        if (itemView == null) {
            mHolder = new ViewHolder();
            itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_gridview, viewGroup, false);
            mHolder.ItemNotice = (LinearLayout)itemView.findViewById(R.id.ItemNotice);
            mHolder.normalItem = (LinearLayout)itemView.findViewById(R.id.normalItem);
            mHolder.notice_Name = (TextView) itemView.findViewById(R.id.notice_Name);
            mHolder.item_li = (LinearLayout) itemView.findViewById(R.id.itemLI);
            mHolder.cate_li = (LinearLayout) itemView.findViewById(R.id.cateLI);
            mHolder.root_line = (FrameLayout) itemView.findViewById(R.id.root_line);
            mHolder.imageLayout = (FrameLayout) itemView.findViewById(R.id.imageLayout);
            mHolder.iv_img = (ImageView) itemView.findViewById(R.id.iv_img);
            mHolder.tv_text = (TextView) itemView.findViewById(R.id.item_title);
            mHolder.txtCost = (TextView) itemView.findViewById(R.id.item_cost);
            mHolder.solidout = (ImageView) itemView.findViewById(R.id.solidout);
            mHolder.txtOrigin = (TextView) itemView.findViewById(R.id.txtOrigin);
            mHolder.txtDate = (TextView) itemView.findViewById(R.id.txtDate);
            mHolder.memo = (ImageView) itemView.findViewById(R.id.memo);
            mHolder.notice_more = (TextView) itemView.findViewById(R.id.notice_more);
            mHolder.MoreInfoBtn = (TextView) itemView.findViewById(R.id.MoreInfoBtn);
            itemView.setTag(mHolder);
        } else {
            //그리드 뷰 클릭오류 - 송윤주
            mHolder = new ViewHolder();
            itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_gridview, viewGroup, false);
            mHolder.normalItem = (LinearLayout)itemView.findViewById(R.id.normalItem);
            mHolder.ItemNotice = (LinearLayout)itemView.findViewById(R.id.ItemNotice);
            mHolder.notice_Name = (TextView) itemView.findViewById(R.id.notice_Name);
            mHolder.item_li = (LinearLayout) itemView.findViewById(R.id.itemLI);
            mHolder.cate_li = (LinearLayout) itemView.findViewById(R.id.cateLI);
            mHolder.root_line = (FrameLayout) itemView.findViewById(R.id.root_line);
            mHolder.root_line = (FrameLayout) itemView.findViewById(R.id.root_line);
            mHolder.imageLayout = (FrameLayout) itemView.findViewById(R.id.imageLayout);
            mHolder.iv_img = (ImageView) itemView.findViewById(R.id.iv_img);
            mHolder.tv_text = (TextView) itemView.findViewById(R.id.item_title);
            mHolder.txtCost = (TextView) itemView.findViewById(R.id.item_cost);
            mHolder.solidout = (ImageView) itemView.findViewById(R.id.solidout);
            mHolder.txtOrigin = (TextView) itemView.findViewById(R.id.txtOrigin);
            mHolder.memo = (ImageView) itemView.findViewById(R.id.memo);
            mHolder.notice_more = (TextView) itemView.findViewById(R.id.notice_more);
            mHolder.txtDate = (TextView) itemView.findViewById(R.id.txtDate);
            mHolder.MoreInfoBtn = (TextView) itemView.findViewById(R.id.MoreInfoBtn);
            // mHolder = (ViewHolder) itemView.getTag();
        }
        final View finalItemView1 = itemView;

        ImageView checkBtn = (ImageView) itemView.findViewById(R.id.checkbtn);
        mHolder.tv_text = (TextView) itemView.findViewById(R.id.tv_text);
        mHolder.txtCost = (TextView) itemView.findViewById(R.id.txtCost);

        machineColummInfo =(MachineColummInfo) dataList.get(i);
//        mHolder.imageLayout.setLayoutParams(new LinearLayout.LayoutParams(250, 350));

        // 원산지 설정
        if(!machineColummInfo.getCountry().equalsIgnoreCase("")){
            mHolder.cate_li.setVisibility(View.GONE);
            mHolder.item_li.setVisibility(View.VISIBLE);
            mHolder.tv_text = (TextView) itemView.findViewById(R.id.item_title);
            mHolder.txtCost = (TextView) itemView.findViewById(R.id.item_cost);
            mHolder.txtOrigin.setText("원산지:" + machineColummInfo.getCountry());
        }

        // 유의사항아이콘 설정
        mHolder.memo.setVisibility(machineColummInfo.getMemo().equals("")?View.GONE:View.VISIBLE);
//        mHolder.memo.bringToFront();
        mHolder.memo.setTag(i);

        // 서버에서 메모가져와 setText
        String[] getmemo = machineColummInfo.getMemo().split("@");
        String rememo = "";
        if(getmemo.length>4){
            // 서버메모가 3줄 이상일 때만 상품 상세보기 버튼 활성화
            for(int x=0;x<3;x++){
                rememo += getmemo[x] + "\n";
            }
            mHolder.notice_more.setText(rememo);
            mHolder.MoreInfoBtn.setVisibility(View.VISIBLE);
        }else{
            for(int x=0;x<getmemo.length;x++){
                rememo += getmemo[x] + "\n";
            }
            mHolder.notice_more.setText(rememo);
            mHolder.MoreInfoBtn.setVisibility(View.GONE);
        }

        // (유의사항 layout)상품상세보기 버튼 클릭 시 detailItemActivity 의 setOnMyItemCheckedChanged 함수 호출
        mHolder.MoreInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnMyItemCheckedChanged != null) {
                    String[] getmemo = machineColummInfo.getMemo().split("@");
                    String rememo = "";
                    for(int x=0;x<getmemo.length;x++){
                        rememo += getmemo[x] + "\n";
                    }
                    final String memo = rememo;
                    mOnMyItemCheckedChanged.onItemCheckedChanged(memo, finalItemView1,i);
                }
            }
        });

        // 상품별 유의사항 플립애니메이션
        machineColummInfo.setNoticeClick(false); // 상품별 유의사항버튼 설정 (false = 상품화면(초기값), true = 유의사항화면)
        mHolder.memo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Log.d("tag", v.getTag()+"");
                FrameLayout li = (FrameLayout)v.getParent();
                final LinearLayout normal = (LinearLayout)li.getChildAt(0);
                final LinearLayout notice = (LinearLayout)li.getChildAt(4);
                MachineColummInfo mc = (MachineColummInfo)dataList.get((Integer) v.getTag());
                if (mc.getNoticeClick() == false) {
                    final ObjectAnimator oa1 = ObjectAnimator.ofFloat(li, "scaleX", 1f, 0f);
                    final ObjectAnimator oa2 = ObjectAnimator.ofFloat(li, "scaleX", 0f, 1f);
                    oa1.setInterpolator(new DecelerateInterpolator());
                    oa1.setDuration(180);
                    oa2.setInterpolator(new AccelerateDecelerateInterpolator());
                    oa2.setDuration(180);
                    oa1.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            normal.setVisibility(View.GONE);
                            notice.setVisibility(View.VISIBLE);
                            mHolder.memo.setBackgroundResource(R.drawable.back);
                            machineColummInfo.setNoticeClick(true);
                            Log.d("catch","뒤로간다");

                            oa2.start();
                        }
                    });
                    oa1.start();
                } else if(mc.getNoticeClick() == true) {
                    final ObjectAnimator oa1 = ObjectAnimator.ofFloat(li, "scaleX", 1f, 0f);
                    final ObjectAnimator oa2 = ObjectAnimator.ofFloat(li, "scaleX", 0f, 1f);
                    oa1.setInterpolator(new DecelerateInterpolator());
                    oa1.setDuration(180);
                    oa2.setInterpolator(new AccelerateDecelerateInterpolator());
                    oa2.setDuration(180);
                    oa1.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            normal.setVisibility(View.VISIBLE);
                            notice.setVisibility(View.GONE);
                            mHolder.memo.setBackgroundResource(R.drawable.exclamation);
                            machineColummInfo.setNoticeClick(false);
                            oa2.start();
                            Log.d("catch","앞으로간다");

                        }
                    });
                    oa1.start();
                }
            }
        });

        // 상품별 가격 설정
        mHolder.notice_Name.setText("상품명 : " + machineColummInfo.getItem_name());
        mHolder.tv_text.setText(machineColummInfo.getColumm() + "  " + machineColummInfo.getItem_name());
        mHolder.txtCost.setVisibility(View.VISIBLE);
        String itemCost = machineColummInfo.getItem_cost().replace(".00", "");
        mHolder.txtCost.setText(itemCost + " 원");

        // 상품별 이미지 설정
        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.transforms(new CenterCrop(), new RoundedCorners(16));

        File imgFile = new File(InfintiApplication.LOCAL_IMAGE_URL + "/" + machineColummInfo.getItem_image());
        Glide.with(context)
                .load(imgFile)
//                .thumbnail(/*sizeMultiplier=*/ 0.5f)
                .apply(requestOptions)
                .override(315,415) // ex) override(600, 200)
                .diskCacheStrategy( DiskCacheStrategy.ALL )
                .into(mHolder.iv_img);
        itemView.setId(i);

        String colm = machineColummInfo.getColumm();
        int target = colm.indexOf("0");
        if(target == 0){
            colm = colm.substring(1);
//            Log.d("tag", machineColummInfo.getColumm()+"--0일 때---"+ colm);
        }else{
            colm = colm;
//            Log.d("tag", machineColummInfo.getColumm()+"--1일 때---"+ colm);
        }
        itemView.setTag(colm);

        if(i == InfintiApplication.totalItems.size()){
            InfintiApplication.totalItems.add((FrameLayout) itemView);
        }
        if(machineColummInfo.getChecked()){
            checkBtn.setVisibility(View.VISIBLE);
            Log.d("this", machineColummInfo.getChecked()+"");

//            BusProvider.getInstance().post(new PushEvent("itemChecked", itemView));
        }else{
            checkBtn.setVisibility(View.GONE);
        }
        final View finalItemView = itemView;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { if (mOnMyItemCheckedChanged != null) mOnMyItemCheckedChanged.onItemCheckedChanged(machineColummInfo, finalItemView, i);
            }
        });

        return itemView;
    }


    private class ViewHolder {
        private FrameLayout root_line, imageLayout;
        private ImageView iv_img;
        private TextView tv_text, txtCost,txtOrigin,txtDate, notice_more, MoreInfoBtn, notice_Name;
        private ImageView solidout, memo;
        private LinearLayout item_li, cate_li, normalItem, ItemNotice;
    }



}


