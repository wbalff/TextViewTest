package com.example.administrator.textviewtest;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;

import android.text.SpannedString;
import android.text.style.ForegroundColorSpan;
import android.text.style.URLSpan;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
/*
 文本滚动条件：
 */

public class MainActivity extends Activity {
    private ZDYMarqueTextView mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mView= (ZDYMarqueTextView) findViewById(R.id.mView);
//        同一文本多种颜色效果，也可以通过方法二：使用SpannableString来实现
      /*  //Spanned继承与CharSequence,而setText里需要CharSequence
        //android 并不支持所有的HTML标签，只支持文本与段落相关的标签
        Spanned spanned=Html.fromHtml("欢迎参加四川大学软件设计大" +
                "赛,<font color='blue'>我也非常想安静</font>");
//     也可以通过<b></b>把字体加粗，<big>变得更大
        mView.setText(spanned);*/
        String text="欢迎参加四川大学软件设计大赛我也非常想安静";
        SpannableString spanText=new SpannableString(text);
        spanText.setSpan(new ForegroundColorSpan(Color.BLUE),14,21,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //这里千万要注意，不能写成22，若超过字符串的长度 则程序会启动时会停止运行
        //用超链接标记文本
        spanText.setSpan(new URLSpan("tel:4155551212"), 2, 5,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        mView.setText(spanText);
        mView.startMarquee();
        mView.setSpeed(5);
//        mView.setTime(10);
       // mView.setTextColor("#ff0000");没有创建此方法
    }
}
