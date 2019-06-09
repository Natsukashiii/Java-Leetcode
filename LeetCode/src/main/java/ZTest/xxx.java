package ZTest;

import javax.swing.text.MutableAttributeSet;
import java.math.BigDecimal;
import java.util.Arrays;

public class xxx
{
    public static void main(String[] args)
    {
        Storage s1 = new Storage(2, 4);
        Storage s2 = new Storage(6, 8);
        System.out.println(s1.data1 + " " + s1.data2);
        System.out.println(s2.data1 + " " + s2.data2);
        s1.modify(s1.data1);
        s2.modify(s1.data1);
        System.out.println(s1.data1 + " " + s1.data2);
        System.out.println(s2.data1 + " " + s2.data2);
        int mod = 2;
        s1.modify(mod);
        s2.modify(mod);
        System.out.println(s1.data1 + " " + s1.data2);
        System.out.println(s2.data1 + " " + s2.data2);
    }
}

