package reflectdemo;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Type;

/**
 * Created by zejian on 2017/5/1.
 * Blog : http://blog.csdn.net/javazejian [ԭ�ĵ�ַ,������ԭ��]
 */
public class ReflectDemo implements Serializable{
    public static void main(String[] args) throws Exception {

        Class<?> clazz = null;

        //��ȡClass���������
        clazz = Class.forName("reflectdemo.User");

        //��һ�ַ�����ʵ����Ĭ�Ϲ��췽����User�����޲ι��캯��,�������쳣
        User user = (User) clazz.newInstance();
        user.setAge(20);
        user.setName("Rollen");
        System.out.println(user);

        System.out.println("--------------------------------------------");

        //��ȡ��String������public���캯��
        Constructor cs1 =clazz.getConstructor(String.class);
        //����User
        User user1= (User) cs1.newInstance("xiaolong");
        user1.setAge(22);
        System.out.println("user1:"+user1.toString());

        System.out.println("--------------------------------------------");

        //ȡ��ָ����int��String�������캯��,�÷�����˽�й���private
        Constructor cs2=clazz.getDeclaredConstructor(int.class,String.class);
        //������private�������ÿɷ���
        cs2.setAccessible(true);
        //����user����
        User user2= (User) cs2.newInstance(25,"lidakang");
        System.out.println("user2:"+user2.toString());

        System.out.println("--------------------------------------------");

        //��ȡ���й������private
        Constructor<?> cons[] = clazz.getDeclaredConstructors();
        // �鿴ÿ�����췽����Ҫ�Ĳ���
        for (int i = 0; i < cons.length; i++) {
            //��ȡ���캯����������
            Class<?> clazzs[] = cons[i].getParameterTypes();
            System.out.println("���캯��["+i+"]:"+cons[i].toString() );
            System.out.print("��������["+i+"]:(");
            for (int j = 0; j < clazzs.length; j++) {
                if (j == clazzs.length - 1)
                    System.out.print(clazzs[j].getName());
                else
                    System.out.print(clazzs[j].getName() + ",");
            }
            System.out.println(")");
        }

        Constructor cs3=clazz.getDeclaredConstructor(int.class,String.class);

        System.out.println("-----getDeclaringClass-----");
        Class uclazz=cs3.getDeclaringClass();
//Constructor�����ʾ�Ĺ��췽������
        System.out.println("���췽������:"+uclazz.getName());

        System.out.println("-----getGenericParameterTypes-----");
//�����ʾ�� Constructor ��������ʾ�ķ������β�����
        Type[] tps=cs3.getGenericParameterTypes();
        for (Type tp:tps) {
            System.out.println("��������tp:"+tp);
        }
        System.out.println("-----getParameterTypes-----");
//��ȡ���캯����������
        Class<?> clazzs[] = cs3.getParameterTypes();
        for (Class claz:clazzs) {
            System.out.println("��������:"+claz.getName());
        }
        System.out.println("-----getName-----");
//���ַ�����ʽ���ش˹��췽��������
        System.out.println("getName:"+cs3.getName());

        System.out.println("-----getoGenericString-----");
//���������� Constructor ���ַ��������а������Ͳ�����
        System.out.println("getoGenericString():"+cs3.toGenericString());
    }
}


class User {
    private int age;
    private String name;
    public User() {
        super();
    }
    public User(String name) {
        super();
        this.name = name;
    }

    /**
     * ˽�й���
     * @param age
     * @param name
     */
    private User(int age, String name) {
        super();
        this.age = age;
        this.name = name;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getAge()
    {
        return age;
    }

    public String getName()
    {
        return name;
    }
//..........ʡ��set �� get����
}
