package com.example.xusy.casesearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * Created by xusy on 2017/8/2.
 */

public class RecordDataBase {


    public void addRecord(Record record) {
        records.add(record);
    }
    public void deleteRecord(int index, Record record) {
        records.remove(index);
    }
    public int findRecord(Record record) {
        return records.indexOf(record);
    }
    public void updateRecord(int index, Record record) {
        records.setElementAt(record,index);
    }
    private static void initRecords(){
        records = new Vector<>();
    }

    public static Vector<Record> getRecords() {
        return records;
    }
    private static String[] dataClass(int Class, Record record){
        String[] data;
        switch (Class){
            case 0:data = record.getOurParties();
                break;
            case 1:data = record.getOpposititeParties();
                break;
            case 2:data = record.getJudgeContactInfo();
                break;
            case 3:data = record.getExecuteJudgeInfo();
                break;
            case 4:data = new String[1];
                data[0] = record.getTrialNumber();
                break;
            case 5:data = new String[1];
                data[0] = record.getExcuteNumber();
                break;
            case 6:data = new String[1];
                data[0] = record.getTollCollectionManner();
                break;
            case 7:data = new String[2];
                data[0] = record.getPayments();
                data[1] = record.getInvoice();
                break;
            default:
                data = record.getOurParties();
                break;
        }
        return data;
    }
    public static void setRecords(Vector<Record> records) {
        RecordDataBase.records = records;
    }
    public static ArrayList<Integer> searchData(int Class, String keyword) {
        ArrayList<Integer> indexs = new ArrayList<Integer>();
        for (int i = 0; i < records.size(); i++) {
            Record record = records.get(i);
            String[] data = dataClass(Class,record);
            if (Class < 2 || Class == 7) {
                for (int j = 0; j < data.length; j++) {
                    if (data[j].equals(keyword)) {
                        indexs.add(i);
                    }
                }
            } else if (Class < 7) {
                if (data[0].equals(keyword)) {
                    indexs.add(i);
                }
            }
        }
        return indexs;
    }
    public static Map<String,Integer> getDataField(int Class) {
        Map<String,Integer> datafield = new HashMap<>();

        for (int i = 0; i < records.size(); i++) {
            Record record = records.get(i);
            String[] data = dataClass(Class,record);
            switch (Class) {
                case 0:
                case 1:
                case 7:
                    for (int j = 0; j <data.length;j++){
                        datafield.put(data[j],i);
                    }
                    break;
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                    datafield.put(data[0],i);
                    break;
            }


        }
        return datafield;
    }
    private static Vector<Record> records;
    private RecordDataBase() {}
    private static RecordDataBase recordDataBase = null;
    public static RecordDataBase getRecordDataBaseInstance() {

            if (recordDataBase == null) {
                recordDataBase = new RecordDataBase();
                initRecords();
            }

        return recordDataBase;
    }
}
