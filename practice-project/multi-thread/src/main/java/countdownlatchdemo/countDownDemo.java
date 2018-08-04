package countdownlatchdemo;

import java.util.concurrent.CountDownLatch;

/**
 * count初始化CountDownLatch，然后需要等待的线程调用await方法。
 * await方法会一直受阻塞直到count=0。
 * 而其它线程完成自己的操作后，调用countDown()使计数器count减1。当count减到0时，所有在等待的线程均会被释放
 */
public class countDownDemo
{
    public static void main(String[] args)
    {
        final CountDownLatch countDownLatch = new CountDownLatch(10);
        System.out.println("先数10个数.......");

        //启动等待线程，等到count为0后才会触发
        new Thread(new Runnable()
        {
            public void run()
            {
                try
                {
                    countDownLatch.await();
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                System.out.println("我到了...");
            }
        }).start();

        //启动其他线程
        for (int i = 0; i < 10; i++)
        {
            final int finalI = i;
            new Thread(new Runnable()
            {
                public void run()
                {
                    System.out.println("第一个数：" + finalI);
                    countDownLatch.countDown();
                    System.out.println("现在的count数量：" + countDownLatch.getCount());
                }
            }).start();
        }
    }
}
