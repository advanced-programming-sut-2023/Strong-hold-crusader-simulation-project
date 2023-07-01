package org.example;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                throw new RuntimeException(new Exception());
            }
        });
        thread.setName("MYTHREAD");
        thread.start();
        while (true) {
            System.out.println("fuck");
            Thread.sleep(1000);
        }
    }
}