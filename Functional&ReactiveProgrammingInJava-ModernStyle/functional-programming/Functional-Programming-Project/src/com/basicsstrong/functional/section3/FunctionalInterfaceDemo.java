package com.basicsstrong.functional.section3;

public class FunctionalInterfaceDemo {

    public static void main(String[] args) {
//        MyFunInterface fun = () -> System.out.println("TEst functional interface");
//        fun.myMethod();

        onTheFly(() -> System.out.println("Hello"));
    }

    public static void onTheFly(MyFunInterface fun) {
        fun.myMethod();
    }
}
