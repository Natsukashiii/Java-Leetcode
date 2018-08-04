package chattingroom;

import chattingroom.util.Constants;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server
{
    //声明服务器
    private ServerSocket serverSocket;
    //声明客户端
    private static Socket socket;

    private String serverName;

    public Server()
    {
        try
        {
            init();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    /**
     * 创建服务器，并开始监听
     */
    private void init() throws IOException
    {
        serverSocket = new ServerSocket(Constants.PORT);
        System.out.println("-----服务器已开启-----");
        System.out.println("请输入服务器的名字：");
        Scanner scanner = new Scanner(System.in);
        serverName = scanner.next();
        while (true)
        {
            socket = serverSocket.accept();
            handle(socket);
        }
    }

    private void handle(Socket socket)
    {
        String key = socket.getInetAddress().getHostAddress() + ":" + socket.getPort();
        System.out.println("监听到的服务端为：" + key);
        Thread thread = new Thread(new ServerThreadSocket(socket));
        thread.setName(serverName);
        thread.start();
    }


    public static void main(String[] args)
    {
        Server server = new Server();
    }
}
