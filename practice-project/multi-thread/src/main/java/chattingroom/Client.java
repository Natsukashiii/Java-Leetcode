package chattingroom;

import chattingroom.threadaction.ThreadReader;
import chattingroom.threadaction.ThreadWriter;
import chattingroom.util.Constants;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client
{
    private static Socket socket;
    private String clientName;

    public Client()
    {
        try
        {
            init();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void init() throws IOException
    {
        System.out.println("-----客户端已开启-----");
        System.out.println("请输入客户端名称：");
        Scanner scanner = new Scanner(System.in);
        clientName = scanner.next();
        socket = new Socket(Constants.IP, Constants.PORT);
    }

    private void handle() throws Exception
    {
        Thread threadReader = new Thread(new ThreadReader(socket.getInputStream()), Thread.currentThread().getName());
        Thread threadWriter = new Thread(new ThreadWriter(socket.getOutputStream()));
        threadWriter.setName(clientName);
        threadReader.start();
        threadWriter.start();
    }

    public static void main(String[] args) throws Exception
    {
        Client client = new Client();
        client.handle();
    }
}
