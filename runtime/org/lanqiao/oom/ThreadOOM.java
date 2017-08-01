package org.lanqiao.oom;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程太多导致的内存溢出
 */
public class ThreadOOM {

    public static void main(String[] args) {
        for (;;) {
            new Thread(() -> {
                while (true){}
            }).start();
        }

    }

}
