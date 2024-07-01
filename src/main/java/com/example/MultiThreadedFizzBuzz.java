package com.example;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class MultiThreadedFizzBuzz {
    private final int n;
    private final BlockingQueue<String> queue = new LinkedBlockingQueue<>();
    private final AtomicInteger counter = new AtomicInteger(1);

    public MultiThreadedFizzBuzz(int n) {
        this.n = n;
    }

    public void fizz() throws InterruptedException {
        while (true) {
            int i = counter.get();
            if (i > n) break;
            if (i % 3 == 0 && i % 5 != 0) {
                queue.put("fizz");
                counter.incrementAndGet();
            }
            Thread.yield();
        }
    }

    public void buzz() throws InterruptedException {
        while (true) {
            int i = counter.get();
            if (i > n) break;
            if (i % 5 == 0 && i % 3 != 0) {
                queue.put("buzz");
                counter.incrementAndGet();
            }
            Thread.yield();
        }
    }

    public void fizzbuzz() throws InterruptedException {
        while (true) {
            int i = counter.get();
            if (i > n) break;
            if (i % 3 == 0 && i % 5 == 0) {
                queue.put("fizzbuzz");
                counter.incrementAndGet();
            }
            Thread.yield();
        }
    }

    public void number() throws InterruptedException {
        while (true) {
            int i = counter.get();
            if (i > n) break;
            if (i % 3 != 0 && i % 5 != 0) {
                queue.put(String.valueOf(i));
                counter.incrementAndGet();
            }
            Thread.yield();
        }
    }

    public void print() throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            System.out.println(queue.take());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int n = 15;
        MultiThreadedFizzBuzz fizzBuzz = new MultiThreadedFizzBuzz(n);

        Thread threadA = new Thread(() -> {
            try {
                fizzBuzz.fizz();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        Thread threadB = new Thread(() -> {
            try {
                fizzBuzz.buzz();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        Thread threadC = new Thread(() -> {
            try {
                fizzBuzz.fizzbuzz();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        Thread threadD = new Thread(() -> {
            try {
                fizzBuzz.number();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        threadA.start();
        threadB.start();
        threadC.start();
        threadD.start();

        threadA.join();
        threadB.join();
        threadC.join();
        threadD.join();

        fizzBuzz.print();
    }
}