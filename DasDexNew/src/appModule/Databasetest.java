package appModule;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.annotations.Test;

public class Databasetest {

	@Test
	public void dbtesting(){
		try{
		// Load Microsoft SQL Server JDBC driver.
		Class.forName("com.mysql.jdbc.Driver");

		// Prepare connection url.
		String url = "jdbc:mysql://172.19.0.230:3306/dd_dev";
		//String url = "jdbc:mysql://localhost/employees";
		System.out.println(url+"this is url");
		// Get connection to DB.
		Connection con = DriverManager.getConnection(url, "dd_dev", "b0180d5c95153293c9d654974a8aaf94");
		//Connection con = DriverManager.getConnection(url, "root", "girdhari12");
		System.out.println(con+"this is connection");
		// Create statement object which would be used in writing DDL and DML
		// SQL statement.
		Statement stmt = con.createStatement();

		// Send SQL SELECT statements to the database via the Statement.executeQuery
		// method which returns the requested information as rows of data in a
		// ResultSet object.
		System.out.println("connection establish");
		ResultSet result =  stmt.executeQuery("select * from employee_details");
		
		while(result.next()){
			String firstname = result.getString("FName");
			long mobno = result.getLong("mobile_no");
			
			System.out.println("|First name : "+ firstname + "| mobile no. : "+ mobno);
		}
		result.close();
		stmt.close();
		con.close();
		
	}catch(Exception e){
		System.out.println(e);
	}
}
}
