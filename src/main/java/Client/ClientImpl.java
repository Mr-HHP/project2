package Client;

import Bean.Environment;
import Interface.Client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @program: project2->Client->ClientImpl
 * @description: 客户端模块实现
 * @author: Mr.黄
 * @create: 2019-09-28 22:19
 **/
public class ClientImpl implements Client {
    private static String ip = "127.0.0.1";
    private static int port = 12345;

    public static void main(String[] args) {
        new ClientImpl().send(new GatherImpl().gather());
    }

    @Override
    public void send(List<Environment> list) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // 接收采集模块封装好的对象集合
                List<Environment> list = new GatherImpl().gather();
                ObjectOutputStream oos = null;
                try {
                    // 创建Socket对象，连接服务器
                    Socket cs = new Socket(ip, port);
                    System.out.println("成功连接服务端！！！");
                    // 套接字流通道
                    oos = new ObjectOutputStream(cs.getOutputStream());
                    oos.writeObject(list);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    // 关闭资源
                    try {
                        if (oos != null) {
                            oos.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, 3000, 3000);
    }
}
