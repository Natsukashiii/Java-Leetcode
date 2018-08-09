package reflectdemo;

import java.util.Random;

class Initable
{
    //�����ھ�̬����
    static final int staticFinal = 47;
    //�Ǳ��ھ�̬����
    static final int staticFinal2 = ClassInitialization.rand.nextInt(1000);

    static
    {
        System.out.println("Initializing Initable");
    }
}

class Initable2
{
    //��̬��Ա����
    static int staticNonFinal = 147;

    static
    {
        System.out.println("Initializing Initable2");
    }
}

class Initable3
{
    //��̬��Ա����
    static int staticNonFinal = 74;

    static
    {
        System.out.println("Initializing Initable3");
    }
}


public class ClassInitialization
{
    public static Random rand = new Random(47);

    public static void main(String[] args) throws Exception
    {
        //���泣����ȡ��ʽ��ȡClass����
        Class initable = Initable.class;
        System.out.println("After creating Initable ref");
        //���������ʼ��
        System.out.println(Initable.staticFinal);
        //�ᴥ�����ʼ��
        System.out.println(Initable.staticFinal2);
        //�ᴥ�����ʼ��
        System.out.println(Initable2.staticNonFinal);
        //forName������ȡClass����
        Class initable3 = Class.forName("reflectdemo.Initable3");
        System.out.println("After creating Initable3 ref");
        System.out.println(Initable3.staticNonFinal);
    }
}
