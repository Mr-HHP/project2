package Interface;

import Bean.Environment;

import java.util.List;

public interface BackUp {
    // 保存未成功入库的数据或未成功发送的数据
    public void save(List<Environment> list);
    // 获取未成功入库的数据
    public List<Environment> load();
}
