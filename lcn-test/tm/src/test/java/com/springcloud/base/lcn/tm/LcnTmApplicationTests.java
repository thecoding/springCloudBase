package com.springcloud.base.lcn.tm;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.*;

class LcnTmApplicationTests {

    @Test
    void contextLoads() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://192.168.5.130:3306/tx_manager?characterEncoding=UTF-8";
        Connection connection = DriverManager.getConnection(url, "root", "123456");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from tx_order where id = 1");
        while (resultSet.next()) {
            String id = resultSet.getString("id");
            String orderName = resultSet.getString("order_name");
            System.out.printf("id=%s,orderName=%s", id, orderName);
        }
        resultSet.close();
        statement.close();
        connection.close();
    }

}
