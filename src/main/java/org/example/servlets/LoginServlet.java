package org.example.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class LoginServlet extends HttpServlet {

    private final String passwordCred = "123456";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Перенаправляем пользователя на статическую html страницу с формой(без нее работает ввод пароля!)
        resp.sendRedirect("login.html");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String password = req.getParameter("password");

        if (passwordCred.equals(password)) {
            HttpSession session = req.getSession();
            session.setMaxInactiveInterval(100);
            resp.sendRedirect("/summary");
        } else {
            resp.getWriter().println("Incorrect password");
        }
    }
}