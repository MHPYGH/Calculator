package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.example.calculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;//存储组件
    private MyViewModel myViewModel;//数据模型

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获取所有组件
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        //获取数据模型
        myViewModel = new ViewModelProvider(this,new ViewModelProvider.NewInstanceFactory()).get(MyViewModel.class);

        //事件监听
        myViewModel.getMainNum().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {//监听mainNum数据发生改变
                //让 myTextView 显示mainNum的数值
                binding.myTextView.setText(myViewModel.getMainNum().getValue());

                //让 textView 显示计算公式
                if(myViewModel.sign2.equals("")){
                    if(myViewModel.sign1.equals("")){
                        binding.textView2.setText(myViewModel.getMainNum().getValue());
                    }else {//计算公式为 a ？ b 类型时
                        binding.textView2.setText(myViewModel.num[0] + myViewModel.sign1 + myViewModel.getMainNum().getValue());
                    }
                }else {//若运算表达式 如 a + b * c
                    binding.textView2.setText(myViewModel.num[0] + myViewModel.sign1 + myViewModel.num[1] + myViewModel.sign2 + myViewModel.getMainNum().getValue());
                }
            }
        });
        binding.button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewModel.setMainNum("0");
            }
        });
        binding.button00.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewModel.setMainNum("00");
            }
        });
        binding.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewModel.setMainNum("1");
            }
        });
        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewModel.setMainNum("2");
            }
        });
        binding.button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewModel.setMainNum("3");
            }
        });
        binding.button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewModel.setMainNum("4");
            }
        });
        binding.button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewModel.setMainNum("5");
            }
        });
        binding.button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewModel.setMainNum("6");
            }
        });
        binding.button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewModel.setMainNum("7");
            }
        });
        binding.button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewModel.setMainNum("8");
            }
        });
        binding.button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewModel.setMainNum("9");
            }
        });


        binding.buttonPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!myViewModel.havePoint){
                    myViewModel.getMainNum().setValue(myViewModel.getMainNum().getValue() + ".");
                    myViewModel.havePoint = true;
                }
            }
        });

        binding.buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(myViewModel.sign1.equals("")){
                    myViewModel.sign1 = "+";
                    myViewModel.num[0] = myViewModel.getMainNum().getValue();
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint = false;
                }else if(myViewModel.sign2.equals("")){
                    myViewModel.num[0] = myViewModel.mainNum_with_num_0_toCal(myViewModel.getMainNum().getValue());
                    myViewModel.sign1 = "+";
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint = false;
                }else {//第二个运算符优先级高于第一个
                    myViewModel.num[1] = myViewModel.mainNum_with_num_1_toCal(myViewModel.getMainNum().getValue());
                    myViewModel.num[0] = myViewModel.mainNum_with_num_0_toCal(myViewModel.num[1]);
                    myViewModel.num[1] = "";
                    myViewModel.sign1 = "+";
                    myViewModel.sign2 = "";
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint = false;
                }
            }
        });
        binding.buttonReduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(myViewModel.sign1.equals("")){
                    myViewModel.sign1 = "-";
                    myViewModel.num[0] = myViewModel.getMainNum().getValue();
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint = false;
                }else if(myViewModel.sign2.equals("")){
                    myViewModel.num[0] = myViewModel.mainNum_with_num_0_toCal(myViewModel.getMainNum().getValue());
                    myViewModel.sign1 = "-";
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint = false;
                }else {//第二个运算符优先级高于第一个
                    myViewModel.num[1] = myViewModel.mainNum_with_num_1_toCal(myViewModel.getMainNum().getValue());
                    myViewModel.num[0] = myViewModel.mainNum_with_num_0_toCal(myViewModel.num[1]);
                    myViewModel.num[1] = "";
                    myViewModel.sign1 = "-";
                    myViewModel.sign2 = "";
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint = false;
                }
            }
        });

        binding.buttonRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(myViewModel.sign1.equals("")){
                    myViewModel.sign1 = "*";
                    myViewModel.num[0] = myViewModel.getMainNum().getValue();
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint = false;
                }else if(myViewModel.sign2.equals("")){
                    if(myViewModel.sign1.equals("*") || myViewModel.sign1.equals("/")){//顺序计算
                        myViewModel.num[0] = myViewModel.mainNum_with_num_0_toCal(myViewModel.getMainNum().getValue());
                        myViewModel.sign1 = "*";
                        myViewModel.getMainNum().setValue("0");
                        myViewModel.havePoint = false;
                    }else {
                        myViewModel.num[1] = myViewModel.getMainNum().getValue();
                        myViewModel.sign2 = "*";
                        myViewModel.getMainNum().setValue("0");
                        myViewModel.havePoint = false;
                    }
                }else{//若运算表达式 如 a + b * c
                    myViewModel.num[1] = myViewModel.mainNum_with_num_1_toCal(myViewModel.getMainNum().getValue());
                    myViewModel.sign2 = "*";
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint = false;
                }
            }
        });
        binding.buttonDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(myViewModel.sign1.equals("")){
                    myViewModel.sign1 = "/";
                    myViewModel.num[0] = myViewModel.getMainNum().getValue();
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint = false;
                }else if(myViewModel.sign2.equals("")){
                    if(myViewModel.sign1.equals("*") || myViewModel.sign1.equals("/")){//顺序计算
                        myViewModel.num[0] = myViewModel.mainNum_with_num_0_toCal(myViewModel.getMainNum().getValue());
                        myViewModel.sign1 = "/";
                        myViewModel.getMainNum().setValue("0");
                        myViewModel.havePoint = false;
                    }else {
                        myViewModel.num[1] = myViewModel.getMainNum().getValue();
                        myViewModel.sign2 = "/";
                        myViewModel.getMainNum().setValue("0");
                        myViewModel.havePoint = false;
                    }
                }else{//若运算表达式 如 a + b * c
                    myViewModel.num[1] = myViewModel.mainNum_with_num_1_toCal(myViewModel.getMainNum().getValue());
                    myViewModel.sign2 = "/";
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint = false;
                }
            }
        });

        binding.buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewModel.sign2 = "";
                myViewModel.num[1] = "";
                myViewModel.sign1 = "";
                myViewModel.num[0] = "";
                myViewModel.getMainNum().setValue("0");
                myViewModel.havePoint = false;
            }
        });

        binding.buttonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(myViewModel.sign2.equals("")){
                    if(!myViewModel.sign1.equals("")){
                        myViewModel.getMainNum().setValue(myViewModel.mainNum_with_num_0_toCal(myViewModel.getMainNum().getValue()));
                        if(myViewModel.getMainNum().getValue().contains("."))
                            myViewModel.havePoint = true;
                        else
                            myViewModel.havePoint = false;
                        myViewModel.num[0] = "";
                        myViewModel.sign1 = "";
                    }
                }else {//表达式为 a + b * c
                    myViewModel.getMainNum().setValue(myViewModel.mainNum_with_num_1_toCal(myViewModel.getMainNum().getValue()));
                    myViewModel.num[1] = "";
                    myViewModel.sign2 = "";
                    myViewModel.getMainNum().setValue(myViewModel.mainNum_with_num_0_toCal(myViewModel.getMainNum().getValue()));
                    myViewModel.num[0] = "";
                    myViewModel.sign1 = "";
                    if(myViewModel.getMainNum().getValue().contains("."))
                        myViewModel.havePoint = true;
                    else
                        myViewModel.havePoint = false;
                    myViewModel.num[0] = "";
                    myViewModel.sign1 = "";
                }
                binding.textView2.setText(myViewModel.getMainNum().getValue());
            }
        });

        binding.buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!myViewModel.getMainNum().getValue().equals("0")){
                    myViewModel.getMainNum().setValue(myViewModel.getMainNum().getValue().substring(0,myViewModel.getMainNum().getValue().length()-1));
                    if(myViewModel.getMainNum().getValue().equals("")){
                        myViewModel.getMainNum().setValue("0");
                    }
                }
            }
        });
    }
}