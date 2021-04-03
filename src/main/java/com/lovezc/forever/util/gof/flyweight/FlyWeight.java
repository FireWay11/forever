package com.lovezc.forever.util.gof.flyweight;

public abstract class FlyWeight {
	/**
	 * 内部状态
	 */
    String instate;
	/**
	 * 外部状态
	 */
    String outstate;

    public FlyWeight(String outstate) {
        this.instate = outstate;
    }

	/**
	 * 与外部状态相关的逻辑操作
	 */
    abstract void operation();

	/**
	 * 获取或者设置内部状态
	 */
    public String getInstate() {
        return instate;
    }

    public void setInstate(String instate) {
        this.instate = instate;
    }

}