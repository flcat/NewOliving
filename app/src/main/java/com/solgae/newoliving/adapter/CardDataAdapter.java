package com.solgae.newoliving.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.solgae.newoliving.R;
import com.solgae.newoliving.vo.DCardData;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CardDataAdapter extends BaseAdapter {

    DecimalFormat decimalFormat;
    private ArrayList<DCardData> Detail_Card_Data;

    public CardDataAdapter() {
        decimalFormat = new DecimalFormat("###,###.##");
        Detail_Card_Data = new ArrayList<DCardData>() ;
    }

// Adapter에 사용되는 데이터의 개수를 리턴
    @Override
    public int getCount() {
        return Detail_Card_Data.size();
    }

    @Override
    public Object getItem(int position) {
        return Detail_Card_Data.get(position) ;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

// position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_item, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        TextView titleTextView = (TextView) convertView.findViewById(R.id.txtItemName) ;
        TextView itemQty = (TextView) convertView.findViewById(R.id.itemQty) ;
        TextView danga = (TextView) convertView.findViewById(R.id.danga) ;
        TextView cost = (TextView) convertView.findViewById(R.id.cost) ;


        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        DCardData dCardData = Detail_Card_Data.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        titleTextView.setText(dCardData.getPro_title());
        itemQty.setText(dCardData.getPro_cnt());
        danga.setText(dCardData.getUnit_money());
        cost.setText(dCardData.getResult_money());

        return convertView;
    }

    // 아이템 데이터 추가를 위한 함수. 개발자가 원하는대로 작성 가능.
    public void addItem(String itemcode, String itemname, String itemcount, String itemDanga, String itemCost) {
        DCardData item = new DCardData();
        item.setPro_code(itemcode);
        item.setPro_title(itemname);
        item.setPro_cnt(decimalFormat.format(itemcount.equalsIgnoreCase("")?0:Double.parseDouble(itemcount)));
        item.setUnit_money(decimalFormat.format(itemDanga.equalsIgnoreCase("")?0:Double.parseDouble(itemDanga)));
        item.setResult_money(decimalFormat.format(itemCost.equalsIgnoreCase("")?0:Double.parseDouble(itemCost)));
        Detail_Card_Data.add(item);
    }
}
