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
            //通过Class.forName获取Gum类的Class对象
            Class clazz = Class.forName("reflectdemo.Gum");
            System.out.println("forName=clazz:" + clazz.getName());
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        //通过实例对象获取Gum的Class对象
        Gum gum = new Gum();
        Class clazz2 = gum.getClass();
        System.out.println("new=clazz2:" + clazz2.getName());

        //字面常量的方式获取Class对象
        Class clazz3 = Gum.class;
        System.out.println("new2= clazz3:" + clazz3.getName());
    }
}
