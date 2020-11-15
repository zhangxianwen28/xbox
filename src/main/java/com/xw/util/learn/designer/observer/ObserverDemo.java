package com.xw.util.learn.designer.observer;

public class ObserverDemo {
    public static void main(String[] args) {
        BeingWatched beingWatched = new BeingWatched();
        Watcher watcher = new Watcher();
        beingWatched.addObserver(watcher);
        beingWatched.counter(10);

    }
}
