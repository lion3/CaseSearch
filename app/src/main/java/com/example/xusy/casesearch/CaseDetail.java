package com.example.xusy.casesearch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class CaseDetail extends AppCompatActivity {
    private TextView D_OurP;
    private TextView D_OppoP;
    private TextView D_Res;
    private TextView D_TrailN;
    private TextView D_ExcuteN;
    private TextView D_FilingT;
    private TextView D_JudgeCI;
    private TextView D_courtD;
    private TextView D_Dica;
    private TextView D_ExFilingT;
    private TextView D_ExJudgeI;
    private TextView D_ContractN;
    private TextView D_ContractL;
    private TextView D_Toll;
    private TextView D_Payment;
    private TextView D_Invoice;
    private Button ChangeStatus;
    private Button DeleteCase;
    private boolean Status;
    private Record record;
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case_detail);
        InitData();
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        record = (Record)bundle.getSerializable("record");
        Status = record.getStatus();
        UpdateDetail(record);
    }
    private void InitData(){
        DeleteCase = (Button)findViewById(R.id.deleteCase);
        ChangeStatus = (Button)findViewById(R.id.ChangeStatus);
        D_OurP = (TextView)findViewById(R.id.DetailOurParties);
        D_OppoP = (TextView)findViewById(R.id.DetailOppositeParties);
        D_Res = (TextView)findViewById(R.id.DetailResLitigiosa);
        D_TrailN = (TextView)findViewById(R.id.DetailTrialNumber);
        D_ExcuteN = (TextView)findViewById(R.id.DetailExcuteNumber);
        D_FilingT = (TextView)findViewById(R.id.DetailFilingTime);
        D_JudgeCI = (TextView)findViewById(R.id.DetailJudgeContactInfo);
        D_courtD = (TextView)findViewById(R.id.DetailCourtDate);
        D_Dica = (TextView)findViewById(R.id.DetailDicastery);
        D_ExFilingT = (TextView)findViewById(R.id.DetailExecuteFilingT);
        D_ExJudgeI = (TextView)findViewById(R.id.DetailExecuteJudgeInfo);
        D_ContractN = (TextView)findViewById(R.id.DetailContractNumber);
        D_ContractL = (TextView)findViewById(R.id.DetailContractLife);
        D_Toll = (TextView)findViewById(R.id.DetailTollCollectionManner);
        D_Payment = (TextView)findViewById(R.id.DetailPayment);
        D_Invoice = (TextView)findViewById(R.id.DetailInvoice);
    }
    private String mutiOutput(String[] strings) {
        String result = "";
        for (int i=0; i < strings.length; i++) {
            result += strings[i];
            result += ";";
        }
        return  result;
    }
    private void UpdateDetail(final Record record) {
        DeleteCase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Record record1:RecordDataBase.getRecords()){
                    if (record1.toString().equals(record.toString())){
                        RecordDataBase.getRecords().remove(record1);
                        finish();
                    }
                }

            }
        });
        if (Status == false) {
            ChangeStatus.setText("已完成");
            ChangeStatus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (Record record1:RecordDataBase.getRecords()){
                       if (record1.toString().equals(record.toString())){
                           record1.setStatus(true);
                           finish();
                       }
                    }

                }
            });
        } else {
            ChangeStatus.setText("未完成");
            ChangeStatus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (Record record1:RecordDataBase.getRecords()){
                        if (record1.toString().equals(record.toString())){
                            record1.setStatus(false);
                            finish();
                        }
                    }

                }
            });
        }
        if (record.getOurParties()!=null)
            D_OurP.setText("我方当事人："+mutiOutput(record.getOurParties()));
        if (record.getOpposititeParties()!=null)
            D_OppoP.setText("对方当事人："+ mutiOutput(record.getOpposititeParties()));
        if (record.getResLitigiosa()!=null)
            D_Res.setText("争议标的："+ mutiOutput(record.getResLitigiosa()));
        if (record.getTrialNumber()!=null)
            D_TrailN.setText("审判案号："+record.getTrialNumber().toString());
        if (record.getExcuteNumber()!=null)
            D_ExcuteN.setText("执行案号："+record.getExcuteNumber().toString());
        if (record.getFilingTime()!=null)
            D_FilingT.setText("立案时间："+sdf.format(record.getFilingTime()));
        if (record.getJudgeContactInfo()!=null)
            D_JudgeCI.setText("法官联系方式："+mutiOutput(record.getJudgeContactInfo()));
        if (record.getCourtDate()!=null)
            D_courtD.setText("开庭时间："+sdf.format(record.getCourtDate()));
        if (record.getDicastery()!=null)
            D_Dica.setText("开庭地点："+record.getDicastery().toString());
        if (record.getExecuteFilingTime()!=null)
            D_ExFilingT.setText("执行立案时间："+sdf.format(record.getExecuteFilingTime()));
        if (record.getExecuteJudgeInfo()!=null)
            D_ExJudgeI.setText("法官联系方式："+mutiOutput(record.getExecuteJudgeInfo()));
        if (record.getContractNumber()!=null)
            D_ContractN.setText("合同号："+record.getContractNumber().toString());
        if (record.getContractLife()!=null)
            D_ContractL.setText("合同有效期："+record.getContractLife().toString());
        if (record.getTollCollectionManner()!=null)
            D_Toll.setText("收费方式："+record.getTollCollectionManner().toString());
        if (record.getPayments()!=null)
            D_Payment.setText("付费情况："+record.getPayments().toString());
        if (record.getInvoice()!=null)
            D_Invoice.setText("发票情况："+record.getInvoice().toString());
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK )
        {
            //这里写需要重写的方法
            Intent intent = new Intent(CaseDetail.this, CaseList.class);
            CaseDetail.this.setResult(1, intent);
            finish();
            return false;
        }
        return false;

    }
}
