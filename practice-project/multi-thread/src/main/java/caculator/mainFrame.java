package caculator;

import javax.swing.*;

public class mainFrame
{
    public mainFrame()
    {
        init();
    }

    private void init()
    {
        JFrame jFrame = new JFrame("Caculator");

        //显示文本框
        JTextField result_TextField = new JTextField(result, 20);

        //清除按钮
        JButton clear_button = new JButton("Clear");

        //数字按钮
        JButton button0 = new JButton("0");
        JButton button1 = new JButton("1");
        JButton button2 = new JButton("2");
        JButton button3 = new JButton("3");
        JButton button4 = new JButton("4");
        JButton button5 = new JButton("5");
        JButton button6 = new JButton("6");
        JButton button7 = new JButton("7");
        JButton button8 = new JButton("8");
        JButton button9 = new JButton("9");

        //加减乘除等符号
        JButton button_point = new JButton(".");
        JButton button_add = new JButton("+");
        JButton button_jian = new JButton("-");
        JButton button_cheng = new JButton("*");
        JButton button_chu = new JButton("/");

        //等于
    }

}
