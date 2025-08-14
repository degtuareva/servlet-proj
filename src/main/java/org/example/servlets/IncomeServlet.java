package org.example.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.servlets.model.Transaction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/incomes/add")
public class IncomeServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null) {
            resp.getWriter().println("Not authorized");
            return;
        }

        String name = req.getParameter("name");
        String amountStr = req.getParameter("amount");

        if (name == null || amountStr == null) {
            resp.getWriter().println("Missing parameters");
            return;
        }

        int amount;
        try {
            amount = Integer.parseInt(amountStr);
        } catch (NumberFormatException e) {
            resp.getWriter().println("Invalid amount");
            return;
        }

        var context = req.getServletContext();
        List<Transaction> transactions = (List<Transaction>) context.getAttribute("transactions");

        if (transactions == null) {
            transactions = new ArrayList<>();
        }

        transactions.add(new Transaction(name, amount, Transaction.Type.INCOME));

        // Обновляем freeMoney: freeMoney += amount
        int freeMoney = (int) context.getAttribute("freeMoney");
        context.setAttribute("freeMoney", freeMoney + amount);

        context.setAttribute("transactions", transactions);

        // Перенаправление на summary
        resp.sendRedirect(req.getContextPath() + "/summary");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().println("<html><body>");
        resp.getWriter().println("<form method='post' action='incomes/add'>");
        resp.getWriter().println("Name: <input type='text' name='name'><br>");
        resp.getWriter().println("Amount: <input type='number' name='amount'><br>");
        resp.getWriter().println("<input type='submit' value='Add Income'>");
        resp.getWriter().println("</form>");
        resp.getWriter().println("</body></html>");
    }
}

