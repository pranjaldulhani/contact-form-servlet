package com.example;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@WebServlet("/contact")
public class ContactServlet extends HttpServlet {

    // in-memory (server-side) list
    private static final List<String[]> messages =
        Collections.synchronizedList(new ArrayList<>());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String message = req.getParameter("message");

        // server-side validation
        if (name == null || name.isBlank() || email == null || email.isBlank()
                || message == null || message.isBlank()) {
            res.sendRedirect("index.html");
            return;
        }

        messages.add(new String[]{name, email, message});

        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out = res.getWriter();
        out.println("<!DOCTYPE html><html><head><title>Thank You</title>");
        out.println("<link rel='stylesheet' href='css/style.css'></head><body>");
        out.println("<div class='card'>");
        out.println("<h2>Thank You, " + esc(name) + "!</h2>");
        out.println("<p>Your message has been received.</p>");
        out.println("<p><b>Email:</b> " + esc(email) + "</p>");
        out.println("<p><b>Message:</b> " + esc(message) + "</p>");
        out.println("<p>Total messages stored: " + messages.size() + "</p>");
        out.println("<a href='index.html'>Back to form</a>");
        out.println("</div></body></html>");
    }

    // prevents XSS
    private String esc(String s) {
        return s.replace("&", "&amp;").replace("<", "&lt;")
                .replace(">", "&gt;").replace("\"", "&quot;");
    }
}