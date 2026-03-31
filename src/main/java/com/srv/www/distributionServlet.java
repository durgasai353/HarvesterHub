package com.srv.www;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.database.www.DBConnection;

public class distributionServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();

        try {

            int itemId = Integer.parseInt(req.getParameter("itemId"));
            String farmer = req.getParameter("farmer");
            int qtyTaken = Integer.parseInt(req.getParameter("qtyTaken"));

            Connection con = DBConnection.getConnection();

            // Get stock details
            String sql = "SELECT qty, min_limit FROM stocks WHERE item_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, itemId);

            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                pw.println("<h3 style='color:red'>Item not found</h3>");
                return;
            }

            int currentQty = rs.getInt("qty");
            int minLimit = rs.getInt("min_limit");

            int remaining = currentQty - qtyTaken;

            // CONDITION 1 : Not enough stock
            if (qtyTaken > currentQty) {

                pw.println("<h3 style='color:red'>Not enough stock available!</h3>");
                return;
            }

            // CONDITION 2 : Minimum limit protection
            if (remaining < minLimit) {

                pw.println("<h3 style='color:red'>Distribution blocked!</h3>");
                pw.println("<p>Minimum stock must remain: " + minLimit + "</p>");
                pw.println("<p>Current stock: " + currentQty + "</p>");
                return;
            }

            // Update stock
            String update = "UPDATE stocks SET qty = qty - ? WHERE item_id=?";
            PreparedStatement ps2 = con.prepareStatement(update);
            ps2.setInt(1, qtyTaken);
            ps2.setInt(2, itemId);
            ps2.executeUpdate();

            // Insert distribution log
            String insert = "INSERT INTO distribution_logs VALUES (sq1.NEXTVAL, ?, ?, ?, SYSDATE)";
            PreparedStatement ps3 = con.prepareStatement(insert);

            ps3.setInt(1, itemId);
            ps3.setString(2, farmer);
            ps3.setInt(3, qtyTaken);

            ps3.executeUpdate();

            pw.println("<h2 style='color:green'>Distribution Recorded Successfully!</h2>");

            con.close();

        } catch (Exception e) {

            pw.println("<h3>Error: " + e.getMessage() + "</h3>");
            e.printStackTrace();
        }
    }
}