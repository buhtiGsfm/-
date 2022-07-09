package Interface;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * @Author: CodeDog
 * @Date: 2022-07-08 09:39:52
 * @Description: 面板，所有组件都添加到该面板中
 */
public class Panel extends JPanel implements Runnable
{
    // 初始化
    {
        setSize(500, 350);
        setLayout(null);
    }

    private JButton[] buttons;
    private JLabel[] labels;

    public Panel()
    {
        addLabels();
        addButtons();
        new Thread(this).start();
    }

    // 添加按钮操作在该方法内操作
    private void addButtons()
    {
        buttons = new Buttons().getButtons();

        buttons[0].setBounds(95, 200, 75, 35);
        buttons[1].setBounds(200, 200, 75, 35);
        buttons[2].setBounds(305, 200, 75, 35);

        for (int i = 0, length = buttons.length; i < length; i++)
        {
            add(buttons[i]);
        }
    }

    // 添加时间标签操作在该方法内操作
    private void addLabels()
    {
        labels = new LabelStatu().getLabels();

        labels[0].setBounds(135, 15, 100, 100);
        labels[1].setBounds(223, 15, 100, 100);
        labels[2].setBounds(275, 15, 100, 100);
        labels[3].setBounds(178, 75, 135, 100);

        for (int i = 0, length = labels.length; i < length; i++)
        {
            add(labels[i]);
        }
    }

    @Override
    public void run()
    {
        Date day = null;
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        StringBuilder time = new StringBuilder();

        ReadInformation read = ReadInformation.getInstance();
        while (true)
        {
            day = new Date();
            time.append(df.format(day));

            labels[0].setText(time.toString());
            labels[2].setText(read.getProperty("Hours") + ":" + read.getProperty("Minutes"));
            labels[3].setText("状态: " + read.getProperty("Statu"));
            validate();

            try
            {
                Thread.sleep(1000);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }

            time.delete(0, time.length());
        }
    }
}
