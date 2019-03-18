package ZTest;

import java.util.Collection;
import java.util.Collections;

public class test {
    public static void main(String[] args) {
        final ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
        threadLocal.set(100);
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+ " local: "+threadLocal.get());
            }
        });
        thread.start();
        System.out.println("Main local: "+threadLocal.get());
    }
}
