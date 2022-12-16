package org.example.user;

import org.example.DbUtil.DbUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/remove")
public class UserRemove extends HttpServlet {
    private static final String DELETE_QUERY = "DELETE FROM users where id = ?";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        int i = Integer.parseInt(id);
        try {
            Connection conn = DbUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(DELETE_QUERY);
            ps.setInt(1, i);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("/list");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
