package Events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Events.Pop_up_Windows.ButtonSetting;
import Events.Pop_up_Windows.ButtonStart;
import Interface.ReadInformation;

/*
 * @Author: CodeDog
 * @Date: 2022-07-08 18:26:27
 * @Description: 按钮事件类
 */
public class ButtonEvent implements ActionListener
{

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if ("开始".equals(e.getActionCommand()))
        {
            new Thread(new ButtonStart()).start();
        } else if ("暂停".equals(e.getActionCommand()))
        {
            ReadInformation read = ReadInformation.getInstance();
            read.setProperty("Statu", "暂停");
        } else if ("设置".equals(e.getActionCommand()))
        {
            new ButtonSetting(2).getPop_Up();
        }
    }
}
