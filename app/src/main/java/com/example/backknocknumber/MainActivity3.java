package com.example.backknocknumber;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.widget.Button;
import android.content.pm.ActivityInfo;//縦画面に固定する為のimport
import android.graphics.Color;
import android.os.Bundle;

import android.hardware.Sensor;//Sensorクラスのハードウェアを利用するためのimport
import android.hardware.SensorEvent;//SensorEventクラスのハードウェアを利用するためのimport,onSensorChangedメソッドの引数に持たせる
import android.hardware.SensorEventListener;//implements SensorEventListenerと継承させるためのimport
import android.hardware.SensorManager;//SensorManagerクラスのハードウェア（センサ類）を利用するためのimport
import android.os.Handler;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;//TextViewクラスのウィジェットを利用する為のimport
import android.view.View;//イベントの発生源をViewオブジェクトに渡すためのimport
import android.widget.Toast;//Toast表示を使えるようにする


//import com.github.mikephil.charting.data.Entry;
//import com.github.mikephil.charting.data.LineData;
//import com.github.mikephil.charting.charts.LineChart;//LineChartを利用できるようにする
//import com.github.mikephil.charting.data.LineDataSet;
//import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
//ファイル操作用
import android.content.Context;

import java.util.Arrays;//ソート用
import java.io.BufferedReader;
import java.io.File;//ファイルの存在を確認、削除するため
import java.io.FileInputStream;//ファイルを開く為の変数の宣言に必要
import java.io.FileOutputStream;//ファイルを作る為の変数の宣言に必要

import java.io.InputStreamReader;


