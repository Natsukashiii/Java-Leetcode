package chattingroom.threadaction;

import java.io.IOException;
import java.io.InputStream;


/**
 * 读取输入流线程
 */
public class ThreadReader implements Runnable
{
    //传输最大字节长度
    private static int HEAD_SIZE=5;
    //每次读取10个字节
    private static int BUFFER_SIZE=10;
    private InputStream is;

    public ThreadReader(InputStream is)
    {
        this.is = is;
    }

    public void run()
    {

        try
        {
            while (true)
            {
                byte[] b = new byte[1024];
                int length = is.read(b);
                String message = new String(b, 0, length);
                System.out.println(Thread.currentThread().getName() + ":" + message);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

}
