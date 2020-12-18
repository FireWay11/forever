package com.lovezc.forever.util.stream;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        List<String> collect = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        List<String> collect1 = collect.stream().map(item -> item + item).collect(Collectors.toList());
        collect1.forEach(item -> System.out.println(item));


        System.out.println(collect1.stream().limit(2).count());

        System.out.println("=================================");

        List<Integer> strs = Arrays.asList(1,2,3,4,5,6,7,8,9);
        // 获取空字符串的数量
        strs.parallelStream().forEach(i -> System.out.println(i + "---" + Thread.currentThread().getName()));

    }

}
