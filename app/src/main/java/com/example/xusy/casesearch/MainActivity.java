package com.example.xusy.casesearch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;


public class MainActivity extends AppCompatActivity {

    private SimpleDateFormat sdf;
    private Date today;
    private Date tomorrow;
    private TextView today1;
    private TextView today2;
    private TextView tomorrow1;
    private TextView tomorrow2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        today = new Date();
        tomorrow = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(today);
        calendar.add(calendar.DATE,1);
        calendar.setTime(tomorrow);
        Vector<Record> records = (Vector<Record> )DataManager.read(null,"RecordsDataFile");
        if (records != null)
            RecordDataBase.setRecords(records);
        InitBtn();
        InitData();
        updateData();
    }
    private void InitData(){
        today1 = (TextView)findViewById(R.id.today1);
        today2 = (TextView)findViewById(R.id.today2);
        tomorrow1 = (TextView)findViewById(R.id.tomorrow1);
        tomorrow2 = (TextView)findViewById(R.id.tomorrow2);
    }
    private void updateData() {
        ArrayList<Record> todayRecord = new ArrayList<>();
        ArrayList<Record> tomorrowRecord = new ArrayList<>();
        if (RecordDataBase.getRecords()!=null) {
            for (Record record : RecordDataBase.getRecords()) {
                if (record.getCourtDate()!=null) {
                    record.getCourtDate();
                    if (record.getCourtDate().getDay() == today.getDay()) {
                        todayRecord.add(record);
                    }
                }
                if (record.getCourtDate()!=null) {
                    record.getCourtDate();
                    if (record.getCourtDate().getDay() == tomorrow.getDay()) {
                        tomorrowRecord.add(record);
                    }
                }
            }

            if (todayRecord.size() > 0) {
                today1.setText(todayRecord.get(0).getTrialNumber());
            }
            if (todayRecord.size() > 1) {
                today2.setText(todayRecord.get(1).getTrialNumber());
            }
            if (tomorrowRecord.size() > 0) {
                tomorrow1.setText(tomorrowRecord.get(0).getTrialNumber());
            }
            if (tomorrowRecord.size() > 1) {
                tomorrow1.setText(tomorrowRecord.get(1).getTrialNumber());
            }
        }

    }
    private void InitBtn() {
        Button next = (Button) findViewById(R.id.button);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, Document.class);
                MainActivity.this.startActivityForResult(intent,0);
            }


        });
    }
    @Override
    protected void onResume(){
        super.onResume();
        Vector<Record> records = (Vector<Record> )DataManager.read(null,"RecordsDataFile");
        if (records != null)
            RecordDataBase.setRecords(records);
        updateData();
        System.out.println("ON RESUME");
    }
    @Override
    protected void onStop(){
        super.onStop();
        DataManager.write(null,RecordDataBase.getRecords(),"RecordsDataFile");
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        switch (resultCode) {
           default:
               updateData();
               System.out.println("ON ACRESUME");
                break;
        }

    }
}