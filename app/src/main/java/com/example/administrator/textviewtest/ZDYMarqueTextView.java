package com.example.administrator.textviewtest;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/1/17.
 */
public class ZDYMarqueTextView extends TextView {
//父类没有默认的无参数构造方法，所以这里必须实现构造方法
    private String mText;//文本框的内容
    private int mTextWidth;//文本的宽度
    //方便用户自己去设置，如果用户不设置的话，用系统默认的设置
    private  int time=100;//间隔时间
    private int speed=4;//偏移的距离
    private int xOffSet=0;//记录偏移量
    private Handler mHandler;//线程时间一到，就去刷新界面
    private boolean isStop=true;//
    //这里只需要设置就可以了 不需要得到 所以就只有setter方法

    public void setTime(int time) {
        this.time = time;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }


    public ZDYMarqueTextView(Context context) {
        super(context);
    }

    public ZDYMarqueTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void startMarquee() {//用于显示滚动效果
        mText=this.getText().toString();
        //根据文本的内容计算宽带
        mTextWidth= (int) getPaint().measureText(mText);
        mHandler=new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if(msg.what==0x11){//收到消息进行处理
                    xOffSet+=speed;
                    if(xOffSet>mTextWidth){//如果滚动完了，从头开始
                        xOffSet=0;
                        scrollTo(0,0);//从0开始
                    }else {
                        scrollTo(xOffSet,0);
                    }

                }
                super.handleMessage(msg);
            }
        };
         startThread();
    }

    public void startThread(){
        isStop=false;
        new Thread(){
            public void run(){
                while(!isStop){
//                while (true){//这是死循环
                    try {
                        Thread.sleep(time);
                        //每隔一段时间发送消息，消息要有一个标记
                        mHandler.sendEmptyMessage(0x11);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


            }
        }.start();//启动线程
    }
    //当触摸时控件停止运行
    public void pause(){//暂停滚动
        isStop=true;

    }
    public void goOn(){//继续滚动
        startThread();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                pause();
                break;
            default:
                goOn();
                break;

        }
        return true;
    }
}
