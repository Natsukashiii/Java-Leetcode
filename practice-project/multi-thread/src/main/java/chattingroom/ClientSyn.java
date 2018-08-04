package chattingroom;

import chattingroom.util.Constants;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientSyn
{

    private Socket socket;

    public ClientSyn()
    {
        try
        {
            socket = new Socket(Constants.IP, Constants.PORT);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private class ServerHandler implements Runnable
    {
        public void run()
        {
            // TODO Auto-generated method stub
            try
            {
                InputStream is = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is, "UTF-8");
                BufferedReader br = new BufferedReader(isr);
                while (true)
                {
                    System.out.println(br.readLine());
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public void start()
    {

        try
        {
            ServerHandler handler = new ServerHandler();
            Thread t = new Thread(handler);
            t.setDaemon(true);
            t.start();

            OutputStream out = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(out, "UTF-8");
            PrintWriter pw = new PrintWriter(osw, true);
            //创建Scanner读取用户输入内容
            Scanner scanner = new Scanner(System.in);
            while (true)
            {
                pw.println(scanner.nextLine());
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
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

    public static void main(String[] args)
    {
        // TODO Auto-generated method stub
        ClientSyn clientSyn = new ClientSyn();
        clientSyn.start();
    }

}
