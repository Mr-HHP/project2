package Configuration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * @program: project2->Configuration->ConfigurationImpl
 * @description: 配置模块实现，主要设置配置文件
 * @author: Mr.黄
 * @create: 2019-09-29 20:32
 **/
public class ConfigurationImpl {
    private static Properties properties = null;
    public static String getAttr(String Attr) {
        String string = properties.getProperty(Attr);
        if (string == null) {
            throw new NullPointerException("配置文件不存在该属性！！！");
        } else {
            return string;
        }
    }

    static {
        // 创建properties对象，读取properties文件
        properties = new Properties();
        try {
            // 连接文件
            properties.load(
                    new BufferedReader(
                            new FileReader("F:\\idea_work\\project2\\src\\main\\java\\Configuration\\config.properties")));
        } catch (IOException e) {
            System.err.println("获取配置文件失败！！！");
        }
    }

    /*
    public static void main(String[] args) {
        System.out.println(getAttr("123"));
        System.out.println(getAttr("url"));
    }
    */

}
