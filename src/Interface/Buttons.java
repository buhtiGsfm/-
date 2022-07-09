package Interface;

import javax.swing.JButton;
import Events.ButtonEvent;

/*
 * @Author: CodeDog
 * @Date: 2022-07-08 09:47:13
 * @Description: 按钮组件
 */
public class Buttons {
    
    // 两种按钮：开始，暂停
    private JButton[] buttons = new JButton[3];

    public Buttons()
    {
        // 初始化按钮
        for (int i = 0, length = buttons.length; i < length; i++) {
            buttons[i] = new JButton();
        }

        // 调用相关的初始化方法
        init();
    }

    private void init()
    {
        buttons[0].setText("开始");
        buttons[1].setText("暂停");
        buttons[2].setText("设置");

        ButtonEvent event = new ButtonEvent();
        buttons[0].addActionListener(event);
        buttons[1].addActionListener(event);
        buttons[2].addActionListener(event);
    }


    // 获取按钮数组
    public JButton[] getButtons()
    {
        return buttons;
    }
}
