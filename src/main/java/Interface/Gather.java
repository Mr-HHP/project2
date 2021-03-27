package Interface;

import Bean.Environment;

import java.util.List;

// 读取数据日志，封装数据
public interface Gather {
    // 封装数据
    public List<Environment> gather();
}
