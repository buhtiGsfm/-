package Interface;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.Toolkit;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.PopupMenu;
import java.awt.TrayIcon;
import java.awt.MenuItem;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/*
 * @Author: CodeDog
 * @Date: 2022-07-08 09:30:39
 * @Description: 主窗口类
 */
public class Window extends JFrame implements WindowListener
{
    // 初始化
    {
        setTitle("自动关闭器");
        setResizable(false);// 不可改变窗口大小
        setLocationRelativeTo(null); // 设置初始化位置
        // 设置窗口风格
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());// UIManager.getSystemLookAndFeelClassName()
                                                                                // 是当前系统风格
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException e)
        {
            e.printStackTrace();
        }
        addWindowListener(this);
        setIconImage(Toolkit.getDefaultToolkit().getImage("./image\\espejo.png"));
        setSize(500, 350);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public Window()
    {
        this.add(new Panel()); // 添加面板
        this.validate();
    }

    @Override
    public void windowClosed(WindowEvent e)
    {
        // 获得系统托盘对象
        SystemTray systemTray = SystemTray.getSystemTray();

        // 判断是否支持系统托盘
        if (systemTray.isSupported())
        {
            Image image = Toolkit.getDefaultToolkit().getImage("./image\\espejo.png");

            // 创建托盘图标
            TrayIcon trayIcon = new TrayIcon(image);
            trayIcon.setImageAutoSize(true);

            // 创建弹出菜单
            PopupMenu popupMenu = new PopupMenu();
            MenuItem showItem = new MenuItem("显示界面");
            showItem.addActionListener((ActionEvent) -> {
                setVisible(true);
                systemTray.remove(trayIcon);
            });


            MenuItem exitItem = new MenuItem("退出");
            exitItem.addActionListener((ActionEvent) -> {
                ReadInformation.getInstance().setProperty("Statu", "暂停");
                System.exit(0);
            });

            popupMenu.add(showItem);
            popupMenu.addSeparator();

            popupMenu.add(exitItem);

            // 为托盘图标加弹出菜弹
            trayIcon.setPopupMenu(popupMenu);
            
            try
            {
                // 为系统托盘加托盘图标
                systemTray.add(trayIcon);
            } catch (Exception e2)
            {
                e2.printStackTrace();
            }
        } else
        {
            JOptionPane.showMessageDialog(null, "not support");
        }

    }

    @Override
    public void windowOpened(WindowEvent e)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowClosing(WindowEvent e)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowIconified(WindowEvent e)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowDeiconified(WindowEvent e)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowActivated(WindowEvent e)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowDeactivated(WindowEvent e)
    {
        // TODO Auto-generated method stub

    }
}
