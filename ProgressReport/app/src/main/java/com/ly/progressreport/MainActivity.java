package com.ly.progressreport;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements WaveProgressView.OnAnimationListener {
    WaveProgressView wave;
    TextView text;
    SeekBar seek;
    SeekBar seek_1;
    SeekBar seek_2;
    SeekBar seek_3;
    MySeekBar seekbar_my_4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWave();
        initSeekbar();
        changeColorSeekBar();
        initSeekbar3();
        initSeekbar4();
    }

    private void initSeekbar4() {
        seekbar_my_4 = findViewById(R.id.seekbar_my_4);
        ThreadSleep.mthreadrun(seekbar_my_4);
        seekbar_my_4.setOnSeekBarChangeListener(new MySeekBar.SetOnSeekBarChangeListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onProgressChanged(int position) {
                Toast.makeText(MainActivity.this,"position = "+position,500).show();
            }
        });
    }

    private void initSeekbar3() {
        seek_3 = findViewById(R.id.seekbar_my_3);
        seek_3.getThumb().setColorFilter(Color.RED, PorterDuff.Mode.SRC);
        ThreadSleep.threadrun(seek_3);
    }

    private void changeColorSeekBar() {
        seek_1 = findViewById(R.id.seekbar_my_1);
        seek_2 = findViewById(R.id.seekbar_my_2);

        seek_1.getProgressDrawable().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);//(前)设置进度条颜色、样式//滑条的颜色
        seek_1.getThumb().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP); //圆圈的颜色

        seek_2.getProgressDrawable().setColorFilter(Color.YELLOW, PorterDuff.Mode.DST_ATOP);//(后)设置进度条颜色、样式//滑条的颜色

        ThreadSleep.threadrun(seek_1);
        ThreadSleep.threadrun(seek_2);

        //两个控件叠加同步
        seek_1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(b){
                   seek_2.setProgress(i);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void initSeekbar() {
        seek = findViewById(R.id.seekbar_android);
        ThreadSleep.threadrun(seek);
    }


    private void initWave() {
        wave = findViewById(R.id.wave);
        text = findViewById(R.id.text);
        wave.setTextView(text);
//        wave.setDrawSecondWave(true);
        wave.setProgressNum(100, 5000);
        wave.setOnAnimationListener(this);
    }

    @Override
    public String howToChangeText(float interpolatedTime, float updateNum, float maxNum) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        String s = decimalFormat.format(interpolatedTime * updateNum / maxNum * 100) + "%";
        return s;
    }

    @Override
    public float howToChangeWaveHeight(float percent, float waveHeight) {
//        Log.d("lylog","percent = "+percent +"  waveHeight"+waveHeight);
        return (1 - percent) * waveHeight;
    }
}
