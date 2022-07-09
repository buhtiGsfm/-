/*
 * @Author: CodeDog
 * @Date: 2022-07-08 10:29:44
 * @Description: 请填写简介
 */
package Test;

import java.io.File;
import java.io.IOException;

public class Test01 {
    
    public static void main(String[] args) {
        try
        {
            System.out.println(new File("./Information.properties").getCanonicalPath());
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
