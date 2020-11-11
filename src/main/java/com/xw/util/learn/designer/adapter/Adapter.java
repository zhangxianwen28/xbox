package com.xw.util.learn.designer.adapter;

public class Adapter implements  Target{
    //引入被适配者
    private Adaptee adaptee;

    public Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void adapteeMethod() {
        adaptee.adapteeMethod();
    }

    @Override
    public void adapterMethod() {
        System.out.println("适配器方法 !");
    }



}
