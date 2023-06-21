package com.example.calculator.my_control;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/*
*自定义组件：带有边框的标签
*/

public class MyTextView extends androidx.appcompat.widget.AppCompatTextView {
    Paint paint = new Paint();

    public MyTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onDrawForeground(Canvas canvas) {
        super.onDrawForeground(canvas);
        paint.setColor(Color.BLACK);
        //绘制边框
        canvas.drawLine(0,0,this.getWidth(),0,paint);//绘制上方的边框
        canvas.drawLine(0,this.getHeight()-1,this.getWidth(),this.getHeight()-1,paint);//绘制下方的边框
        canvas.drawLine(0,0,0,this.getHeight(),paint);//绘制左方的边框
        canvas.drawLine(this.getWidth()-1,0,this.getWidth()-1,this.getHeight(),paint);//绘制右方的边框
    }
}
