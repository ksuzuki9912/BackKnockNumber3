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


import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.charts.LineChart;//LineChartを利用できるようにする
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
//ファイル操作用
import android.content.Context;

import java.util.Arrays;//ソート用
import java.io.BufferedReader;
import java.io.File;//ファイルの存在を確認、削除するため
import java.io.FileInputStream;//ファイルを開く為の変数の宣言に必要
import java.io.FileOutputStream;//ファイルを作る為の変数の宣言に必要

import java.io.InputStreamReader;



//登録画面のクラス
public class MainActivity2 extends AppCompatActivity implements SensorEventListener/*View.OnClickListener*/{

    private Common common;
    private SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        common=(Common) getApplication();
        common.initA();
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main2);

        MyView myView = new MyView(this);
        MyView2 myView2 = new MyView2(this);
        setContentView(myView);

        int endPosition = 6870; //画面の最終到達位置
        TestAnimation testAnimation = new TestAnimation(myView, endPosition);
        testAnimation.setDuration(10000); //アニメーションの継続時間(ノーツの流れる速さはここで変える)
        //testAnimation.setRepeatMode(TestAnimation.REVERSE);
        testAnimation.setRepeatCount(3); //アニメーションの繰り返しを行う関数。n回繰り返す場合はn-1にする
        myView.startAnimation(testAnimation);


        testAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                System.out.println("アニメーション1が終了しました");
                setContentView(myView2);
                TestAnimation2 testAnimation2 = new TestAnimation2(myView2, 0);
                testAnimation2.setDuration(5000);
                myView2.startAnimation(testAnimation2);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public class MyView extends View {
        Paint paint;
        Path path;

        float dp;

        public MyView(Context context) {
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
            float yc4 = 241;

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
                canvas.drawCircle(xc - 0 * dp, common.yv1 - (num * xc) + 0 * dp, xc / 2, paint);
                paint.setStyle(Paint.Style.STROKE);
                paint.setColor(Color.argb(255, 0, 0, 0));
                canvas.drawCircle(xc - 0 * dp, common.yv1 - (num * xc) + 0 * dp, xc / 2, paint);


            }
            for (int num = 0; num < 10; num++) {
                paint.setStyle(Paint.Style.FILL_AND_STROKE);
                paint.setStrokeWidth(5);
                paint.setTextSize(200);
                paint.setColor(Color.argb(255, 0, 0, 0));

                Integer i = Integer.valueOf(num);
                String str = i.toString();
                canvas.drawText(str, xc - 20 * dp, common.yv1 - (num * xc) + 20 * dp, paint);
            }
            float a = (common.yv1 - 970) / xc;
            int ia = (int) a;
            //if (ia >= 0 && ia < 10) {
                //System.out.println(ia);
            //}
            //System.out.println((yv));
            if (common.yv1 == 6870) {
                //System.out.println(count);
                common.count1++;
            }
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    //System.out.println(count);
                    //System.out.println(yv);
                    int xc = 540;
                    int num = (common.yv1 - 970) / xc;
                    switch (common.count1) {
                        case 0:
                            if (common.Af0 == 0) {
                                common.An0 = num;
                                System.out.println(common.An0);
                                common.Af0 = 1;
                            } else {
                                System.out.println("既に入力されています。");
                            }
                            break;
                        case 1:
                            if (common.Af1 == 0) {
                                common.An1 = num;
                                System.out.println(common.An1);
                                common.Af1 = 1;
                            } else {
                                System.out.println("既に入力されています。");
                            }
                            break;
                        case 2:
                            if (common.Af2 == 0) {
                                common.An2 = num;
                                System.out.println(common.An2);
                                common.Af2 = 1;
                            } else {
                                System.out.println("既に入力されています。");
                            }
                            break;
                        case 3:
                            if (common.Af3 == 0) {
                                common.An3 = num;
                                System.out.println(common.An3);
                                common.Af3 = 1;
                            } else {
                                System.out.println("既に入力されています。");
                            }
                            break;
                        default:
                            break;
                    }


                    Integer i = Integer.valueOf(num);
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

        public int getPosition() {
            return common.yv1;
        }

        public void setPosition(int pos) {
            common.yv1 = pos;
        }
    }

    public class MyView2 extends View {
        Paint paint;
        Path path;

        float dp;

        public MyView2(Context context) {
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
            canvas.drawText("Number1="+str0, x -0* dp, y0-150* dp, paint);
            Integer i1= Integer.valueOf(common.An1);
            String str1 = i1.toString();
            canvas.drawText("Number2="+str1, x -0* dp, y1-150* dp, paint);
            Integer i2 = Integer.valueOf(common.An2);
            String str2 = i2.toString();
            canvas.drawText("Number3="+str2, x -0* dp, y2-150* dp, paint);
            Integer i3 = Integer.valueOf(common.An3);
            String str3 = i3.toString();
            canvas.drawText("Number4="+str3, x -0* dp, y3-150* dp, paint);
        }

        public int getPosition2() {
            return common.yv1;
        }

        public void setPosition2(int pos) {
            common.yv1 = pos;
        }


    }
    @Override
    public void onSensorChanged(SensorEvent event) {

    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Listenerの登録
        Sensor accel = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION/*TYPE_ACCELEROMETER*/);//加速度センサ-9.8用

        ////加速度センサ-9.8用

        sensorManager.registerListener(this, accel, SensorManager.SENSOR_DELAY_FASTEST);//最速通知頻度
    }


    //解除するコード
    @Override
    protected void onPause() {
        super.onPause();
        //Listenerを解除
        sensorManager.unregisterListener(this);
    }
}