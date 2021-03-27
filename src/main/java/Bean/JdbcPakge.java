package Bean;

import Configuration.ConfigurationImpl;

import java.sql.*;

/**
 * @program: project2->Bean->JdbcPakge
 * @description: 封装jdbc
 * @author: Mr.黄
 * @create: 2019-09-29 17:05
 **/
public class JdbcPakge {
    private static Connection connection = null;
    private static String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://192.168.85.134:3306/MyProject";
    private static String user = "root";
    private static String password = "root";

    // 向外提供获取连接方法
    public static Connection getConnection() {
        if (connection == null) {
            synchronized (JdbcPakge.class) {
                if (connection == null) {
                    createConnection();
                }
            }
        }
        return connection;
    }

    // 注册驱动，获取连接
    private static void createConnection() {
        try {
            // 驱动
            Class.forName(driver);
            // 获取连接
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 关闭资源
    public static void close(Connection connection,
                      Statement statement,
                      PreparedStatement preparedStatement,
                      ResultSet resultSet) {
        try {
            if (connection != null)
                connection.close();
            if (statement != null)
                statement.close();
            if (preparedStatement != null)
                preparedStatement.close();
            if (resultSet != null)
                resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
