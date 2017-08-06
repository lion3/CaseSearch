package com.example.xusy.casesearch;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by xusy on 2017/7/31.
 */

public class Record implements Serializable{
    private String[] OurParties;
    private String[] OpposititeParties;
    private String[] ResLitigiosa;
    private String TrialNumber;
    private String ExcuteNumber;
    private Date FilingTime;
    private String[] JudgeContactInfo;
    private String[] ExecuteJudgeInfo;
    private Date CourtDate;
    private Date ExecuteFilingTime;
    private String Dicastery;
    private String ContractNumber;
    private String ContractLife;
    private String tollCollectionManner;
    private String Payments; // 1000 paid
    private String Invoice;
    private boolean Status = false; // processing | done
   @Override
   public String toString(){
       String s = "";
       if (OurParties!=null) {
           for (String s1 : OurParties) {
               s += s1;
           }
       }
       if (OpposititeParties!=null) {
           for (String s1:OpposititeParties){
               s +=s1;
           }
       }
       if (ResLitigiosa!=null) {
           for (String s1:ResLitigiosa){
               s +=s1;
           }
       }

       if (TrialNumber!=null) {
           s+=TrialNumber;
       }
       if (ExcuteNumber!=null) {
           s+=ExcuteNumber;
       }
      if (FilingTime!=null){
          s+=FilingTime.toString();
      }
      if (JudgeContactInfo!=null) {
          for (String s1 : JudgeContactInfo) {
              s += s1;
          }
      }
       if (JudgeContactInfo!=null) {
           for (String s1:ExecuteJudgeInfo){
               s +=s1;
           }
       }
       if (CourtDate!=null)
       s+=CourtDate.toString();
       if (ExecuteFilingTime!=null)
       s+=ExecuteFilingTime.toString();
       if (Dicastery!=null)
       s+=Dicastery;
       if (ContractNumber!=null)
       s+=ContractNumber;
       if (ContractLife!=null)
       s+=ContractLife;
       if (tollCollectionManner!=null)
       s+=tollCollectionManner;
       if (Payments!=null)
       s+=Payments;
       if (Invoice!=null)
       s+=Invoice;
       return s;
   }
    public Date getExecuteFilingTime() {
        return ExecuteFilingTime;
    }

    public void setExecuteFilingTime(Date executeFilingTime) {
        ExecuteFilingTime = executeFilingTime;
    }

    public String[] getExecuteJudgeInfo() {
        return ExecuteJudgeInfo;
    }

    public void setExecuteJudgeInfo(String[] executeJudgeInfo) {
        ExecuteJudgeInfo = executeJudgeInfo;
    }

    public String getExcuteNumber() {
        return ExcuteNumber;
    }

    public void setExcuteNumber(String excuteNumber) {
        ExcuteNumber = excuteNumber;
    }

    public String[] getOurParties() {
        return OurParties;
    }

    public void setOurParties(String[] ourParties) {
        OurParties = ourParties;
    }

    public String[] getOpposititeParties() {
        return OpposititeParties;
    }

    public void setOpposititeParties(String[] opposititeParties) {
        OpposititeParties = opposititeParties;
    }

    public String[] getResLitigiosa() {
        return ResLitigiosa;
    }

    public void setResLitigiosa(String[] resLitigiosa) {
        ResLitigiosa = resLitigiosa;
    }

    public String getTrialNumber() {
        return TrialNumber;
    }

    public void setTrialNumber(String trialNumber) {
        TrialNumber = trialNumber;
    }

    public Date getFilingTime() {
        return FilingTime;
    }

    public void setFilingTime(Date filingTime) {
        FilingTime = filingTime;
    }

    public String[] getJudgeContactInfo() {
        return JudgeContactInfo;
    }

    public void setJudgeContactInfo(String[] judgeContactInfo) {
        JudgeContactInfo = judgeContactInfo;
    }

    public Date getCourtDate() {
        return CourtDate;
    }

    public void setCourtDate(Date courtDate) {
        CourtDate = courtDate;
    }

    public String getDicastery() {
        return Dicastery;
    }

    public void setDicastery(String dicastery) {
        Dicastery = dicastery;
    }

    public String getContractNumber() {
        return ContractNumber;
    }

    public void setContractNumber(String contractNumber) {
        ContractNumber = contractNumber;
    }

    public String getContractLife() {
        return ContractLife;
    }

    public void setContractLife(String contractLife) {
        ContractLife = contractLife;
    }

    public String getTollCollectionManner() {
        return tollCollectionManner;
    }

    public void setTollCollectionManner(String tollCollectionManner) {
        this.tollCollectionManner = tollCollectionManner;
    }

    public String getPayments() {
        return Payments;
    }

    public void setPayments(String payments) {
        Payments = payments;
    }

    public String getInvoice() {
        return Invoice;
    }

    public void setInvoice(String invoice) {
        Invoice = invoice;
    }

    public boolean getStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }


}
