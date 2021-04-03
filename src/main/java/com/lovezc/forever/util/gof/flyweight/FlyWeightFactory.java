package com.lovezc.forever.util.gof.flyweight;

import java.util.HashMap;
import java.util.Map;

//泛型时，不能用单例模式，否则泛型将会没有意义
public class FlyWeightFactory {
    private FlyWeightFactory() {
    }

    ;
    // volatile是避免重排序
    private static volatile FlyWeightFactory INSTANCE = null;

    public static FlyWeightFactory getINSTANCE() {
        if (INSTANCE == null) {
            synchronized (FlyWeightFactory.class) {
                if (INSTANCE == null)
                    INSTANCE = new FlyWeightFactory();
            }
        }
        return INSTANCE;
    }

    static Map<Character, FlyWeight> FlyWeightMap = new HashMap<Character, FlyWeight>();

    public FlyWeight getConcreteFlyWeight(char c) {
        if (!FlyWeightMap.containsKey(c)) {
			FlyWeightMap.put(c, new A());
		}
        return FlyWeightMap.get(c);
    }
}
