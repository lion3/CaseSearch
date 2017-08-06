package com.example.xusy.casesearch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CaseList extends AppCompatActivity {
    private LinearLayout caseList;
    private RecordDataBase recordDataBase;
    private boolean status;
    private TextView CaseStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case_list);
        caseList = (LinearLayout) findViewById(R.id.caseList);
        caseList.setGravity(0);
        CaseStatus = (TextView)findViewById(R.id.CaseStatus);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        status = bundle.getBoolean("Status");
        InitData();
    }
    private void InitData() {
        if (status == false) {
            CaseStatus.setText("正在进行");
        } else {
            CaseStatus.setText("已完成");
        }
        recordDataBase = RecordDataBase.getRecordDataBaseInstance();
        for (int i = 0; i < RecordDataBase.getRecords().size(); i++) {
            if (status == RecordDataBase.getRecords().elementAt(i).getStatus()){
                addRecord(RecordDataBase.getRecords().elementAt(i));
            }
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
    private void addRecord(final Record record){
        CaseRecord caseRecord = new CaseRecord(this);
        caseRecord.setValue("审判案号："+record.getTrialNumber(),
                "执行案号："+record.getExcuteNumber(),"当事人："+mutiOutput(record.getOurParties()));
        caseRecord.setClickable(true);
        caseRecord.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                System.out.println("on click");
                Intent intent = new Intent(CaseList.this, CaseDetail.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("record",record);
                intent.putExtra("bundle",bundle);
                CaseList.this.startActivity(intent);
            }
        });

        caseList.addView(caseRecord);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK )
        {
            //这里写需要重写的方法
            Intent intent = new Intent(CaseList.this, Document.class);
            CaseList.this.setResult(1, intent);
            finish();
            return false;
        }
        return false;

    }
}
