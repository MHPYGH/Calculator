package com.example.calculator;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


//数据模型
public class MyViewModel extends ViewModel {
    private MutableLiveData<String> mainNum;//主数值(用户正在操作的数)
    public boolean havePoint = false;//主数值中是否包含小数点
    public String sign1 = "" , sign2 = "";//存储运算符
    public String num[] = {"",""};//存储参与运算的数值

    public MutableLiveData<String> getMainNum(){
        if(mainNum == null){
            mainNum = new MutableLiveData<String>();
            mainNum.setValue("0");
        }
        return mainNum;
    }

    public void setMainNum(String n){
        if(mainNum.getValue().equals("0")){
            mainNum.setValue(n);
        }else {
            mainNum.setValue(mainNum.getValue() + n);
        }
    }

    public String mainNum_with_num_0_toCal( String m){
        String value = "0";
        //如果其中一个数有小数点，那么运算结果必定含有小数点
        if(mainNum.getValue().contains(".") || num[0].contains(".")){
            switch (sign1){
                case "+":
                    value = String.valueOf(Double.valueOf(num[0]) + Double.valueOf(m));
                    break;
                case "-":
                    value = String.valueOf(Double.valueOf(num[0]) - Double.valueOf(m));
                    break;
                case "*":
                    value = String.valueOf(Double.valueOf(num[0]) * Double.valueOf(m));
                    break;
                case "/":
                    if (mainNum.getValue().equals("0"))
                        mainNum.setValue("1");
                    value = String.valueOf(Double.valueOf(num[0]) / Double.valueOf(m));
                    break;
            }
        }else {//如果两数值均为整数，那么运算结果也为整数，除法除外
            switch (sign1){
                case "+":
                    value = String.valueOf(Integer.valueOf(num[0]) + Integer.valueOf(m));
                    break;
                case "-":
                    value = String.valueOf(Integer.valueOf(num[0]) - Integer.valueOf(m));
                    break;
                case "*":
                    value = String.valueOf(Integer.valueOf(num[0]) * Integer.valueOf(m));
                    break;
                case "/":
                    if (mainNum.getValue().equals("0"))
                        mainNum.setValue("1");
                    value = String.valueOf(Double.valueOf(num[0]) / Double.valueOf(m));
                    break;
            }
        }
        return value;
    }

    public String mainNum_with_num_1_toCal(String m){
        String value = "0";
        //如果其中一个数有小数点，那么运算结果必定含有小数点
        if(mainNum.getValue().contains(".") || num[1].contains(".")){
            switch (sign2){
                case "*":
                    value = String.valueOf(Double.valueOf(num[1]) * Double.valueOf(m));
                    break;
                case "/":
                    if (mainNum.getValue().equals("0"))
                        mainNum.setValue("1");
                    value = String.valueOf(Double.valueOf(num[1]) / Double.valueOf(m));
                    break;
            }
        }else {//如果两数值均为整数，那么运算结果也为整数，除法除外
            switch (sign2){
                case "*":
                    value = String.valueOf(Integer.valueOf(num[1]) * Integer.valueOf(m));
                    break;
                case "/":
                    if (mainNum.getValue().equals("0"))
                        mainNum.setValue("1");
                    value = String.valueOf(Double.valueOf(num[1]) / Double.valueOf(m));
                    break;
            }
        }
        return value;
    }
}
