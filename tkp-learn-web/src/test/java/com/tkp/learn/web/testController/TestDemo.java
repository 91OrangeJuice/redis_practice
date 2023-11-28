package com.tkp.learn.web.testController;

import java.nio.ByteBuffer;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: itw_wangsc01
 * @createDate: 2020/7/12
 * @version: 1.0
 */
public class TestDemo {

    public static void main(String[] args) {
        ByteBuffer bb=ByteBuffer.allocateDirect(1024*1024*128);
        try {
            TimeUnit.SECONDS.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("isOk");
    }

}
