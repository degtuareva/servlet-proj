package org.example.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.servlets.model.Transaction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExpensesServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null) {
            resp.getWriter().println("Not authorized");
            return;
        }

        var context = req.getServletContext();

        List<Transaction> transactions = (List<Transaction>) context.getAttribute("transactions");
        if (transactions == null) {
            transactions = new ArrayList<>();
        }

        int freeMoney = (int) context.getAttribute("freeMoney");

        for (var key : req.getParameterMap().keySet()) {
            int value;
            try {
                value = Integer.parseInt(req.getParameter(key));
            } catch (NumberFormatException e) {
                resp.getWriter().println("Invalid expense amount for " + key);
                return;
            }

            freeMoney -= value;
            if (freeMoney < 0) {
                resp.getWriter().println("Not enough money");
                return;
            }

            transactions.add(new Transaction(key, value, Transaction.Type.EXPENSE));
        }

        context.setAttribute("freeMoney", freeMoney);
        context.setAttribute("transactions", transactions);

        // Перенаправление на summary
        resp.sendRedirect(req.getContextPath() + "/summary");
    }
}