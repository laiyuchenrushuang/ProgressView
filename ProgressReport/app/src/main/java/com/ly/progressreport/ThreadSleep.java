package com.ly.progressreport;

import android.widget.SeekBar;

/**
 * Created by ly on 2019/6/28 13:17
 * <p>
 * Copyright is owned by chengdu haicheng technology
 * co., LTD. The code is only for learning and sharing.
 * It is forbidden to make profits by spreading the code.
 */
public class ThreadSleep {
    public static void threadsleep(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void threadrun(final SeekBar seek) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int count = 0;
                while (count <= 100) {
                    count++;
                    seek.setProgress(count);
                    threadsleep(50);//注意 seekbar子线程也可以使用
                }
            }
        }).start();
    }

    public static void mthreadrun(final MySeekBar seek) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int count = 0;
                while (count <= 100) {
                    seek.setProgress(count);
                    count += 1;
                    threadsleep(50);//注意 seekbar子线程也可以使用
                }
            }
        }).start();
    }
}
