package Util;

import Bean.Environment;
import Interface.BackUp;

import java.util.List;

/**
 * @program: project2->Util->BackUplmpl
 * @description: 数据备份类
 * @author: Mr.黄
 * @create: 2019-10-09 17:06
 **/
public class BackUplmpl implements BackUp {

    // 保存未成功入库的数据
    @Override
    public void save(List<Environment> list) {

    }

    // 获取未成功入库的数据
    @Override
    public List<Environment> load() {
        return null;
    }
}
