package semaphoredemo;

import java.util.concurrent.Semaphore;

/**
 * 可以控制同时访问的线程个数
 * 当调用acquire()方法时，会消费一个许可证。如果没有许可证了，会阻塞起来
 * 当调用release()方法时，会添加一个许可证。
 */
public class semaphoreDemo
{
    public static void main(String[] args)
    {
        //听说障障发猫粮，来了好多只喵
        int number = 20;

        final Semaphore semaphore = new Semaphore(5);
        for (int i = 0; i < number; i++)
        {
            final int finalI = i;
            new Thread(new Runnable()
            {
                public void run()
                {
                    try
                    {
                        // 障障觉得帅的才能来吃猫粮
                        // 障障觉得都很帅，杯杯很生气，表示只能选择5只喵
                        semaphore.acquire();
                        System.out.println("猫猫" + finalI + "来吃猫粮了");

                        // 吃猫粮吃猫粮！
                        Thread.sleep(1000);

                        // 吃完被杯杯打跑了！哦豁
                        System.out.println("猫猫" + finalI + "吃完跑了QAQ...");
                        semaphore.release();
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }).start();
        }


    }
}
