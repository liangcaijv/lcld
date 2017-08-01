package org.lanqiao.concurrent.cases;

import java.text.SimpleDateFormat;

public class ThreadLocalDemo {
    private static ThreadLocal<SimpleDateFormat> localSimpleDateFormatter = new ThreadLocal<>();
    static{
        localSimpleDateFormatter.set(new SimpleDateFormat("yyyy-MM-dd"));
    }
}
