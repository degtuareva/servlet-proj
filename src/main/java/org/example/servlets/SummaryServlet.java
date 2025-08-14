package org.example.servlets;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.servlets.model.Transaction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SummaryServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        var context = config.getServletContext();
        context.log("[SummaryServlet init]");

        int salary = Integer.parseInt(context.getInitParameter("salary"));
        int rent = Integer.parseInt(config.getInitParameter("rent"));

        context.setAttribute("freeMoney", salary - rent);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("rent", rent, Transaction.Type.EXPENSE));
        context.setAttribute("transactions", transactions);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var context = req.getServletContext();

        resp.setContentType("text/plain;charset=UTF-8");
        resp.getWriter().println("Transactions:");

        List<Transaction> transactions = (List<Transaction>) context.getAttribute("transactions");
        if (transactions == null) {
            resp.getWriter().println("No transactions available.");
            return;
        }

        for (Transaction t : transactions) {
            resp.getWriter().println(String.format("- %s: %s (%d)", t.getType(), t.getName(), t.getAmount()));
        }

        resp.getWriter().println("\nFree money: " + context.getAttribute("freeMoney"));
    }
}