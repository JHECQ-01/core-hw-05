package com.example;

public class ElapsedTimeDisplay {
    public static void main(String[] args) {
        Thread timeThread = new Thread(new Runnable() {
            public void run() {
                int secondsPassed = 0;
                while (true) {
                    System.out.println("Elapsed time: " + secondsPassed + " seconds");
                    secondsPassed++;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread messageThread = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(5000);
                        System.out.println("been five seconds");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        timeThread.start();
        messageThread.start();
    }
}