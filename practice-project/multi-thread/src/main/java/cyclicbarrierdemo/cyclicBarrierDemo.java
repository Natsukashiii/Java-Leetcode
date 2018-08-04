package cyclicbarrierdemo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier允许一组线程互相等待，直到到达某个公共屏障点。
 * 叫做cyclic是因为当所有等待线程都被释放以后，
 * CyclicBarrier可以被重用(对比于CountDownLatch是不能重用的)
 */
public class cyclicBarrierDemo
{
    public static void main(String[] args)
    {
        final CyclicBarrier CyclicBarrier = new CyclicBarrier(2);
        for (int i = 0; i < 2; i++)
        {
            new Thread(new Runnable()
            {
                public void run()
                {
                    String currentThreadName = Thread.currentThread().getName();
                    if (("Thread-0").equals(currentThreadName))
                    {
                        currentThreadName = "障障";
                    }
                    else
                    {
                        currentThreadName = "杯杯";
                    }
                    System.out.println("I am: " + currentThreadName);
                    try
                    {
                        // 两只喵来了
                        CyclicBarrier.await();
                        System.out.println(currentThreadName + "来了");

                        // 打一架
                        CyclicBarrier.await();
                        System.out.println(currentThreadName + "打架");

                        // 打完架吃猫粮
                        CyclicBarrier.await();
                        System.out.println("吃猫粮！");

                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    catch (BrokenBarrierException e)
                    {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
