package com.xw.elastic.domain;

import java.util.concurrent.LinkedBlockingQueue;

public class StartLog {
    public static LinkedBlockingQueue<String> log = new LinkedBlockingQueue<>();
}
