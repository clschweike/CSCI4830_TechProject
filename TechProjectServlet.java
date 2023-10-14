
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import util.UtilFile;

// TODO: add hibernate.cfg.xml back in? 


/**
 * Servlet implementation class TechProjectServlet
 */
@WebServlet("/TechProjectServlet")
public class TechProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static String url = "jdbc:mysql://ec2-18-222-150-58.us-east-2.compute.amazonaws.com:3306/MyDBTechProject";
	static String user = "newmysqlremoteuser";
	static String password = "password123";
	static Connection connection = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TechProjectServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String filename = "/WEB-INF/input2.csv";
		List<String> contents = UtilFile.readFile(getServletContext(), filename);
		response.getWriter().println("-------- MySQL JDBC Connection Testing ------------<br>");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); //old:com.mysql.jdbc.Driver
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MYSQL JDBC Driver?");
			e.printStackTrace();
			return;
		}
		response.getWriter().println("MySQL JDBC Driver Registered!<br>");
		connection = null;
		try {
			connection = DriverManager.getConnection(url, user, password);
		}	catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
		}
		if (connection != null) {
			response.getWriter().println("You made it, take control your database now!<br>");
		} else {
			System.out.println("Failed to make connection!");
		}
		
		
		
		// Insert data
		for (String line: contents) {
			String[] separated = line.split(",");
			if (separated.length == 4) {
				String prodName = separated[0].trim();
				String prodSize = separated[1].trim();
				String prodTime = separated[2].trim();
				String prodCost = separated[3].trim();
				
				String insertSQL = "INSERT INTO MyTableTechProject VALUES (PROD_NAME, PROD_SIZE, PROD_TIME, PROD_COST) VALUES (?, ?, ?, ?)";
			
				try {
						PreparedStatement prepStmt = connection.prepareStatement(insertSQL);
						prepStmt.setString(1, prodName);
						prepStmt.setString(2, prodSize);
						prepStmt.setString(3, prodTime);
						prepStmt.setString(4, prodCost);
						
						prepStmt.execute();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			else
			// separated.length != 4
			{
				response.getWriter().append("¡ERROR! ¿Qué pasó?");
			}
		}
		
			
			
		try {
			// Display data
			String selectSQL = "SELECT * FROM MyTableTechProject";
			response.getWriter().println(selectSQL + "<br>");
			response.getWriter().println("------------------------------------------<br>");
			PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				String id = rs.getString("ID");
	            String pname = rs.getString("PROD_NAME");
	            String psize = rs.getString("PROD_SIZE");
	            String ptime = rs.getString("PROD_TIME");
	            String pcost = rs.getString("PROD_COST");
	            response.getWriter().append("PRODUCT ID: " + id + ", ");
	            response.getWriter().append("PRODUCT NAME: " + pname + ", ");
	            response.getWriter().append("PRODUCT SIZE: " + psize + ", ");
	            response.getWriter().append("PRODUCT TIME: " + ptime + ", ");
	            response.getWriter().append("PRODUCT COST: " + pcost + "<br>");
			}
			
			// print line by line
			for (int i = 0; i < 3; i++) {
				response.getWriter().print(contents.get(i) + "<br>");
			}
			
        } catch (SQLException e) {
        	e.printStackTrace();
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
