package countdownlatchdemo;

import java.util.concurrent.CountDownLatch;

public class countDownDemo1
{
    public static void main(String[] args)
    {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        System.out.println("你们等我到了再来...");

        new Thread(new Runnable()
        {
            public void run()
            {
                try
                {
                    Thread.sleep(5);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                System.out.println("我到了!");
                countDownLatch.countDown();
            }
        }).start();

        for (int i = 0; i < 5; i++)
        {
            final int finalI = i;
            new Thread(new Runnable()
            {
                public void run()
                {
                    System.out.println("我们还在等你到!");
                    try
                    {
                        countDownLatch.await();
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    System.out.println("你终于到了," + finalI + "号也到了! ");
                }
            }).start();
        }
    }
}
