package ru.java.basic.otus.homework;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class javaHomework {
    public static void main(String[] args) throws InterruptedException {

        long start1 = System.currentTimeMillis();
        double [] array1 = new double[100000000];
        for (int i = 0; i<100000000; i++) {
            array1[i] = 1.14 * Math.cos(i) * Math.sin(i * 0.2) * Math.cos(i / 1.2);
        }
        long finish1 = System.currentTimeMillis();
        System.out.println(finish1-start1 + " мс. без многопоточности");

        long start2 = System.currentTimeMillis();
        CountDownLatch latch = new CountDownLatch(4);
        double [] array2 = new double[100000000];
        Thread t1 = new Thread(() -> {
            for (int i = 0; i<25000000; i++) {
                array2[i] = 1.14 * Math.cos(i) * Math.sin(i * 0.2) * Math.cos(i / 1.2);
            }
            latch.countDown();
        });
        Thread t2 = new Thread(() -> {
            for (int i = 25000000; i<50000000; i++) {
                array2[i] = 1.14 * Math.cos(i) * Math.sin(i * 0.2) * Math.cos(i / 1.2);
            }
            latch.countDown();
        });
        Thread t3= new Thread(() -> {
            for (int i = 50000000; i<75000000; i++) {
                array2[i] = 1.14 * Math.cos(i) * Math.sin(i * 0.2) * Math.cos(i / 1.2);
            }
            latch.countDown();
        });
        Thread t4= new Thread(() -> {
            for (int i = 75000000; i<100000000; i++) {
                array2[i] = 1.14 * Math.cos(i) * Math.sin(i * 0.2) * Math.cos(i / 1.2);
            }
            latch.countDown();
        });
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        latch.await();
        long finish2 = System.currentTimeMillis();
        System.out.println(finish2-start2 + " мс. с многопоточностью");
    }
}
