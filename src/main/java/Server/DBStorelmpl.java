package Server;

import Bean.Environment;
import Bean.JdbcPakge;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * @program: project2->Server->DBStorelmpl
 * @description: 入库模块，将集合数据录入数据库
 * @author: Mr.黄
 * @create: 2019-09-29 16:47
 **/
public class DBStorelmpl {
    private static int id;

    public void saveEnvironment(List<Environment> list) {
        // 连接
        Connection connection = null;
        // Statement或PreparedStatement
        PreparedStatement ps = null;
        try {
            // 获取连接
            connection = JdbcPakge.getConnection();
            // 设置事务手动提交
//            connection.setAutoCommit(false);
            // sql语句
            String humidSql = "insert into humid " +
                    "values(?,?,?,?,?,?,?,?,?,?,?)";

            String sunshineSql = "insert into sunshine " +
                    "values(?,?,?,?,?,?,?,?,?,?)";

            String co2Sql = "insert into co2 " +
                    "values(?,?,?,?,?,?,?,?,?,?)";
            for (Environment e : list) {
                // 温湿度
                if ("16".equals(e.getSersorAddress())) {
                    // 创建Statement或PreparedStatement对象
                    ps = connection.prepareStatement(humidSql);

                    ps.setInt(1, ++id);
                    ps.setString(2, e.getSrcId());
                    ps.setString(3, e.getDstID());
                    ps.setString(4, e.getDevId());
                    ps.setString(5, e.getSersorAddress());
                    ps.setString(6, e.getCount());
                    ps.setString(7, e.getCmd());
                    ps.setString(8, e.getTemperature());
                    ps.setString(9, e.getHumidity());
                    ps.setString(10, e.getStatus());
                    ps.setString(11, e.getStatus());
                    // 执行sql语句
                    ps.execute();
                } else if ("256".equals(e.getSersorAddress())) {
                    // 光照强度
                } else if ("1280".equals(e.getSersorAddress())) {
                    // 二氧化碳
                }
            }
            // 提交事务
//            connection.commit();

        } catch (SQLException e) {
            try {
                // 事务回滚
                connection.rollback();
            } catch (SQLException ex) {
                System.err.println("数据库回滚失败！！！");
            } finally {
                // 数据备份


            }
            e.printStackTrace();
        } finally {
            // 关闭资源
            JdbcPakge.close(connection, null, ps, null);
        }

    }
}
