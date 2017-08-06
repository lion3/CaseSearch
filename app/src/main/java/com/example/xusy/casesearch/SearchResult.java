package com.example.xusy.casesearch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class SearchResult extends AppCompatActivity {
    private TextView keyWord;
    private LinearLayout resultLayout;
    private String keyword;
    private  ArrayList<Integer> result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        initElement();
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        keyword = bundle.getString("keyword");
        result = bundle.getIntegerArrayList("result");
        initData();
    }
    private void initElement(){
        keyWord = (TextView)findViewById(R.id.keyword);
        resultLayout = (LinearLayout)findViewById(R.id.resultLayout);
    }
    private void initData() {
        for (int i = 0; i < result.size();i++){
            Record record = RecordDataBase.getRecords().get(result.get(i));
            addCaseInfo(record);
        }

    }
    private String mutiOutput(String[] strings) {
        String result = "";
        for (int i=0; i < strings.length; i++) {
            result += strings[i];
            result += ";";
        }
        return  result;
    }
    private void addCaseInfo(final Record record){
      //  LinearLayout caseInfo = new LinearLayout(this);
        CaseRecord caseRecord = new CaseRecord(this);
        caseRecord.setValue("审判案号："+record.getTrialNumber(),
                "执行案号："+record.getExcuteNumber(),"当事人："+mutiOutput(record.getOurParties()));
        caseRecord.setClickable(true);
        caseRecord.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                System.out.println("on click");
                Intent intent = new Intent(SearchResult.this, CaseDetail.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("record",record);
                intent.putExtra("bundle",bundle);
                SearchResult.this.startActivity(intent);
            }
        });
        resultLayout.addView(caseRecord);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK )
        {
            //这里写需要重写的方法
            Intent intent = new Intent(SearchResult.this, ClassifySearch.class);
            SearchResult.this.setResult(1, intent);
            finish();
            return false;
        }
        return false;

    }
}
