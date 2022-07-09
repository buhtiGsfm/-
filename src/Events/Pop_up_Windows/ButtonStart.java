package Events.Pop_up_Windows;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Interface.ReadInformation;

/*
 * @Author: CodeDog
 * @Date: 2022-07-09 14:26:16
 * @Description: 开始的线程类
 */
public class ButtonStart implements Runnable{

    private boolean flag = true;


    @Override
    public void run()
    {
        Date day = null;
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        String[] strs = null;
        
        ReadInformation read = ReadInformation.getInstance();
        read.setProperty("Statu", "运行");
        
        while(flag)
        {
            if("暂停".equals(read.getProperty("Statu") + ""))
            {
                flag = false;
                break;
            }

            day = new Date();

            strs = df.format(day).split(":");
            if(strs[0].equals(read.getProperty("Hours")) && strs[1].equals(read.getProperty("Minutes")))
            {
                // 调用关机程序
                try
                {
                    Process process = Runtime.getRuntime().exec("shutdown /p");
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }

            System.out.println("运行中...");

            try
            {
                Thread.sleep(5000);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        
    }
    
}
