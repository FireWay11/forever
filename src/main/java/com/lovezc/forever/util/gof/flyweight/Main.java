package com.lovezc.forever.util.gof.flyweight;
public class Main {
	public static void main(String[] args) {
		/*
		 * Integer a=Integer.valueOf(3);//有缓存机制 Integer c=new Integer(3);//没有用到缓存机制
		 * Integer b=new Integer(3); Integer d=3;//有用到缓存机制 Integer e=129; Integer
		 * f=129;//超过了缓存池 System.out.println(a==d); System.out.println(e==d);
		 * System.out.println(c==b);
		 *
		 * String a0="a"; String a1="b"; String a2="a"+"b"; String b="ab"; String
		 * c="ab"; String a=a0+a1;
		 * System.out.println(a==b);//字符串相加其实是StringBuilder.append()然后toString
		 * System.out.println(a.intern()==b);//intern是找常量池中的引用
		 * System.out.println(b==c);//两个都指向常量池中
		 * System.out.println(a2==b);//如果是定义的时候直接相加，编译器会进行优化，直接看成String a2="ab";
		 */
		/*FlyWeight a=FlyWeightFactory.getINSTANCE().getConcreteFlyWeight('a',"outstate");
		FlyWeight b=FlyWeightFactory.getINSTANCE().getConcreteFlyWeight('a',"outstate");
		System.out.println(a==b);*/
	}
}
