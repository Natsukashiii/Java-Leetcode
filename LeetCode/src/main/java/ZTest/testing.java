package ZTest;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

public class testing
{
    public static void main(String[] args)
    {
        test2();
    }

    public static String test2()
    {
        String result = "";

        try
        {
            Process process = Runtime.getRuntime().exec("python /tmp/test.py ");
//            process.waitFor();
            InputStreamReader ir = new InputStreamReader(process.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
            result = input.readLine();
            System.out.println(result);
            input.close();
            ir.close();
//            process.waitFor();
        }
        catch (IOException e)
        {
            System.out.println("����python�ű�����ȡ���ʱ����" + e.getMessage());
        }
        return result;
    }

    public static void test1()
    {
        int count = 66;
        foo(count);
        System.out.println(count);
    }

    public static void foo(int count)
    {
        count = 666;
    }
}
