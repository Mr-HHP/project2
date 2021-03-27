package Interface;

import Bean.Environment;

import java.util.List;

// 把gather采集到的数据发送给中央服务器
public interface Client {
    public void send(List<Environment> list);
}
