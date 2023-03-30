package com.solgae.newoliving;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.solgae.newoliving.adapter.MainGridAdapter;
import com.solgae.newoliving.base.InfintiApplication;
import com.solgae.newoliving.base.PayMethodActivity;
import com.solgae.newoliving.base.PopupActivity;
import com.solgae.newoliving.vo.MachineColummInfo;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView KeyEnterTV, Btn1, Btn2, Btn3, Btn4, Btn5, Btn6, Btn7, Btn8, Btn9, Btn0, BtnBack, BtnCheck;
    ImageView InCartBtn, PayBtn, BeforeCart;

    // 그리드뷰
    HorizontalScrollView seatLegendLayout;
    MainGridAdapter gridViewAdapter;
    private List<GridView> gridList;
    GridView ItemGridView;
    List<Object> collunms;
    LinearLayout GridLinearLi, AllCartLi;

    View nowView; //현재 파란 테두리가 그려져있는 뷰 1개

    //카트
    ArrayList<MachineColummInfo> SelectedItem = new ArrayList<>(); // 카트들어간 아이템들 저장 .. 중복방지
    List<ViewGroup> cartlists = new ArrayList<>(); // 카트에 들어간 아이템 레이아웃
    DecimalFormat myFormatter;
    TextView Main_OrderCount2, Main_PayAmt2, Main_DisAmt2, Main_TotalAmt2;
    LinearLayout Main_CartLi, LogoCartArea;
    ArrayList<ViewGroup> SelectedView = new ArrayList<>(); // 카트에 들어간 아이템 GridView 레이아웃
    HashMap<Object, ViewGroup> hashMap = new HashMap<>();
    HashMap<Object, String> hashMap2 = new HashMap<>(); // 아이템의 ItemNumber 저장 (Cancel 시 사용)
    ArrayList<Integer> stock = new ArrayList<>();

    String nowNum; // 키보드에서 현재 선택된 번호

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        gridList = new ArrayList<>();

        KeyEnterTV = (TextView)findViewById(R.id.KeyEnterTV);
        Btn1 = (TextView)findViewById(R.id.Btn1);
        Btn2 = (TextView)findViewById(R.id.Btn2);
        Btn3 = (TextView)findViewById(R.id.Btn3);
        Btn4 = (TextView)findViewById(R.id.Btn4);
        Btn5 = (TextView)findViewById(R.id.Btn5);
        Btn6 = (TextView)findViewById(R.id.Btn6);
        Btn7 = (TextView)findViewById(R.id.Btn7);
        Btn8 = (TextView)findViewById(R.id.Btn8);
        Btn9 = (TextView)findViewById(R.id.Btn9);
        Btn0 = (TextView)findViewById(R.id.Btn0);
        BtnBack = (TextView)findViewById(R.id.BtnBack);
        BtnCheck = (TextView)findViewById(R.id.BtnCheck);
        InCartBtn = (ImageView)findViewById(R.id.InCartBtn);
        PayBtn = (ImageView) findViewById(R.id.PayBtn);
        ItemGridView = (GridView) findViewById(R.id.ItemGridView);
        GridLinearLi = (LinearLayout)findViewById(R.id.GridLinearLi);
        seatLegendLayout = (HorizontalScrollView)findViewById(R.id.seatLegendLayout);
        AllCartLi = (LinearLayout)findViewById(R.id.AllCartLi);
        Main_OrderCount2 = (TextView)findViewById(R.id.Main_OrderCount2); //총수량
        Main_PayAmt2 = (TextView)findViewById(R.id.Main_PayAmt2); // 상품금액
        Main_DisAmt2 = (TextView)findViewById(R.id.Main_DisAmt2); //할인금액
        Main_TotalAmt2 = (TextView)findViewById(R.id.Main_TotalAmt2); //최종결제금액
        Main_CartLi = (LinearLayout)findViewById(R.id.Main_CartLi);
        LogoCartArea = (LinearLayout)findViewById(R.id.LogoCartArea);
        BeforeCart = (ImageView)findViewById(R.id.BeforeCart);

        Btn1.setOnClickListener(this);
        Btn2.setOnClickListener(this);
        Btn3.setOnClickListener(this);
        Btn4.setOnClickListener(this);
        Btn5.setOnClickListener(this);
        Btn6.setOnClickListener(this);
        Btn7.setOnClickListener(this);
        Btn8.setOnClickListener(this);
        Btn9.setOnClickListener(this);
        Btn0.setOnClickListener(this);
        BtnBack.setOnClickListener(this);
        BtnCheck.setOnClickListener(this);
        InCartBtn.setOnClickListener(this);
        PayBtn.setOnClickListener(this);

        myFormatter = new DecimalFormat("###,###");

        //물품데이터 더하기
        collunms = new ArrayList<>();
        collunms.addAll(InfintiApplication.machineMaster.getMachineColummInfo());
        display_item_position(collunms);
    }

    //컬럼정보를 화면에 등록한다
    private void display_item_position(final List<Object> mGridData){
        if (gridList.size() > 0) gridList.clear();
        Log.d("CATEGRID", "들어온 값 : "+ mGridData.size());

        Log.d("CATEGRID", "ALLMODE");
        gridViewAdapter = new MainGridAdapter(mGridData, this, "ALLMODE");
//        ItemGridView.setNumColumns(47);
//        ItemGridView.setVerticalSpacing(7);
//        ItemGridView.setHorizontalSpacing(5);
        ItemGridView.setAdapter(gridViewAdapter);
        gridList.add(ItemGridView);

        gridViewAdapter.setOnMyItemCheckedChanged(new MainGridAdapter.OnMyItemCheckedChanged() {
            @Override
            public void onItemCheckedChanged(Object bean, View v, int position) {

                int x = v.getLeft();
                int x2 = v.getMeasuredWidth();
                Log.d("width","getLeft : " + x);
                MachineColummInfo cartCheck = (MachineColummInfo) bean;

                //품절재고 선택 시
                ImageView soldoutIMG = (ImageView)v.findViewById(R.id.solidout);
//                if(soldoutIMG.getVisibility()==View.VISIBLE){
//                        AlertMsg("재고 부족", 0.5, 0.15, "STOCK", "alert_logo", "#F65656");
//                        return;
//                }

                //50000원 이상 결제시
//                int TotalCost = Integer.parseInt(Main_OrderCount2.getText().toString().replaceAll("[^0-9]","")) + Integer.parseInt(cartCheck.getPrice());
//                Log.d("LIMITTEST", TotalCost+"");
//                if(TotalCost > 50000 ){
//                    AlertMsg("구매 안내", 0.5, 0.15, "PAY_LIMIT", "alert_logo", "#F65656");
//                    return;
//                };


                //물품 선택 시 깜빡 애니메이션
                Animation animation1 = new AlphaAnimation(0.5f, 1.0f);
                animation1.setDuration(1000);
                v.startAnimation(animation1);

                view_item_selected(v, cartCheck);

            }
        });
    }

    private void view_item_selected(View view, Object bean) {


        //장바구니 초기 배경 GONE
        if (BeforeCart.getVisibility()==View.VISIBLE) BeforeCart.setVisibility(View.GONE);

        // 테두리 있을 시 GONE
        if ( view.getBackground()!=null && view.getBackground().getConstantState().equals(getResources().getDrawable(R.drawable.selecteditem).getConstantState())) {
            view.setBackgroundColor(Color.WHITE);
            InCartBtn.setBackgroundResource(R.drawable.cartbeforebtn);
            InCartBtn.clearAnimation();
        }

        int SelectedItemNum = (int) view.getId(); // 선택된 아이템 TAG
        ((MachineColummInfo) bean).setSelectedItemNum(SelectedItemNum); // 선택된 아이템 Num

        //동일상품 선택 시
        if(SelectedSameItem(view, (MachineColummInfo)bean)==true){ return; };

        // 카트에 뷰 추가
        // cart_item_layout.xml inflate하여 카트리스트 생성 -- 변수선언 -->
        LayoutInflater inflate = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewGroup mView = (ViewGroup) inflate.inflate(R.layout.cart_item_layout, null);

        LinearLayout firstLayer = (LinearLayout) mView.getChildAt(0); //List 레이아웃 ListLi
        TextView ItemName = (TextView)firstLayer.getChildAt(0);
        TextView ItemCost = (TextView)firstLayer.getChildAt(2);

        LinearLayout ItemNumLayer = (LinearLayout) firstLayer.getChildAt(1); // - 1 + Li
        TextView ItemMinus = (TextView) ItemNumLayer.getChildAt(0); // - Btn
        TextView ItemNum = (TextView) ItemNumLayer.getChildAt(1); // 아이템수량
        ItemNum.setText("1");
        ItemNum.setTextColor(Color.BLACK);
        TextView ItemAdd = (TextView) ItemNumLayer.getChildAt(2); // + Btn
        ImageView ItemCancel = (ImageView)firstLayer.getChildAt(3);// x Btn
        ItemMinus.setOnClickListener(this);
        ItemAdd.setOnClickListener(this);
        ItemCancel.setOnClickListener(this);
        // <--

        //선택된 아이템 체크버튼
        ViewGroup vg = (ViewGroup) view;

        // 카트리스트에 데이터를 넣기전 사이즈를 가져옴 add, minus 시 onclick에서 index로 활용
        int totalIndex = InfintiApplication.totalItems.indexOf(view);
        if(totalIndex == -1){
            totalIndex = 0;
        }
        ItemMinus.setTag(totalIndex);
        ItemAdd.setTag(totalIndex);

        // cancel 시 onclick 에서 index로 활용
        ItemCancel.setTag(vg.hashCode());
        hashMap.put(vg.hashCode(), vg);
        hashMap2.put(vg.hashCode(),"ItemNum: " + SelectedItemNum);
        mView.setTag("ItemNum: " + SelectedItemNum);
        Log.d("mViewtag", totalIndex+"");

        // setText
        ItemName.setText(((MachineColummInfo) bean).getItem_name());
        double cost = Double.parseDouble(((MachineColummInfo)bean).getItem_cost());
        int intcost = (int) cost;
        String stcost = myFormatter.format(Integer.valueOf(intcost));
        Log.d("cost", stcost+"");
        ItemCost.setText(stcost + " 원");

        LinearLayout SecondLayer = (LinearLayout) mView.getChildAt(1); //재고 레이아웃 stockLi

        String count = Main_OrderCount2.getText().toString().replaceAll("[^0-9]","");
        String totalCost = Main_PayAmt2.getText().toString().replaceAll("[^0-9]","");
        String allcost = myFormatter.format(Integer.valueOf(totalCost)+intcost);
        Main_OrderCount2.setText(Integer.parseInt(count)+1 + "개");
        Main_PayAmt2.setText(allcost + "원");
        Main_TotalAmt2.setText(allcost + "원");

        // view, array 추가
        cartlists.add(mView);
        Main_CartLi.addView(mView, 0);
        SelectedItem.add((MachineColummInfo)bean);

        SelectedView.add(vg);
    }

    public boolean SelectedSameItem(View view , MachineColummInfo NowItem){
        // 똑같은 상품 click 시 cart 재고 1 ++
            if (SelectedItem.contains(NowItem)) {
                for(int i=0;i<cartlists.size();i++){
                    Log.d("carts", "ItemNum: "+NowItem.getSelectedItemNum());
                    Log.d("carts", "cart ====" + cartlists.get(i).getTag()+"");

                    String check ="ItemNum: "+NowItem.getSelectedItemNum();
                    if (cartlists.get(i).getTag().equals(check)){
                        Log.d("carts", "!!! equals !!!");
                        LinearLayout firstLi = (LinearLayout) cartlists.get(i).getChildAt(0);
                        LinearLayout secondLi = (LinearLayout) firstLi.getChildAt(1);
                        final TextView cartStock = (TextView)secondLi.getChildAt(1);
                        String thisStock = cartStock.getText().toString();
                        TextView cartCost = (TextView) firstLi.getChildAt(2);

                        final int from = ContextCompat.getColor(this, R.color.olivingRed);
                        final int to   = ContextCompat.getColor(this, R.color.colorWhite);

                        ValueAnimator anim = new ValueAnimator();
                        anim.setIntValues(from, to);
                        anim.setEvaluator(new ArgbEvaluator());
                        final LinearLayout cart = (LinearLayout)cartlists.get(i);
                        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                cart.setBackgroundColor((Integer)valueAnimator.getAnimatedValue());
                            }
                        });

                        anim.setDuration(1000);
                        anim.start();

                        if (!thisStock.equalsIgnoreCase(NowItem.getRemain_cnt())) {
                            int addStock = Integer.valueOf(thisStock) + 1;
                            cartStock.setText(addStock + "");
                            String itemcost = NowItem.getItem_cost();

                            if (itemcost.contains(".")) {
                                String cost[] = itemcost.split("\\.");
                                itemcost = cost[0];
                            }
                            String cost = myFormatter.format(Integer.valueOf(itemcost) * addStock);
                            cartCost.setText(cost + " 원"); // 아이템*개수 가격

                            //총 가격
                            String count = Main_OrderCount2.getText().toString().replaceAll("[^0-9]","");
                            String totalCost = Main_PayAmt2.getText().toString().replaceAll("[^0-9]","");
                            int all = Integer.valueOf(totalCost) + Integer.valueOf(itemcost);
                            String allcost = myFormatter.format(all);

                            Main_OrderCount2.setText(Integer.parseInt(count)+1 + "개");
                            Main_PayAmt2.setText(allcost + "원");
                            Main_TotalAmt2.setText(allcost + "원");
                            return true;
                        } else {
                            AlertMsg("제한 수량 초과", 0.5, 0.15, "STOCK", "alert_logo", "#F65656");
//                    Toast.makeText(getApplicationContext(), "해당제품의 재고는 " + thisStock + " 개 입니다.", Toast.LENGTH_LONG).show();
                            return true;
                        }
                    }
                }
            }
        return false;

    }

    @Override
    public void onClick(View view) {

        int cartIndex = 0;
        String thisStock = "";
        LinearLayout firstLayer, secondLayer;
        TextView cartStock = new TextView(getApplication()); // 카트 재고 텍스트뷰
        TextView cartCost = new TextView(getApplication());
        Log.d("null", "nowView null?");

        if(nowView!=null){
            Log.d("null", "nowView not null");
            nowView.setBackgroundColor(Color.WHITE);
            InCartBtn.clearAnimation();
            InCartBtn.setBackgroundResource(R.drawable.cartbeforebtn);
        }

        if(view.getId() == R.id.cart_add || view.getId() == R.id.cart_minus){
            ViewParent viewParent = view.getParent();
            ViewParent vg = (ViewParent) viewParent.getParent();
            ViewGroup viewGroup = (ViewGroup)vg.getParent();
            cartIndex = cartlists.indexOf(viewGroup);

            firstLayer = (LinearLayout) cartlists.get(cartIndex).getChildAt(0);
            secondLayer = (LinearLayout) firstLayer.getChildAt(1);
            cartStock = (TextView)secondLayer.getChildAt(1);
            thisStock = cartStock.getText().toString();
            cartCost = (TextView) firstLayer.getChildAt(2);
        }

        switch (view.getId()){
            case R.id.Btn1: KeyPress(Btn1, "1");break;
            case R.id.Btn2: KeyPress(Btn2, "2");break;
            case R.id.Btn3: KeyPress(Btn3, "3");break;
            case R.id.Btn4: KeyPress(Btn4, "4");break;
            case R.id.Btn5: KeyPress(Btn5, "5");break;
            case R.id.Btn6: KeyPress(Btn6, "6");break;
            case R.id.Btn7: KeyPress(Btn7, "7");break;
            case R.id.Btn8: KeyPress(Btn8, "8");break;
            case R.id.Btn9: KeyPress(Btn9, "9");break;
            case R.id.Btn0: KeyPress(Btn0, "0");break;

            case R.id.BtnBack:
                KeyBack(BtnBack, "BACK");
                break;

            case R.id.BtnCheck:
                KeyEnter(BtnCheck, "CHECK");
                break;

            case R.id.InCartBtn:
                if(InCartBtn.getAnimation()!=null){
                    InCartBtn.clearAnimation();
                    InCartBtn.setBackgroundResource(R.drawable.cartbeforebtn);
                }
                nowNum = KeyEnterTV.getText().toString();
                if(!nowNum.equalsIgnoreCase("")){
                    int num = Integer.parseInt(nowNum);
                    view_item_selected(InfintiApplication.totalItems.get(num-1),collunms.get(num-1));
                    KeyEnterTV.setText("");
                }else{
                    AlertMsg("번호 미입력", 0.5, 0.15, "NOT_ENTER", "alert_logo", "#F65656");
                }

                break;

            case R.id.PayBtn:
                stock.clear(); // 재고 어레이 비워주기 ( 결제하기 -> 결제취소 -> 물품 선택 시 에러 해결, product_out 에서 최종 stock 사용)

                if(SelectedItem.size() < 1){
//                    alert("선택된 상품이 없습니다.", "NORMAL");
                    Toast.makeText(getApplicationContext(), "선택된 상품이 없습니다.", Toast.LENGTH_SHORT).show();
                    return;
                } else{
                    for(int i=0;i<SelectedItem.size(); i++) {
                        firstLayer = (LinearLayout) cartlists.get(i).getChildAt(0);
                        secondLayer = (LinearLayout) firstLayer.getChildAt(1);
                        cartStock = (TextView)secondLayer.getChildAt(1);
                        thisStock = cartStock.getText().toString();
                        stock.add(Integer.parseInt(thisStock));
                    }
                }

                Intent payIntent = new Intent(getApplicationContext(), PayMethodActivity.class);
                String Totalnum = Main_OrderCount2.getText().toString().replaceAll("[^0-9]","");
                String Totalcost = Main_PayAmt2.getText().toString().replaceAll("[^0-9]","");
                payIntent.putExtra("Totalnum", Totalnum);
                payIntent.putExtra("Totalcost", Totalcost);
                payIntent.putExtra("selected", SelectedItem);
                payIntent.putExtra("stock",stock);
                startActivity(payIntent);
                break;

            case R.id.cart_add:

                if (!thisStock.equalsIgnoreCase(SelectedItem.get(cartIndex).getRemain_cnt())) {
                    int addStock = Integer.valueOf(thisStock) + 1;
                    cartStock.setText(addStock + "");
                    String itemcost = SelectedItem.get(cartIndex).getItem_cost();

                    if (itemcost.contains(".")) {
                        String cost[] = itemcost.split("\\.");
                        itemcost = cost[0];
                    }
                    String cost = myFormatter.format(Integer.valueOf(itemcost) * addStock);
                    cartCost.setText(cost + " 원"); // 아이템*개수 가격
                } else {
                    Toast.makeText(getApplicationContext(), "해당제품의 재고는 " + thisStock + " 개 입니다.", Toast.LENGTH_LONG).show();
                }

                break;

            case R.id.cart_minus:
                if (Integer.valueOf(thisStock) > 1) {
                    int deleteStock = Integer.valueOf(thisStock) - 1;
                    cartStock.setText(deleteStock + "");
                    String itemcost = SelectedItem.get(cartIndex).getItem_cost();

                    if (itemcost.contains(".")) {
                        String cost[] = itemcost.split("\\.");
                        itemcost = cost[0];
                    }
                    String cost = myFormatter.format(Integer.valueOf(itemcost) * deleteStock);
                    cartCost.setText(cost + " 원"); //아이템*개수 가격
                }
                break;

            case R.id.Item_cancel:

                String page[] = hashMap2.get(view.getTag()).split(","); // 취소할 아이템의 위치
                int ItemNumber = Integer.parseInt(page[0].replaceAll("[^0-9]","")); // 아이템

                InfintiApplication.machineMaster.getMachineColummInfo().get(ItemNumber).setChecked(false);
                // (장바구니) 해당 아이템 카트리스트 제거
                ViewParent viewParent = view.getParent();
                ViewGroup viewGroup = (ViewGroup) viewParent.getParent();
                cartIndex = cartlists.indexOf(viewGroup);

                Main_CartLi.removeView(cartlists.get(cartIndex));
                cartlists.remove(cartIndex);
                SelectedItem.remove(cartIndex);
                SelectedView.remove(cartIndex);
                break;
        }

        Reload_Cart();
    }

    // 숫자 눌렀을 때
    public void KeyPress(TextView Num, String SelectNum){
//        Num.setBackgroundResource(R.drawable.select_round);
        nowNum = KeyEnterTV.getText().toString();
        KeyEnterTV.setText(nowNum + SelectNum);
    }

    // 백스페이스 눌렀을 때
    public void KeyBack(TextView Num, String SelectNum){
//        Num.setBackgroundResource(R.drawable.select_round);
        nowNum = KeyEnterTV.getText().toString();
        if(nowNum.length()>0) nowNum = nowNum.substring(0,nowNum.length()-1);
        KeyEnterTV.setText(nowNum);
    }

    //체크버튼 눌렀을 때
    public void KeyEnter(TextView Num, String SelectNum){
        nowNum = KeyEnterTV.getText().toString();

        if(!nowNum.equalsIgnoreCase("") && Integer.parseInt(nowNum)<= collunms.size()){
            int num = Integer.parseInt(nowNum);
            // 장바구니 자동 스크롤뷰
            int Xposition = (num-2)*303;
            ObjectAnimator xTranslate = ObjectAnimator.ofInt(seatLegendLayout, "scrollX", Xposition);
            ObjectAnimator yTranslate = ObjectAnimator.ofInt(seatLegendLayout, "scrollY", 0);
            AnimatorSet animators = new AnimatorSet();
            animators.setDuration(1000);
            animators.playTogether(xTranslate, yTranslate);
            animators.start();
            animators.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    // 상품 배경 테두리 처리
                    View mView = ItemGridView.getChildAt(num-1);
                    mView.setBackgroundResource(R.drawable.selecteditem);
                    CartBtnBlinkAnim(num-1);
                    nowView = mView; //테두리 없애주기 위해 저장
                }
            });
        }else{
            AlertMsg("물품 재선택", 0.5, 0.15, "OUT_OF_RANGE_ITEM", "alert_logo", "#F65656");

        }

    }

    public void CartBtnBlinkAnim(int num){
        // 장바구니 담기 버튼 애니메이션
        InCartBtn.setBackgroundResource(R.drawable.cartbtn);
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.blinkin);
        InCartBtn.startAnimation(anim);
        InfintiApplication.totalItems.get(num).setPadding(3,3,3,3);
    }

    public void Reload_Cart(){
        int allCounts = 0;
        int totalCost = 0;
        int totalCount =0;
        for(ViewGroup LI : cartlists){
            LinearLayout first = (LinearLayout) LI.getChildAt(0); //ListLi
            LinearLayout SelectNumLayer = (LinearLayout) first.getChildAt(1); //SelectNumLi
            TextView countText = (TextView) SelectNumLayer.getChildAt(1);
            TextView costText = (TextView) first.getChildAt(2);
            totalCost += Integer.valueOf(costText.getText().toString().replaceAll("[^0-9]", ""));
            totalCount += Integer.valueOf(countText.getText().toString().replaceAll("[^0-9]", ""));
        }
        //총 가격
        String totalCosts = myFormatter.format(totalCost);
        String totalCounts = myFormatter.format(totalCount);
        allCounts = totalCount;
        Main_OrderCount2.setText(totalCounts + "개");
        Main_PayAmt2.setText(totalCosts + "원");
        Main_TotalAmt2.setText(totalCosts + "원");
    }

    private void AlertMsg(String title, double width, double height, String Type, @Nullable String logo, String mainColor){
        // type : 마지막버튼 - 0: 없음, 1: 확인, 2: 취소, 3: 영수
        Intent intent = new Intent(getApplicationContext(), PopupActivity.class);
        intent.putExtra("logo", logo);
        intent.putExtra("title", title);
        intent.putExtra("width", width);
        intent.putExtra("height", height);
        intent.putExtra("Type", Type);
        intent.putExtra("color", mainColor);
        startActivity(intent);
    }

}