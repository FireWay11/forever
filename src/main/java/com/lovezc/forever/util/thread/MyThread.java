package com.lovezc.forever.util.thread;


public class MyThread extends Thread {
    @Override
    public void run() {
        while (true) {
            System.out.println(this.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        MyThread thread = new MyThread();
        thread.start(); //线程启动的正确方式
    }

}


