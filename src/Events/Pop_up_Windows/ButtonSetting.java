package Events.Pop_up_Windows;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.PlainDocument;
import Interface.ReadInformation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * @Author: CodeDog
 * @Date: 2022-07-09 12:40:28
 * @Description: 设置按钮的弹窗
 */
public class ButtonSetting extends PlainDocument implements ActionListener
{
    private JDialog dialog;
    private JButton[] jButtons;
    private JLabel[] jLabels;
    private JTextField[] text;
    private int limit; // 限制的长度

    public ButtonSetting(int limit)
    {
        super(); // 调用父类构造
        this.limit = limit;
    }

    public void getPop_Up()
    {
        init();
        initParameters();
    }

    // 初始化所有需要的组件
    public void init()
    {
        dialog = new JDialog();
        text = new JTextField[2];
        jLabels = new JLabel[2];
        jButtons = new JButton[2];

        // 添加标签
        jLabels[0] = new JLabel("时：");
        jLabels[1] = new JLabel("分：");

        // 添加按钮
        jButtons[0] = new JButton("确定");
        jButtons[1] = new JButton("取消");

        for (int i = 0, length = text.length; i < length; i++)
        {
            text[i] = new JTextField();
            dialog.add(text[i]);
            dialog.add(jButtons[i]);
            dialog.add(jLabels[i]);
        }
    }

    // 初始化组件参数的方法
    public void initParameters()
    {
        dialog.setTitle("关闭时间设置");
        dialog.setLayout(null);
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(null);
        dialog.setSize(250, 200);

        jLabels[0].setBounds(5, 8, 50, 50);
        jLabels[1].setBounds(5, 47, 50, 50);

        jButtons[0].setBounds(35, 100, 65, 42);
        jButtons[1].setBounds(105, 100, 65, 42);
        
        text[0].setBounds(65, 20, 50, 20);
        text[1].setBounds(65, 60, 50, 20);
        
        text[0].setDocument(new ButtonSetting(2));
        text[1].setDocument(new ButtonSetting(2));

        jButtons[0].addActionListener(this);
        jButtons[1].addActionListener(this);

        dialog.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

        if("确定".equals(e.getActionCommand()))
        {

            if("".equals(text[0].getText()) || "".equals(text[1].getText()))
            {
                JOptionPane.showMessageDialog(null,"“时” 或 “分” 不要留空！","消息提示",JOptionPane.WARNING_MESSAGE);	//消息对话框
                
            }else if(Integer.valueOf(text[0].getText()) > 23 || Integer.valueOf(text[1].getText()) > 59){

                JOptionPane.showMessageDialog(null,"“时” 不能大于 23，“分” 不能大于 59！！！","消息提示",JOptionPane.WARNING_MESSAGE);	//消息对话框
            }else
            {
                ReadInformation read = ReadInformation.getInstance();

                // 如果读取到的数字长度为 1，就在该数字前加上一个 0
                read.setProperty("Hours", text[0].getText().length() == 1 ? "0" + text[0].getText() : text[0].getText());
                read.setProperty("Minutes", text[1].getText().length() == 1 ? "0" + text[1].getText() : text[1].getText());
                dialog.dispose();
            }
        }else
        {
            dialog.dispose();
        }
    }

    // 该方法用于给 TextField 类进行输入限制
    public void insertString(int offset, String str, AttributeSet attr) throws javax.swing.text.BadLocationException
    {
        if (str == null)
        {
            return;
        }
        if (attr == null)
        {
            int allowCount = limit - getLength();
            if (allowCount > 0)
            {
                if (allowCount < str.length())
                    str = str.substring(0, allowCount);
            } else
                return;
        }

        char[] s = str.toCharArray();
        int length = 0;
        // 过滤非数字
        for (int i = 0; i < s.length; i++)
        {
            if ((s[i] >= '0') && (s[i] <= '9'))
            {
                s[length++] = s[i];
            }
            // 插入内容
            super.insertString(offset, new String(s, 0, length), attr);
        }
    }

}
