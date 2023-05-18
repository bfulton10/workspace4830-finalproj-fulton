

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MyServletDB")
public class MyServletDB extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	 String dns="ec2-3-144-192-161.us-east-2.compute.amazonaws.com";
	 String user = "bede";
	 String password = "bedem777";
    Connection connection = null;
	 Statement statement = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyServletDB() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sql;
        Connection connection = null;
        PreparedStatement statement1 = null;
        ResultSet rs = null;
        String keyword = request.getParameter("keyword");
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        String title = "Restaurant Catalogue";
        String docType =
                "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";

        out.println(docType + //
            "<html>\n" + //
            "<head>" + 
                "<meta charset=\"UTF-8\">" + 
                "<title>" + title + "</title>" + 
                "<style>" + 
                "body {" + 
                    "background-color: white;" + 
                    "font-family: Arial, sans-serif;" + 
                    "text-align: center;" + 
                "}" + 
                "h1 {" + 
                    "color: #222222;" + 
                    "margin-bottom: 20px;" + 
                "}" + 
                "p {" + 
                    "color: #666666;" + 
                    "font-size: 14px;" + 
                    "margin-bottom: 20px;" + 
                "}" + 
                "ul {" + 
                    "list-style-type: none;" + 
                    "padding: 0;" + 
                "}" + 
                "li {" + 
                    "display: inline-block;" + 
                    "margin: 0 10px;" + 
                "}" + 
                "a {" + 
                    "text-decoration: none;" + 
                    "color: #0077c9;" + 
                    "font-weight: bold;" + 
                    "background-color: #dddddf;" + 
                    "padding: 10px 20px;" + 
                    "border-radius: 4px;" + 
                    "margin-bottom: 30px;" +
                "}" + 
                "form {" + 
                    "border: 1px solid #dddddd;" + 
                    "padding: 20px;" + 
                    "border-radius: 4px;" + 
                "}" + 
                "form p {" + 
                    "margin-bottom: 10px;" + 
                "}" + 
                "form input[type=\"text\"], form input[type=\"number\"] {" + 
                    "padding: 10px;" + 
                    "width: 200px;" + 
                    "border: 1px solid #dddddd;" + 
                    "border-radius: 4px;" + 
                "}" + 
                "form select {" + 
                    "padding: 10px;" + 
                    "border: 1px solid #dddddd;" + 
                    "border-radius: 4px;" + 
                "}" + 
                "form input[type=\"submit\"] {" + 
                    "background-color: #0077c9;" + 
                    "color: white;" + 
                    "padding: 10px 20px;" + 
                    "font-weight: bold;" + 
                    "border: none;" + 
                    "border-radius: 4px;" + 
                "}" +
                ".button {" +
                      	"text-decoration: none;" +
                        "background-color: #0077c9;" +
                        "color: white;" + 
                        "padding: 10px 20px;" +
                        "font-weight: bold;" +
                        "border: none;" +
                        "border-radius: 4px;" +
                      "}" +
                "</style>" +
            "</head>\n" + //
            "<body bgcolor = \"white\">\n" + //
            "<h1 align = \"center\">" + title + "</h1>\n" + //
            "<a href=\"RestaurantHome.html\" class=\"button\">HOME</a>" +
            "<spacer type=\"vertical\" width=\"100\" height=\"100\" size=\"100\"></spacer>");


        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your MySQL JDBC Driver?");
            e.printStackTrace();
            return;
        }

        try {
        	connection = DriverManager.getConnection("jdbc:mysql://"+ dns+":3306/myDBASE", user, password);
        } catch (SQLException e2) {
            // TODO Auto-generated catch block
            System.out.println("Connection Failed!:\n" + e2.getMessage());
            
        }

        sql = "SELECT * FROM myTable";
        try {

            statement1 = connection.prepareStatement(sql);     
        } catch (SQLException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }

        try {

            rs = statement1.executeQuery();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        out.println("<table align=\"center\" border=1 width=80% height=30% style=\"margin: 20px 0;\">");
        out.println("<tr><th>ID</th><th>Restaurant Name</th><th>Description</th><th>Address</th><th>Cuisine</th><th>Contact</th><th>Rating</th></tr>");
        try {
            while (rs.next()) {
                //Retrieve by column name
                String id = rs.getString("ID");
                String identification = rs.getString("IDENTIFICATION");
				String description = rs.getString("DESCRIPTION");
				String address = rs.getString("ADDRESS");
				String cuisine = rs.getString("CUISINE");
				String contact = rs.getString("CONTACT");
				int rating = rs.getInt("RATING");
                out.println("<tr><td>" + id + "</td><td>" + identification + "</td><td>" + description + "</td><td>" + address + "</td><td>" + cuisine + "</td><td>" + contact + "</td><td>" + rating + "</td></tr>");
            }
            out.println("</body></html>");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
