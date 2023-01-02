package org.example.userDAO;

import org.example.DbUtil.DbUtil;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDao {
    private static final String CREATE_USER_QUERY =
            "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";

    private static final String READ_USER_QUERY =
            "SELECT * FROM users WHERE id = ?";

    private static final String EDIT_QUERY =
            "UPDATE users SET email = ?, username = ?, password = ? WHERE id = ?";

    private static final String DELETE_QUERY = "DELETE FROM users where id = ?";

    private static final String READ_ALL_USERS_QUERY =
            "SELECT * FROM users";

    private static final String GET_ID =
            "SELECT id FROM users where id = ?";

    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }


    public User create(User user) {
        try (Connection con = DbUtil.getConnection()) {
            PreparedStatement ps = con.prepareStatement(CREATE_USER_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getEmail());
            ps.setString(3, hashPassword(user.getPassword()));
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                System.out.println("Your ID: " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }


        return user;
    }

    public User read(int userId) {
        try {

            Connection con = DbUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(READ_USER_QUERY);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUserName(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                return user;

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public User update(User user) {
        try (Connection con = DbUtil.getConnection()) {
            PreparedStatement ps = con.prepareStatement(EDIT_QUERY);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getUserName());
            ps.setString(3, user.getPassword());
            ps.setInt(4, user.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return user;
    }

    public User delete(int userId) {
        try {
            Connection conn = DbUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(DELETE_QUERY);
            ps.setInt(1, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public ArrayList<User> findAll() {
        try {
            var users = new ArrayList<User>();
            Connection conn = DbUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(READ_ALL_USERS_QUERY);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setUserName(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}






