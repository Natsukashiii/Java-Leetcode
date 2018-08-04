package chattingroom;

import chattingroom.util.Constants;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerSyn
{
    //声明服务器
    private ServerSocket serverSocket;
    //声明客户端
    private static Socket socket;
    //线程池
    private ExecutorService threadPool;
    //所有客户端输出流
    private List<PrintWriter> allOut;

    private String serverName;

    public ServerSyn()
    {
        try
        {
            serverSocket = new ServerSocket(Constants.PORT);
            allOut = new ArrayList<PrintWriter>();
            threadPool = Executors.newFixedThreadPool(Constants.POOL_NUMBER);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 将输出流存入共享集合，与下面两个方法互斥，保证同步安全
     */
    private synchronized void addOut(PrintWriter out)
    {
        allOut.add(out);
    }

    private synchronized void removeOut(PrintWriter out)
    {
        allOut.remove(out);
    }

    private synchronized void sendMessage(String message)
    {
        for (PrintWriter o : allOut)
        {
            o.println(message);
        }
    }

    /**
     * 并发处理客户端的交互
     */
    class ClientHandler implements Runnable
    {
        private Socket socket;

        public ClientHandler(Socket socket)
        {
            this.socket = socket;
        }

        public void run()
        {
            PrintWriter printWriter = null;
            try
            {
                OutputStream outputStream = socket.getOutputStream();
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
                printWriter = new PrintWriter(outputStreamWriter, true);
                //存入共享集合
                //allOut.add(pw);
                addOut(printWriter);
                InputStream is = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is, "UTF-8");
                BufferedReader br = new BufferedReader(isr);
                String message = null;
                while ((message = br.readLine()) != null)
                {
                    //for(PrintWriter o: allOut){
                    //    o.println(message);
                    sendMessage(message);

                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                //当客户端断线时，要将输出流从集合中删除
                //allOut.remove(pw);
                removeOut(printWriter);
                if (socket != null)
                {
                    try
                    {
                        socket.close();
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void start()
    {
        try
        {
            //循环监听客户端的连接
            while (true)
            {

                System.out.println("等待客户端连接--------");
                Socket socket = serverSocket.accept();
                System.out.println("客户端已连接！");

                ClientHandler handler = new ClientHandler(socket);
                //启动一个线程来完成针对该客户端的交互
                threadPool.execute(handler);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        ServerSyn serverSyn = new ServerSyn();;
        serverSyn.start();
    }
}
