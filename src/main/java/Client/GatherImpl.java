package Client;

import Bean.Environment;
import Data.DataClient;
import Interface.Gather;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: project2->Client->GatherImpl
 * @description: 采集模块实现
 * @author: Mr.黄
 * @create: 2019-09-28 22:20
 **/
public class GatherImpl implements Gather {
    // 读取文件开始指针位置
    private volatile static long startRead;
    private static String data_log = "src\\main\\java\\LogFile\\dataLog.txt";

    // 读取数据日志文件封装数据
    @Override
    public List<Environment> gather() {
        List<Environment> list = new ArrayList<Environment>();
        RandomAccessFile rafRead = null;
        try {

            // 数据采集
//            new DataClient().collect();
            // 睡1秒，让数据采集先执行，确保已经采集到数据并写入数据日志文件
            Thread.sleep(1000);

            // 读取数据日志文件
            rafRead = new RandomAccessFile(data_log, "r");
            // 每次读一整个文件的长度
            // 读取结束位置
            long endRead = rafRead.length();
            String line = null;
            String[] data;
            Environment environment = null;
            while (endRead > startRead) {
                rafRead.seek(startRead);
                line = rafRead.readLine();
                // 每一行加上2个字节的回车换行符
                startRead += line.length() + 2;
//                System.out.println(line);
                environment = new Environment();
                data = line.split("[|]");
                if ("16".equals(data[3])) {
                    // 处理湿度温度数据
                    // 温度
                    double temperature = Integer.parseInt(data[6].substring(0, 4), 16) * 0.00268127 - 46.85;
                    // 湿度
                    double humidity = Integer.parseInt(data[6].substring(4, 8), 16) * 0.00190735 - 6;
                    environment.setTemperature(temperature + "");
                    environment.setHumidity(humidity + "");

                } else if ("256".equals(data[3])) {
                    // 处理光照强度数据
                } else if ("1280".equals(data[3])) {
                    // 处理二氧化碳数据
                }
                environment.setSrcId(data[0]);
                environment.setDstID(data[1]);
                environment.setDevId(data[2]);
                environment.setSersorAddress(data[3]);
                environment.setCount(data[4]);
                environment.setCmd(data[5]);
                environment.setStatus(data[7]);
                environment.setGather_date(data[8]);

                list.add(environment);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (rafRead != null) {
                    rafRead.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
//            System.out.println("日志采集成功");
        }
        return list;
    }
}
