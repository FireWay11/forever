package com.lovezc.forever.util.gof.flyweight;

public class A extends FlyWeight {

    public A(String outstate) {
        super(outstate);
        // TODO Auto-generated constructor stub
    }

    public A() {
        super(null);
    }

    //根据外部状态进行一系列的逻辑操作
    @Override
    void operation() {
        // TODO Auto-generated method stub
        System.out.println(outstate);
    }


}
