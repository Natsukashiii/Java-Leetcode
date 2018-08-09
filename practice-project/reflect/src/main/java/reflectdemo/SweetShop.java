package reflectdemo;

class Candy
{
    static
    {
        System.out.println("Loading Candy");
    }
}

class Gum
{
    static
    {
        System.out.println("Loading Gum");
    }
}

class Cookie
{
    static
    {
        System.out.println("Loading Cookie");
    }
}

public class SweetShop
{
    public static void print(Object obj)
    {
        System.out.println(obj);
    }

    public static void main(String[] args)
    {
        print("inside main");
        new Candy();
        print("After creating Candy");
        try
        {
            Class.forName("reflectdemo.Gum");
        }
        catch (ClassNotFoundException e)
        {
            print("Couldn't find Gum");
        }
        print("After Class.forName(\"com.natsu.Gum\")");
        new Cookie();
        print("After creating Cookie");


        try
        {
            //ͨ��Class.forName��ȡGum���Class����
            Class clazz = Class.forName("reflectdemo.Gum");
            System.out.println("forName=clazz:" + clazz.getName());
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        //ͨ��ʵ�������ȡGum��Class����
        Gum gum = new Gum();
        Class clazz2 = gum.getClass();
        System.out.println("new=clazz2:" + clazz2.getName());

        //���泣���ķ�ʽ��ȡClass����
        Class clazz3 = Gum.class;
        System.out.println("new2= clazz3:" + clazz3.getName());
    }
}
