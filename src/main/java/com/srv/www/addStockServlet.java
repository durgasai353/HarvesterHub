package com.srv.www;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.database.www.DBConnection;
@WebServlet("/addStock")
public class addStockServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        response.setContentType("text/html");

        try {
            String name = request.getParameter("name");
            String category = request.getParameter("category");
            int qty = Integer.parseInt(request.getParameter("qty"));
            String minStr = request.getParameter("min");

            int min = 0;   // default value

            if(minStr != null && !minStr.isEmpty()){
                min = Integer.parseInt(minStr);
            }

            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO STOCKS (ITEM_ID, NAME, CATEGORY, QTY, MIN_LIMIT) VALUES (stock_seq.NEXTVAL, ?, ?, ?, ?)";


            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, name);
            ps.setString(2, category);
            ps.setInt(3, qty);
            ps.setInt(4, min);

            ps.executeUpdate();

            response.getWriter().println("Stock Added Successfully!");

            con.close();

        } catch(Exception e){
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }

    }
}