//認証画面のクラス
public class MainActivity3 extends AppCompatActivity {
    private Common common;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        common=(Common) getApplication();
        common.initB();

        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main3);

        MyView3 myView3 = new MyView3(this);
        MyView4 myView4 = new MyView4(this);
        setContentView(myView3);

        int endPosition = 6870; //画面の最終到達位置
        TestAnimation3 testAnimation3 = new TestAnimation3(myView3, endPosition);
        testAnimation3.setDuration(10000);
        testAnimation3.setRepeatCount(3);
        myView3.startAnimation(testAnimation3);
        testAnimation3.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                System.out.println("アニメーション4が終了しました");
                setContentView(myView4);
                TestAnimation4 testAnimation4 = new TestAnimation4(myView4, 0);
                testAnimation4.setDuration(10000);
                myView4.startAnimation(testAnimation4);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public class MyView3 extends View {
        Paint paint;
        Path path;

        float dp;

        public MyView3(Context context) {
            super(context);
            paint = new Paint();
            path = new Path();

            // スクリーンサイズからdipのようなものを作る
            DisplayMetrics metrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(metrics);

            dp = getResources().getDisplayMetrics().density;
            Log.d("debug", "fdp=" + dp);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            // 背景
            canvas.drawColor(Color.argb(255, 255, 255, 255));


            float x = getWidth();
            float y = getHeight();
            //System.out.println(x);
            //System.out.println(y);
            float xc = x / 2;
            float yc = y / 2;
            float xc2 = xc / 2;
            float xc3 = xc + xc2;
            float yc2 = yc / 2;
            float yc3 = yc + yc2;
            float yc4 = 241;
            float yr = yc3 + 25;

            // 線
            paint.setStrokeWidth(10);
            paint.setColor(Color.argb(255, 0, 0, 0));
            // (x1,y1,x2,y2,paint) 始点の座標(x1,y1), 終点の座標(x2,y2)
            canvas.drawLine(xc2 + 0 * dp, yc - (yc - 1) * dp, xc2 - 0 * dp, yc + (yc - 1) * dp, paint);
            canvas.drawLine(xc3 + 0 * dp, yc - (yc - 1) * dp, xc3 + 0 * dp, yc + (yc - 1) * dp, paint);
            canvas.drawLine(xc - (xc - 1) * dp, yc + yc4 + 0 * dp, xc + (xc - 1) * dp, yc + yc4 + 0 * dp, paint);
            canvas.drawLine(xc - (xc - 1) * dp, yc + yc4 + xc + 0 * dp, xc + (xc - 1) * dp, yc + yc4 + xc + 0 * dp, paint);


            paint.setColor(Color.argb(255, 0, 0, 0));
            paint.setStrokeWidth(10);
            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.STROKE);
            // (x1,y1,r,paint) 中心x1座標, 中心y1座標, r半径
            // canvas.drawCircle(xc - 0*dp, yr+0*dp, xc / 2, paint);
            //canvas.drawCircle(xc - 0*dp, yr-xc+0*dp, xc / 2, paint);
            //canvas.drawCircle(xc - 0*dp, yr-(2*xc)+0*dp, xc / 2, paint);
            //canvas.drawCircle(xc - 0*dp, yr-(3*xc)+0*dp, xc / 2, paint);
            for (int num = 0; num < 10; num++) {
                paint.setStyle(Paint.Style.FILL);
                paint.setColor(Color.argb(255, 255, 255, 255));
                canvas.drawCircle(xc - 0 * dp, common.yv2 - (num * xc) + 0 * dp, xc / 2, paint);
                paint.setStyle(Paint.Style.STROKE);
                paint.setColor(Color.argb(255, 0, 0, 0));
                canvas.drawCircle(xc - 0 * dp, common.yv2 - (num * xc) + 0 * dp, xc / 2, paint);


            }
            for (int num = 0; num < 10; num++) {
                paint.setStyle(Paint.Style.FILL_AND_STROKE);
                paint.setStrokeWidth(5);
                paint.setTextSize(200);
                paint.setColor(Color.argb(255, 0, 0, 0));

                Integer i = Integer.valueOf(num);
                String str = i.toString();
                canvas.drawText(str, xc - 20 * dp, common.yv2 - (num * xc) + 20 * dp, paint);
            }
            float a = (common.yv2 - 970) / xc;
            int ia = (int) a;
            if (ia >= 0 && ia < 10) {
                //System.out.println(ia);
            }
            //System.out.println((yv));
            if (common.yv2 == 6870) {
                //System.out.println(count);
                common.count2++;
            }
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    //System.out.println(count);
                    //System.out.println(yv);
                    int xc = 540;
                    int num = (common.yv2 - 970) / xc;
                    switch (common.count2) {
                        case 0:
                            if (common.Bf0 == 0) {
                                common.Bn0 = num;
                                System.out.println(common.Bn0);
                                common.Bf0 = 1;
                            } else {
                                System.out.println("既に入力されています。");
                            }
                            break;
                        case 1:
                            if (common.Bf1 == 0) {
                                common.Bn1 = num;
                                System.out.println(common.Bn1);
                                common.Bf1 = 1;
                            } else {
                                System.out.println("既に入力されています。");
                            }
                            break;
                        case 2:
                            if (common.Bf2 == 0) {
                                common.Bn2 = num;
                                System.out.println(common.Bn2);
                                common.Bf2 = 1;
                            } else {
                                System.out.println("既に入力されています。");
                            }
                            break;
                        case 3:
                            if (common.Bf3 == 0) {
                                common.Bn3 = num;
                                System.out.println(common.Bn3);
                                common.Bf3 = 1;
                            } else {
                                System.out.println("既に入力されています。");
                            }
                            break;
                        default:
                            break;
                    }


                    Integer i = Integer.valueOf(num);
                    String str = i.toString();
                    break;
                case MotionEvent.ACTION_UP:
                    break;
            }
            //System.out.println("1ケタ目=" + n0);
            //System.out.println("2ケタ目=" + n1);
            //System.out.println("3ケタ目=" + n2);
            //System.out.println("4ケタ目=" + n3);
            invalidate();
            return true;
        }

        public int getPosition3() {
            return common.yv2;
        }

        public void setPosition3(int pos) {
            common.yv2 = pos;
        }
    }
    public class MyView4 extends View {
        Paint paint;
        Path path;

        float StrokeWidth1 = 20f;
        float StrokeWidth2 = 40f;
        float dp;

        public MyView4(Context context) {
            super(context);
            paint = new Paint();
            path = new Path();

            // スクリーンサイズからdipのようなものを作る
            DisplayMetrics metrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(metrics);

            dp = getResources().getDisplayMetrics().density;
            Log.d("debug", "fdp=" + dp);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            float x=0;
            float x1=getWidth();
            float x2=x1/2;
            float y3=getHeight();
            float y0=y3/4;
            float y1=y3/2;
            float y2=y0+y1;
            canvas.drawColor(Color.argb(255, 255, 255, 255));

            paint.setStyle(Paint.Style.FILL_AND_STROKE);
            paint.setStrokeWidth(5);
            paint.setTextSize(100);
            paint.setColor(Color.argb(255, 0, 0, 0));

            Integer i0 = Integer.valueOf(common.An0);
            String str0 = i0.toString();
            canvas.drawText("An1="+str0, x -0* dp, y0-150* dp, paint);
            Integer i1= Integer.valueOf(common.An1);
            String str1 = i1.toString();
            canvas.drawText("An2="+str1, x -0* dp, y1-150* dp, paint);
            Integer i2 = Integer.valueOf(common.An2);
            String str2 = i2.toString();
            canvas.drawText("An3="+str2, x -0* dp, y2-150* dp, paint);
            Integer i3 = Integer.valueOf(common.An3);
            String str3 = i3.toString();
            canvas.drawText("An4="+str3, x -0* dp, y3-150* dp, paint);

            Integer j0 = Integer.valueOf(common.Bn0);
            String str4 = j0.toString();
            canvas.drawText("Bn1="+str4, x2 -0* dp, y0-150* dp, paint);
            Integer j1= Integer.valueOf(common.Bn1);
            String str5 = j1.toString();
            canvas.drawText("Bn2="+str5, x2 -0* dp, y1-150* dp, paint);
            Integer j2 = Integer.valueOf(common.Bn2);
            String str6 = j2.toString();
            canvas.drawText("Bn3="+str6, x2 -0* dp, y2-150* dp, paint);
            Integer j3 = Integer.valueOf(common.Bn3);
            String str7 = j3.toString();
            canvas.drawText("Bn4="+str7, x2 -0* dp, y3-150* dp, paint);

            if((common.An0==common.Bn0)&&(common.An1==common.Bn1)&&(common.An2==common.Bn2)&&(common.An3==common.Bn3)){
                paint.setColor(Color.argb(255, 0, 0, 255));
                canvas.drawText("Accept!!",x-0*dp,y3-0*dp,paint);
            } else{
                paint.setColor(Color.argb(255, 255, 0, 0));
                canvas.drawText("Reject!!",x-0*dp,y3-0*dp,paint);
            }
        }

        public int getPosition4() {
            return common.yv2;
        }

        public void setPosition4(int pos) {
            common.yv2 = pos;
        }
    }
}