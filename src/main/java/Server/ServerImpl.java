package Server;

import Bean.Environment;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

/**
 * @program: project2->ServerImpl->ServerImpl
 * @description: 服务器模块，接收客户端整理的集合数据
 * @author: Mr.黄
 * @create: 2019-09-29 14:53
 **/
public class ServerImpl {
    private static int port = 12345;
    public static void main(String[] args) {
        new ServerImpl().receiver();
    }
    private void receiver() {
        ObjectInputStream ois = null;
        try {
            // 创建服务器ServerSocket对像， 并监听一个端口
            ServerSocket ss = new ServerSocket(port);
            System.out.println("服务器启动成功");
            while (true) {
                // 接收客户端
                Socket client = ss.accept();
                System.out.println("客户端连接成功");
                // 套接字流通道
                ois = new ObjectInputStream(client.getInputStream());
                // 接收数据
                List<Environment> list = (List<Environment>) ois.readObject();
                // 发送数据进行入库处理
                new DBStorelmpl().saveEnvironment(list);

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
