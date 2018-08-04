package chattingroom;

import chattingroom.threadaction.ThreadReader;
import chattingroom.threadaction.ThreadWriter;

import java.io.IOException;
import java.net.Socket;


/**
 * 服务器处理Socket线程
 */
public class ServerThreadSocket implements Runnable
{
    private Socket socket;

    public ServerThreadSocket(Socket socket)
    {
        this.socket = socket;
    }

    public void run()
    {
        try
        {
            Thread threadReader = new Thread(new ThreadReader(socket.getInputStream()));
            Thread threadWriter = new Thread(new ThreadWriter(socket.getOutputStream()));
            threadReader.start();
            threadWriter.start();

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
