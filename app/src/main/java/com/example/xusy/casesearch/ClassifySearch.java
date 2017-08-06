package com.example.xusy.casesearch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

public class ClassifySearch extends AppCompatActivity {
    private EditText searchBox;
    private ImageButton searchButton;
    private LinearLayout layout1;
    private LinearLayout layout2;
    private TextView class1;
    private TextView class2;
    private static Map<String,Integer> datafield1;
    private static Map<String,Integer> datafield2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classify_search);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        int Class = bundle.getInt("Class");
        InitComponent(Class);
        InitData(Class);
        setData();
    }
    private void InitComponent(final int Class){
        searchBox = (EditText)findViewById(R.id.SearchBox);
        searchButton = (ImageButton)findViewById(R.id.SearchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyword = searchBox.getText().toString();
                ArrayList<Integer> result = search(keyword,Class);
                Intent intent = new Intent(ClassifySearch.this,SearchResult.class);
                Bundle bundle = new Bundle();
                bundle.putString("keyword", keyword);
                bundle.putIntegerArrayList("result", result);
                intent.putExtra("bundle",bundle);
                if (result.isEmpty()!=true)
                ClassifySearch.this.startActivity(intent);
            }
        });
        layout1 =(LinearLayout)findViewById(R.id.Classify1);
        layout2 =(LinearLayout)findViewById(R.id.Classify2);
        class1 =(TextView)findViewById(R.id.class1);
        class2 =(TextView)findViewById(R.id.class2);
        switch (Class) {
            case 0:
                class1.setText("我方当事人：");
                class2.setText("对方当事人：");
                break;
            case 2:
                class1.setText("审判法官：");
                class2.setText("执行法官：");
                break;
            case 4:
                class1.setText("审判案号：");
                class2.setText("执行案号：");
                break;
            case 6:
                class1.setText("收费方式：");
                class2.setText("付费情况/发票情况：");
                break;
        }
    }
    private void InitData(int Class){
        switch (Class) {
            case 0:
                datafield1 = RecordDataBase.getDataField(0);
                datafield2 = RecordDataBase.getDataField(1);
                break;
            case 2:
                datafield1 = RecordDataBase.getDataField(2);
                datafield2 = RecordDataBase.getDataField(3);
                break;
            case 4:
                datafield1 = RecordDataBase.getDataField(4);
                datafield2 = RecordDataBase.getDataField(5);
                break;
            case 6:
                datafield1 = RecordDataBase.getDataField(6);
                datafield2 = RecordDataBase.getDataField(7);
                break;
        }
    }
    private void setData(){
        for (String data1:datafield1.keySet()){
            TextView textView = new TextView(this);
            textView.setText(data1);
            layout1.addView(textView);
        }
        for (String data2:datafield2.keySet()){
            TextView textView = new TextView(this);
            textView.setText(data2);
            layout2.addView(textView);
        }
    }
    private ArrayList<Integer> search(String keyword,int Class) {
        ArrayList<Integer> result = new ArrayList<>();
        if (Class < 8) {
            for (String data1 : datafield1.keySet()) {
                if (data1.indexOf(keyword) >= 0) {
                    ArrayList<Integer> searchResult = RecordDataBase.searchData(Class, data1);
                    result.addAll(searchResult);
                }
            }
            for (String data2 : datafield2.keySet()) {
                if (data2.indexOf(keyword) >= 0) {
                    ArrayList<Integer> searchResult = RecordDataBase.searchData(Class + 1, data2);
                    result.addAll(searchResult);
                }
            }
        }
        return result;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK )
        {
            //这里写需要重写的方法
            Intent intent = new Intent(ClassifySearch.this, CaseList.class);
            ClassifySearch.this.setResult(1, intent);
            finish();
            return false;
        }
        return false;

    }
}
