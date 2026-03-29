package com.basicsstrong.functional.section3;

public class LambdaUnderTheHood {
    public static void main(String[] args) {
//        MyFunInterface fun1 = new MyFunInterface() {
//            @Override
//            public void myMethod() {
//                System.out.println("Implementation1");
//            }
//        };
//
//        MyFunInterface fun2 = new MyFunInterface() {
//            @Override
//            public void myMethod() {
//                System.out.println("Implementation2");
//            }
//        };

        MyFunInterface fun = () -> System.out.println("I am light weight");
    }
}
