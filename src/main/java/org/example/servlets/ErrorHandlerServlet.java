package org.example.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/error-handler")
public class ErrorHandlerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Throwable throwable = (Throwable) req.getAttribute("jakarta.servlet.error.exception");
        Integer statusCode = (Integer) req.getAttribute("jakarta.servlet.error.status_code");

        resp.setContentType("text/plain;charset=UTF-8");

        if (statusCode != null && statusCode == 404) {
            resp.getWriter().println("Error (404) — page not found");
        } else if (throwable != null) {
            resp.getWriter().println("Error (500) — " + throwable.toString());
        } else {
            resp.getWriter().println("Error occurred");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}