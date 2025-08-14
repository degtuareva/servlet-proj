package org.example.servlets.report;

import jakarta.servlet.AsyncContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static java.lang.String.format;

@WebServlet(value = "/complex-report", asyncSupported = true)
public class ComplexReportServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long start = System.currentTimeMillis();
//        try{
//            Thread.sleep(3000);
//        }catch(InterruptedException e){
//            throw new RuntimeException(e);
//        }
        final AsyncContext asyncContext = req.startAsync();
        new Thread(() -> {
            try {
                Thread.sleep(3000);
                asyncContext.getResponse().getWriter().println("Success");
                asyncContext.complete();
            } catch (InterruptedException | IOException e) {
                throw new RuntimeException(e);
            }
        }).start();

        req.getServletContext().log(format("[ComplexReportServlet] Tread %s,%d ", Thread.currentThread().getName(), System.currentTimeMillis() - start));
        //resp.getWriter().println("Success");
    }
}