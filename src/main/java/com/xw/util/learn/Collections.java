package com.xw.util.learn;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Collections {

    public void collections() {
        // Map
        Map<String, String> hashMap = new HashMap<>();
        Map<String, String> linkedHashMap = new LinkedHashMap<>();
        Map<String, String> hashtable = new Hashtable<>(); //线程安全（实现方式synchronized）
        Map<String, String> treeMap = new TreeMap<>();//默认升序排列
        Map<String, String> identityHashMap = new IdentityHashMap<>();
        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>();
        ConcurrentSkipListMap<String, String> concurrentSkipListMap = new ConcurrentSkipListMap<>();// 线程安全有序的哈希表
        BiMap<String, String> biMap = HashBiMap.create();

        // List
        List<String> arrayList = new ArrayList<>();
        List<String> linkedList = new LinkedList<>();//比arrayList更占用内存
        CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList<>();//jdk1.5 对比vector性能更好
        Vector<String> vector = new Vector<>(); //jdk1.0 最早期的集合线程安全（实现方式synchronized）
        Stack<String> stack = new Stack<>();//先进后出

        // Set
        Set<String> hashSet = new HashSet<>();
        Set<String> linkedHashSet = new LinkedHashSet<>();
        Set<String> treeSet = new TreeSet<>();

        // Queue
        Queue<String> queue = new ArrayDeque<>();
        PriorityQueue<String> priorityQueue = new PriorityQueue<>();
        ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(1);
        LinkedBlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue<>();
        LinkedBlockingDeque<String> linkedBlockingDeque = new LinkedBlockingDeque<>();
        LinkedTransferQueue<String> linkedTransferQueue = new LinkedTransferQueue<>();
        PriorityBlockingQueue<String> priorityBlockingQueue = new PriorityBlockingQueue<>();
        SynchronousQueue<String> synchronousQueue = new SynchronousQueue<>();
        ConcurrentLinkedDeque<String> concurrentLinkedDeque = new ConcurrentLinkedDeque<>();
    }


    public void locks() {
        ReentrantLock lock = new ReentrantLock();
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    }

    public void threads() {
        //推荐使用
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("-%d").build();
        ExecutorService taskExe = new ThreadPoolExecutor(10, 19, 200L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), threadFactory);

        ExecutorService cachedThreadPool1 = Executors.newCachedThreadPool();
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
        ExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        ExecutorService singleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();

    }
}
