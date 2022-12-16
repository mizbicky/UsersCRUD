package org.example.user;

import org.example.DbUtil.DbUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

@WebServlet("/info")
public class UsersInfo extends HttpServlet {

    private static final String READ_USER_QUERY =
            "SELECT * FROM users WHERE id = ?";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String id = request.getParameter("id");
            int i = Integer.parseInt(id);
            ArrayList<User> users = userInfo(i);
            request.setAttribute("users", users);
            getServletContext().getRequestDispatcher("/info.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
    private ArrayList<User> userInfo(int id) {
        var users = new ArrayList<User>();
        try {
            Connection connection = DbUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(READ_USER_QUERY);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setEmail(resultSet.getString("email"));
                user.setUserName(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                users.add(user);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }
}
