package com.srv.www;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.database.www.DBConnection;

public class stockViewServlet extends HttpServlet {

protected void doGet(HttpServletRequest req, HttpServletResponse res)
throws IOException, ServletException {

res.setContentType("text/html");
PrintWriter pw = res.getWriter();

pw.println("<html>");
pw.println("<head>");

pw.println("<title>View Stock</title>");

pw.println("<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css'>");

pw.println("<style>");
pw.println("body{background:#f4f6f9;}");
pw.println("</style>");

pw.println("</head>");
pw.println("<body>");

pw.println("<div class='container mt-5'>");

pw.println("<h2 class='mb-4'>Harvester Hub - Current Inventory</h2>");

pw.println("<table class='table table-bordered table-striped text-center'>");

pw.println("<thead class='table-success'>");
pw.println("<tr>");
pw.println("<th>ID</th>");
pw.println("<th>Name</th>");
pw.println("<th>Category</th>");
pw.println("<th>Quantity</th>");
pw.println("<th>Status</th>");
pw.println("</tr>");
pw.println("</thead>");

pw.println("<tbody>");

try {

Connection con = DBConnection.getConnection();
Statement stmt = con.createStatement();

ResultSet rs = stmt.executeQuery("SELECT * FROM STOCKS");

while(rs.next()) {

int id = rs.getInt("ITEM_ID");
String name = rs.getString("NAME");
String category = rs.getString("CATEGORY");
int qty = rs.getInt("QTY");
int min = rs.getInt("MIN_LIMIT");

pw.println("<tr>");
pw.println("<td>"+id+"</td>");
pw.println("<td>"+name+"</td>");
pw.println("<td>"+category+"</td>");
pw.println("<td>"+qty+"</td>");

if(qty < min){
pw.println("<td class='text-danger fw-bold'>LOW STOCK</td>");
}else{
pw.println("<td class='text-success fw-bold'>Available</td>");
}

pw.println("</tr>");
}

con.close();

}catch(Exception e){
e.printStackTrace();
}

pw.println("</tbody>");
pw.println("</table>");

pw.println("</div>");

pw.println("</body>");
pw.println("</html>");
}
}
