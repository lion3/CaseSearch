package com.example.xusy.casesearch;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddNewCase extends AppCompatActivity {
    public Record record;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        final EditText OurParties = (EditText)findViewById(R.id.OurParties);
        final EditText OppositeParties = (EditText)findViewById(R.id.OppositeParties);
        final EditText ResLitigiosa = (EditText)findViewById(R.id.ResLitigiosa);
        final EditText TrialNumber = (EditText)findViewById(R.id.TrialNumber);
        final EditText ExcuteNumber = (EditText)findViewById(R.id.ExcuteNumber);
        final EditText FilingTime = (EditText)findViewById(R.id.FilingTime);
        final EditText JudgeContactInfo = (EditText)findViewById(R.id.JudgeContactInfo);
        final EditText CourtDate = (EditText)findViewById(R.id.CourtDate);
        final EditText Dicastery = (EditText)findViewById(R.id.Dicastery);
        final EditText ExecuteFilingTime = (EditText)findViewById(R.id.FilingTime2);
        final EditText ExecuteJudgeInfo = (EditText)findViewById(R.id.ExecuteJudgeInfo);
        final EditText ContractNumber = (EditText)findViewById(R.id.ContractNumber);
         final EditText ContractLife = (EditText)findViewById(R.id.ContractLife);
         final EditText TollCollectionManner = (EditText)findViewById(R.id.TollCollectionManner);
         final EditText Payments = (EditText)findViewById(R.id.Payment);
         final EditText Invoice = (EditText)findViewById(R.id.Invoice);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        record = new Record();

        final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        RecordDataBase recordDataBase = RecordDataBase.getRecordDataBaseInstance();


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] ourParties;
                ourParties = ProcessMutiInput(OurParties.getText().toString());
                record.setOurParties(ourParties);

                String[] oppositeParties;
                oppositeParties = ProcessMutiInput(OppositeParties.getText().toString());
                record.setOpposititeParties(oppositeParties);

                String[] resLitigiosa;
                resLitigiosa = ProcessMutiInput(ResLitigiosa.getText().toString());
                record.setResLitigiosa(resLitigiosa);

                String[] judgeInfo;
                judgeInfo = ProcessMutiInput(JudgeContactInfo.getText().toString());
                record.setJudgeContactInfo(judgeInfo);
                
                String[] executeJudgeInfo;
                executeJudgeInfo = ProcessMutiInput(ExecuteJudgeInfo.getText().toString());
                record.setExecuteJudgeInfo(executeJudgeInfo);

                record.setContractNumber(ContractNumber.getText().toString());
                record.setContractLife(ContractLife.getText().toString());
                record.setTollCollectionManner(TollCollectionManner.getText().toString());
                record.setPayments(Payments.getText().toString());
                record.setInvoice(Invoice.getText().toString());
                record.setTrialNumber(TrialNumber.getText().toString());
                record.setExcuteNumber(ExcuteNumber.getText().toString());
                record.setDicastery(Dicastery.getText().toString());

                try {
                    if (FilingTime.getText()!=null)
                        record.setFilingTime(sdf.parse(FilingTime.getText().toString()));


                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (ExecuteFilingTime.getText()!=null)
                    try {
                        record.setExecuteFilingTime(sdf.parse(ExecuteFilingTime.getText().toString()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                if (CourtDate.getText()!=null)
                    try {
                        record.setCourtDate(sdf.parse(CourtDate.getText().toString()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                Intent intent = new Intent(AddNewCase.this, Document.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("record",record);
                intent.putExtra("bundle",bundle);
                Snackbar.make(view, "已添加新的案件", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                AddNewCase.this.setResult(0, intent);
                finish();
            }
        });
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK )
        {
            //这里写需要重写的方法
            Intent intent = new Intent(AddNewCase.this, Document.class);
            AddNewCase.this.setResult(1, intent);
            finish();
            return false;
        }
        return false;

    }
    private String[] ProcessMutiInput(String input) {
        String[] result;
        result = input.split(" ");
        return result;
    }
}





