package com.example.xusy.casesearch;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.util.AttributeSet;
import android.widget.TextView;
/**
 * Created by xusy on 2017/8/3.
 */

    public class CaseRecord extends LinearLayout {
    private String JudgeNum = "审判案号";
    private String ExcuteNum = "执行案号";
    private String Parties = "当事人";

    public TextView getJudgeNumber() {
        return judgeNumber;
    }

    private TextView judgeNumber;
    private TextView excuteNumber;
    private TextView parties;
    public CaseRecord(Context context){
        this(context,null);
    }
    public CaseRecord(final Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.case_record, this);
        judgeNumber=(TextView)findViewById(R.id.JudgeNumber1);
        excuteNumber=(TextView)findViewById(R.id.ExcuteNumber1);
        parties=(TextView)findViewById(R.id.Parties1);
    }

    public void setValue(String s1,String s2, String s3){
        judgeNumber.setText(s1);
        excuteNumber.setText(s2);
        parties.setText(s3);
    }




}
