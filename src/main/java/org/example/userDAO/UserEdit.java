package org.example.userDAO;

import org.example.DbUtil.DbUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;



@WebServlet("/edit")
public class UserEdit extends HttpServlet {

    private static final String EDIT_QUERY =
            "UPDATE users SET username = ?, email = ? WHERE id = ?";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        UserDao user = new UserDao();
        User read = user.read(Integer.parseInt(id));
        request.setAttribute("user", read);
        getServletContext().getRequestDispatcher("/edit.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        try {
            Connection con = DbUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(EDIT_QUERY);
            ps.setString(1, username);
            ps.setString(2, email);
            ps.setString(3, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("/list");
    }
}
