package org.example;

import org.example.DbUtil.DbUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            Connection connection = DbUtil.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}