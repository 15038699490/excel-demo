package com.yzw.exceldemo.excel;

import java.util.List;

/**
 * @author yzw
 * 使用一个线程对当前的数据进行一个存储
 */
public class ObjectList {

    private static ThreadLocal currentUser = new ThreadLocal();

    public static void setCurrentFlag(List t) {
        currentUser.set(t);
    }

    public static List getCurrentFlag() {
        return (List) currentUser.get();
    }

    public static void removeCurrentFlag() {
        currentUser.remove();
    }
}
