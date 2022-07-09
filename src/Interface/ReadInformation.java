package Interface;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/*
 * @Author: CodeDog
 * @Date: 2022-07-08 10:16:25
 * @Description: 配置文件读取工具类
 */
public class ReadInformation
{
    private static ReadInformation instance;// 不实例化
    private static Properties properties;
    private static String filePath = null;

    private ReadInformation()
    {
        try
        {
            this.filePath = new File("./Information.properties").getCanonicalPath();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    // 懒汉设计模式
    public static ReadInformation getInstance()
    {
        if (instance == null)
        {
            instance = new ReadInformation();

            if(properties == null)
            {
                properties = new Properties();
            }
        }
        return instance;
    }

    /**
     * 获取 key 对应的值
     * 
     * @param key 值对应的 key
     * @return 获取 key 对应的值
     */
    public static Object getProperty(String key)
    {
        FileInputStream in = null;
        try
        {
            in = new FileInputStream(filePath);
            properties.load(in);
        } catch (IOException e)
        {
            e.printStackTrace();
        }finally
        {
            try
            {
                in.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return properties.get(key);
    }

    /**
     * 更新键值对
     * @param key
     * @param value
     */
    public static void setProperty(String key, Object value)
    {
        // 1.读取原来的数据
        FileInputStream in = null;
        FileOutputStream out = null;

        try
        {
            in = new FileInputStream(filePath);
            properties.load(in);
            
            // 2.更新数据
            properties.setProperty(key, (String) value);

            out = new FileOutputStream(filePath);
            properties.store(out, null);
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                out.close();
                in.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
