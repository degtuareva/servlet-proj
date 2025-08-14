package org.example;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SummaryServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config); // рекомендуется вызвать родительский init
        System.out.println("init");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet");
        resp.setContentType("text/plain");
        resp.getWriter().write("SummaryServlet is running!");
    }

    @Override
    public void destroy() {
        System.out.println("destroy");
    }
}
