package com.example.xusy.casesearch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Vector;

public class Document extends AppCompatActivity {
    private RecordDataBase recordDataBase;
    private TextView ProcessingNumber;
    private TextView DoneNumber;
    private TextView ProcessingItem;
    private TextView DoneItem;
    private TextView SortByParties;
    private TextView SortByJudges;
    private TextView SortByCaseNumber;
    private TextView SortByPayment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document);
        recordDataBase = RecordDataBase.getRecordDataBaseInstance();
        Vector<Record> records = (Vector<Record> )DataManager.read(null,"RecordsDataFile");
        if (records != null)
        RecordDataBase.setRecords(records);
        InitBtn();
        InitElements();
        updateElements();
    }
    @Override
    protected void onResume(){
        super.onResume();
        Vector<Record> records = (Vector<Record> )DataManager.read(null,"RecordsDataFile");
        if (records != null)
            RecordDataBase.setRecords(records);
    }
    @Override
    protected void onStop(){
        super.onStop();
        DataManager.write(null,RecordDataBase.getRecords(),"RecordsDataFile");
    }
    private void InitBtn(){
        ImageButton imageButton = (ImageButton)findViewById(R.id.addMoreCase);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Document.this, AddNewCase.class);
                Document.this.startActivityForResult(intent,0);
            }
        });
    }
    private void InitElements(){
        ProcessingNumber = (TextView)findViewById(R.id.ProcessingNumber);
        DoneNumber = (TextView)findViewById(R.id.DoneNumber);
        ProcessingItem = (TextView)findViewById(R.id.ProcessingItem);
        ProcessingItem.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setClass(Document.this, CaseList.class);
                Bundle bundle = new Bundle();
                bundle.putBoolean("Status",false);
                intent.putExtra("bundle",bundle);
                Document.this.startActivityForResult(intent,0);
            }
        });
        DoneItem = (TextView)findViewById(R.id.DoneItem);
        DoneItem.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setClass(Document.this, CaseList.class);
                Bundle bundle = new Bundle();
                bundle.putBoolean("Status",true);
                intent.putExtra("bundle",bundle);
                Document.this.startActivityForResult(intent,0);
            }
        });
        SortByParties = (TextView)findViewById(R.id.parties);
        SortByParties.setClickable(true);
        SortByParties.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Document.this, ClassifySearch.class);
                Bundle bundle = new Bundle();
                bundle.putInt("Class", 0);
                intent.putExtra("bundle",bundle);
                Document.this.startActivityForResult(intent,0);
            }
        });
        SortByJudges = (TextView)findViewById(R.id.Judge);
        SortByJudges.setClickable(true);
        SortByJudges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Document.this, ClassifySearch.class);
                Bundle bundle = new Bundle();
                bundle.putInt("Class", 2);
                intent.putExtra("bundle",bundle);
                Document.this.startActivityForResult(intent,0);
            }
        });
        SortByCaseNumber = (TextView)findViewById(R.id.ReferenceNumber);
        SortByCaseNumber.setClickable(true);
        SortByCaseNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Document.this, ClassifySearch.class);
                Bundle bundle = new Bundle();
                bundle.putInt("Class", 4);
                intent.putExtra("bundle",bundle);
                Document.this.startActivityForResult(intent,0);
            }
        });
        SortByPayment = (TextView)findViewById(R.id.Payment);
        SortByPayment.setClickable(true);
        SortByPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Document.this, ClassifySearch.class);
                Bundle bundle = new Bundle();
                bundle.putInt("Class", 6);
                intent.putExtra("bundle",bundle);
                Document.this.startActivityForResult(intent,0);
            }
        });
    }
    private void updateElements(){
        int processingNumber = 0;
        int doneNumber = 0;
        for (int i = 0; i < RecordDataBase.getRecords().size(); i++) {
            if (RecordDataBase.getRecords().get(i).getStatus() == false) {
                processingNumber++;
            } else if(RecordDataBase.getRecords().get(i).getStatus() == true) {
                doneNumber++;
            }
        }
        String s1 = String.valueOf(processingNumber);
        String s2 = String.valueOf(doneNumber);
        DoneNumber.setText(s2);
        ProcessingNumber.setText(s1);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        switch (resultCode) {
            case 0:
                Bundle bundle = data.getBundleExtra("bundle");
                Record record = (Record)bundle.getSerializable("record");
                recordDataBase.addRecord(record);
                updateElements();
                break;
            case 1:
                updateElements();
                break;
        }

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK )
        {
            //这里写需要重写的方法
            Intent intent = new Intent(Document.this, MainActivity.class);
            Document.this.setResult(1, intent);
            finish();
            return false;
        }
        return false;

    }
}
