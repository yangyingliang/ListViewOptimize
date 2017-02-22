package com.example.yyl.myapplication;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayAdapter<String> arrayAdapter ;
    private List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listview);
        for(int i = 0;i < 50;i++){
           list.add("第"+ i + "个数");
        }
        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1 ,list);
        listView.setAdapter(arrayAdapter);
        setListViewHeight(listView);
    }

    private void setListViewHeight(ListView lv) {
        ListAdapter listAdapter = lv.getAdapter();
        if(listAdapter == null){
            return;
        }
        int totalHeight = 0;
        for(int i = 0;i < listAdapter.getCount();i++){
            View listItem = listAdapter.getView(i,null,lv);
            listItem.measure(0,0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams parms = lv.getLayoutParams();
        int divderHeight = lv.getDividerHeight() * (listAdapter.getCount() - 1);
        parms.height = totalHeight + divderHeight;
        lv.setLayoutParams(parms);
    }

//动态的设置ListView高度（拿到所有item的高度，设置到listView身上）
//private void setListViewHeight(ListView listview) {
//    //获取listView对应的Adapter
//    ListAdapter listAdapter = listview.getAdapter();
//    if (listAdapter == null){
//        return;
//    }
//    int totalHeight = 0;
//    for (int i = 0; i < listAdapter.getCount(); i++) {
//        View listItem = listAdapter.getView(i,null,listview);
//        //计算item的宽高（调用一个绘制的方法）
//        listItem.measure(0,0);
//        //统计所有子项的高度
//        totalHeight += listItem.getMeasuredHeight();
//    }
//
//    //拿到listView布局参数
//    ViewGroup.LayoutParams parms = listview.getLayoutParams();
//    //获取分割线的总高度
//    int dividerHeight = listview.getDividerHeight() * (listAdapter.getCount() - 1);
//
//    parms.height= totalHeight + dividerHeight;
//    listview.setLayoutParams(parms);
//}
    //3种方式前提是ScrollView里只能有一个控件，里面是线性布局，然后是ListView
    //1.给ListView一个固定高度
    //2.动态的给高度
    //3.自定义ListView,重写onMeasure方法
    // int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >>2,MeasureSpec.AT_MOST);
    //super.onMeasure(widthMeasureSpec, expandSpec);
}
