import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


/**
 * @author natsukashii
 */
public class ThreadReader implements Runnable
{

    //private static int HEAD_SIZE=5;//传输最大字节长度
    //private static int BUFFER_SIZE=10;//每次读取10个字节
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
