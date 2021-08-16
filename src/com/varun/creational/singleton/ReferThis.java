package com.varun.creational.singleton;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

class Driver {
    public static void main(String[] args) {
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 20000; i++) {
            threads.add(new Thread(() -> {
                Singleton2.getInstance();
            }));
        }
        threads.forEach((thread) -> thread.setDaemon(true));
        Instant start = Instant.now();
        threads.forEach(Thread::start);
        threads.forEach((thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }));

        Instant end = Instant.now();

        System.out.println("Threads execution time: " + Duration.between(start, end).toMillis());
    }
}

//fails in multi-threaded env
class Singleton1 {

    private static Singleton1 instance;

    private Singleton1() {
        System.out.println("Singleton class constructor called");
    }

    public static Singleton1 getInstance() {
        if (instance == null) {
            instance = new Singleton1();
        }
        return instance;
    }
}

//succeeds in multi-threaded env but is very slow
class Singleton2 {

    private static Singleton2 instance;

    private Singleton2() {
        System.out.println("Singleton class constructor called");
    }

    public static synchronized Singleton2 getInstance() {
        if (instance == null) {
            instance = new Singleton2();
        }
        return instance;
    }
}

//thread safe and faster, but complex to understand
class Singleton3 {

    private static volatile Singleton3 instance;

    private Singleton3() {
        System.out.println("Singleton class constructor called");
    }

    public static Singleton3 getInstance() {
        if (instance == null) {
            synchronized (Singleton3.class) {
                if (instance == null) {
                    instance = new Singleton3();
                }
            }
        }
        return instance;
    }
}

//thread safe, since the instance is created during class initialization
//this is done when class is loaded by class loader,
//we dont have threads execution at the time of class loading.
class Singleton4 {

    private static final Singleton4 INSTANCE = new Singleton4();

    private Singleton4() {
        System.out.println("Singleton class constructor called");
    }

    public static Singleton4 getInstance() {
        return INSTANCE;
    }
}
