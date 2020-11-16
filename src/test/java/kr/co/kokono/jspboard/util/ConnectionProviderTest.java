package kr.co.kokono.jspboard.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.sql.Connection;
import java.sql.SQLException;

@RunWith(JUnit4.class)
public class ConnectionProviderTest {

    @Test
    public void dbConnectionTest() {

        ConnectionProvider connectionProvider = new ConnectionProvider();
        try {
            Connection connection = connectionProvider.getConnection();
            System.out.println("connection = " + connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}