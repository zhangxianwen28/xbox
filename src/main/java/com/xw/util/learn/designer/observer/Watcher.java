package com.xw.util.learn.designer.observer;

import java.util.Observable;
import java.util.Observer;

public class Watcher implements Observer {

    public void update(Observable observable ,Object ojb) {
        System.out.println("update () called ,counts is "+ojb.toString());
    }
}
