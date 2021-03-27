package Data;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @program: xml_test->test.xml->DataClient
 * @description: 发送指令给树莓派系统并且接收树莓派系统发回的指令数据
 * 处理指令生成一行数据，存储在Data包下的templogs文件中
 * @author: Mr.黄
 * @create: 2019-09-27 15:29
 **/
public class DataClient {
    private static String command_file = "src\\main\\java\\Xml\\command.xml";
    private static String temp_file = "src\\main\\java\\Xml\\tempFile.xml";
    private static String data_log = "src\\main\\java\\LogFile\\dataLog.txt";

    public static void main(String[] args) throws IOException {
//        collect();
    }

    public void collect() throws IOException {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                BufferedReader client_br = null;
                BufferedReader file_br = null;
                BufferedWriter client_bw = null;
                BufferedWriter file_bw = null;
                try {
                    // 创建客户端Socket对象
//                    Socket client = new Socket("192.168.1.101", 10000);
                    Socket client = new Socket("127.0.0.1", 12345);
                    System.out.println("已经连接树莓派");
                    // 密码：ligaotang
                    // 读取指令xml文件
                    file_br = new BufferedReader(
                            new FileReader(command_file));

                    // 获取Socket套接字流通道
                    // 发送xml文件给树莓派
                    client_bw = new BufferedWriter(
                            new OutputStreamWriter(
                                    client.getOutputStream()));
                    String line;
                    while ((line = file_br.readLine()) != null) {
                        client_bw.write(line);
                        client_bw.newLine();
                        client_bw.flush();
                    }
                    client_bw.write("0");
                    client_bw.newLine();
                    client_bw.flush();

                    /*
                    // 获取树莓派数据
                    file_bw = new BufferedWriter(
                            new FileWriter("F:\\idea_work\\project2\\src\\main\\java\\Bean\\data.xml"));
                    client_br = new BufferedReader(
                            new InputStreamReader(client.getInputStream()));
                    String data = null;
                    while ((data = client_br.readLine()) != null) {
                        file_bw.write(data);
                        file_bw.newLine();
                        file_bw.flush();
                    }
                    file_bw.flush();
                    */

                    // 测试
                    // 接收服务器数据并临时存储
                    file_bw = new BufferedWriter(
                            new FileWriter(temp_file));
                    client_br = new BufferedReader(
                            new InputStreamReader(client.getInputStream()));
                    String str = null;
                    while ((str = client_br.readLine()) != null) {
                        if ("0".equals(str))
                            break;
                        file_bw.write(str);
                        file_bw.newLine();
                        file_bw.flush();
                    }


                    // 处理临时数据
                    gather(temp_file);

                } catch (IOException e) {
                    e.printStackTrace();
                } finally {

                    try {
                        // 关闭资源
                        if (file_bw != null)
                            file_bw.close();
                        if (client_br != null)
//                            client_br.close();
                        if (client_bw != null)
//                            client_bw.close();
                        if (file_br != null)
                            file_br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        }, 0, 500);


    }

    // 处理临时数据，写入日志文件
    private static void gather(String tempFile) {
        if (!(new File(tempFile).exists())) {
            System.err.println("树莓派反馈数据临时文件不存在");
            return;
        }
        // 采集指令拼接
        String[] elements = {"SrcID", "DstID", "DevID", "SensorAddress", "Counter", "Cmd"};
        String str = parse(command_file, elements);

        // 反馈数据拼接
        String[] serverData = {"Data", "Status"};
        String serverStr = parse(tempFile, serverData);

        String time = new SimpleDateFormat("yyy-MM-dd HH:mm:ss").format(
                new Date());
//        System.out.println(time);
        String line = str + serverStr + time;
        BufferedWriter bw = null;
        try {
            // 提取数据写入日志文件
            bw = new BufferedWriter(
                    new FileWriter(
                            data_log, true));
            bw.write(line);
            bw.newLine();
            bw.flush();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭资源
                if (bw != null)
                    bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 解析XML文档，使用dom4j
    private static String parse(String fileName, String[] elements) {
        String line = "";
        // 获取dom4j解析器
        SAXReader dom4j = new SAXReader();
        try {
            // 获取Document对象操作xml文档
            Document document = dom4j.read(fileName);
            // 是否重定向dtd路径
            document.setEntityResolver(new EntityResolver() {
                @Override
                public InputSource resolveEntity(String publicId, String systemId) {
                    byte[] bytes = "重定向dtd路径".getBytes();
                    return new InputSource(
                            new ByteArrayInputStream(bytes));
                }
            });
            // 获取根节点
            Element root = document.getRootElement();
            // 获取标签内的文本数据
            for (String element : elements) {
                line += root.elementText(element) + "|";
            }

        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return line;
    }

}
