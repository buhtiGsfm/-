package Interface;

import java.awt.Font;
import java.util.Calendar;
import javax.swing.JLabel;

/*
 * @Author: CodeDog
 * @Date: 2022-07-08 10:09:54
 * @Description: 状态类
 */
public class LabelStatu
{

    private JLabel[] labels = new JLabel[4];
    
    public LabelStatu()
    {
        // 初始化标签
        for (int i = 0, length = labels.length; i < length; i++)
        {
            labels[i] = new JLabel();
        }

        // 调用相关的初始化方法
        init();
    }
    
    private void init()
    {
        Calendar now = Calendar.getInstance();

        labels[0].setText(now.get(Calendar.HOUR_OF_DAY) + ":" + now.get(Calendar.MINUTE)); // 当前系统时间
        labels[1].setText(" -> ");
        labels[3].setText("状态: 暂停"); // 状态，是否在运行

        for (int i = 0; i < labels.length; i++)
        {
            labels[i].setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 23));
        }
    }

    // 获取按钮数组
    public JLabel[] getLabels()
    {
        return labels;
    }
}
