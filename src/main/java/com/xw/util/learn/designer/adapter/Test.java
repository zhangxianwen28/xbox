package com.xw.util.learn.designer.adapter;

public class Test {
    public static void main(String[] args) {
        Target target = new Adapter(new Adaptee());
        target.adapteeMethod();
        target.adapterMethod();
    }
}
