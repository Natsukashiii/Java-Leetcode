package reflectdemo;

import java.lang.reflect.Field;

public class ReflectField
{

    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, InstantiationException
    {
        Class<?> clazz = Class.forName("reflectdemo.Student");
        //��ȡָ���ֶ����Ƶ�Field��,ע���ֶ����η�����Ϊpublic���Ҵ��ڸ��ֶ�,
        // ������NoSuchFieldException
        Field field = clazz.getField("age");
        System.out.println("field:" + field);

        //��ȡ�������η�Ϊpublic���ֶ�,���������ֶ�,ע�����η�Ϊpublic�Ż��ȡ
        Field fields[] = clazz.getFields();
        for (Field f : fields)
        {
            System.out.println("f:" + f.getDeclaringClass());
        }

        System.out.println("================getDeclaredFields====================");
        //��ȡ��ǰ�����ֶ�(����private�ֶ�),ע�ⲻ����������ֶ�
        Field fields2[] = clazz.getDeclaredFields();
        for (Field f : fields2)
        {
            System.out.println("f2:" + f.getDeclaringClass());
        }
        //��ȡָ���ֶ����Ƶ�Field��,�������������η����Զ�,ע�ⲻ����������ֶ�
        Field field2 = clazz.getDeclaredField("desc");
        System.out.println("field2:" + field2);


        //��ȡClass��������
        Class<?> clazz1 = Class.forName("reflectdemo.Student");

        Student st = (Student) clazz1.newInstance();
//��ȡ����public�ֶβ���ֵ
        Field ageField = clazz1.getField("age");
        ageField.set(st, 18);
        Field nameField = clazz1.getField("name");
        nameField.set(st, "Lily");

//ֻ��ȡ��ǰ����ֶ�,����ȡ������ֶ�
        Field descField = clazz1.getDeclaredField("desc");
        descField.set(st, "I am student");
        Field scoreField = clazz1.getDeclaredField("score");
//���ÿɷ��ʣ�score��private��
        scoreField.setAccessible(true);
        scoreField.set(st, 88);
        System.out.println(st.toString());

//��������Student{age=18, name='Lily ,desc='I am student', score=88}

//��ȡ�ֶ�ֵ
        System.out.println(scoreField.get(st));
// 88

    }
    /**
     ������:
     field:public int reflect.Person.age
     f:public java.lang.String reflect.Student.desc
     f:public int reflect.Person.age
     f:public java.lang.String reflect.Person.name

     ================getDeclaredFields====================
     f2:public java.lang.String reflect.Student.desc
     f2:private int reflect.Student.score
     field2:public java.lang.String reflect.Student.desc
     */
}

class Person
{
    public int age;
    public String name;
    //ʡ��set��get����
}


class Student extends Person
{
    public String desc;
    private int score;
    //ʡ��set��get����
}