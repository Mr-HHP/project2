package Data;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @program: xml_test->test->DataServer
 * @description: 模拟树莓派温度湿度产生 服务器
 * @author: Mr.黄
 * @create: 2019-09-27 17:07
 **/
public class DataServer {
    public static void main(String[] args) throws IOException {


        /*
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                BufferedWriter bw = null;
                try {
                    bw = new BufferedWriter(
                            new FileWriter("F:\\idea_work\\project2\\src\\main\\java\\Bean\\radwtmp.txt", true));
                        bw.write("100|101|2|16|1|3|5d606f7802|1|2019-09-29 15:26:20");
                        bw.newLine();
                        bw.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, 0, 2000);
        */

        new DataServer().server();

    }

    public void server() throws IOException {
        // 创建模拟服务端ServerSocket对象，并监听一个端口
        ServerSocket ss = new ServerSocket(12345);
        System.out.println("服务器已经启动");

        while (true) {
            // 获取客户端的Socket对象
            Socket client = ss.accept();
            System.out.println(client.getInetAddress().getHostAddress() + "已经连接");
            // 创建套接字流通道
            BufferedReader server_br = new BufferedReader(
                    new InputStreamReader(
                            client.getInputStream()));

            // 获取客户端数据
            BufferedWriter file_bw = new BufferedWriter(
                    new FileWriter("src\\main\\java\\Xml\\server_accept.xml"));
            String line = null;
            while ((line = server_br.readLine()) != null) {
                if ("0".equals(line))
                    break;
                file_bw.write(line);
                file_bw.newLine();
                file_bw.flush();
            }
            file_bw.flush();

            System.out.println("*********");

            // 反馈数据
            BufferedReader file_br = new BufferedReader(
                    new FileReader("F:\\idea_work\\project2\\src\\main\\java\\Xml\\server.xml"));

            BufferedWriter server_bw = new BufferedWriter(
                    new OutputStreamWriter(client.getOutputStream()));
            String str = null;
            while ((str = file_br.readLine()) != null) {
                server_bw.write(str);
                server_bw.newLine();
                server_bw.flush();
            }
            server_bw.write("0");
            server_bw.newLine();
            server_bw.flush();

            // 关闭资源
            server_bw.close();
            file_br.close();
            file_bw.close();
            server_br.close();

        }

    }

}
