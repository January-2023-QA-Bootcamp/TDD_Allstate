package misc;

import org.testng.annotations.Test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import util.data.JDBCUtil;

public class JDBCTest {

	/*
	 * Add JDBC Library in pom.xml
	 * Register JDBC Driver (Postgresql, Oracle, MySQL) ("org.postgresql.Driver")
	 * Connection object = DB url (where your db hosting, username, password)
	 * Statement object can create from connection object
	 * statement object can send a query to db
	 * ResultSet will return from statement object
	 * Close it
	 */
	
	@Test
	public void test() throws ClassNotFoundException, SQLException {
		Object [][] arr = testJDBC();
		System.out.println(arr.length);
		for(Object[] obj: arr) {
			System.out.println(obj[0]);
		}
	}
	
	@Test
	public static Object[][] testJDBC() throws ClassNotFoundException, SQLException {
		Object[][] objects;
		Class.forName("org.postgresql.Driver");
		Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/employees_db", "postgres", "hr");
		Statement statement = connection.createStatement();
		statement.executeQuery("select count(*) from countries;");
		ResultSet resultSet = statement.getResultSet();
		resultSet.next();
		int rows = resultSet.getInt(1);
		System.out.println("Row count : "+rows);
		
		statement.executeQuery("select * from countries;");
		resultSet = statement.getResultSet();
		ResultSetMetaData metaData = resultSet.getMetaData();
		int length = metaData.getColumnCount();
		
		objects = new Object[rows][length];

		int row = 0;
		while(resultSet.next()) {
			for(int i = 0; i < length; i++) {
				objects[row][i] = resultSet.getString(i+1);
			}
			row++;
			//System.out.println(resultSet.getString(1));
		}
		connection.close();
		return objects;
	}
	
	@Test
	public void createTableTest() throws ClassNotFoundException, SQLException {
		createTable();
	}
	
	public static void createTable() throws ClassNotFoundException, SQLException {
		Class.forName("org.postgresql.Driver");
		Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/employees_db", "postgres", "hr");
		Statement statement = connection.createStatement();
		statement.executeQuery("create table autoData(quoteTitle varchar(255), "
				+ "zipCode varchar(255),"
				+ "infoTitle varchar(255),"
				+ "liab5Yrs varchar(255),"
				+ "ownOrRent varchar(255),"
				+ "emailErrorMsg varchar(255),"
				+ "email varchar(255),"
				+ "driverTtitle varchar(255))");
		//statement.executeQuery("select * from autodata");
	}
	
	@Test
	public void insertData() throws ClassNotFoundException, SQLException {
		insertingDataIntoAutoData();
	}
	
	public static void insertingDataIntoAutoData() throws ClassNotFoundException, SQLException {
		Class.forName("org.postgresql.Driver");
		Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/employees_db", "postgres", "hr");
		Statement statement = connection.createStatement();
		statement.executeQuery("insert into autodata (quoteTitle,zipCode,infoTitle,liab5Yrs,ownOrRent,emailErrorMsg,email,driverTtitle)\n"
				+ "values('Car insurance with flexible payment options.', '54111', 'General Info', 'No','Rent', 'Please provide a valid email address.', 'john3.doe@test.com', 'Driver 1 (Named Insured) Information')\n"
				+ "");
//				+ " insert into autodata(quoteTitle,zipCode,infoTitle,liab5Yrs,ownOrRent,emailErrorMsg,email,driverTtitle) "
//				+ "values(\"Car insurance with flexible payment options.\", \"54112\", \"General Info\", \"No\",\n"
//				+ "			\"Rent\", \"Please provide a valid email address.\", \"john2.doe@test.com\", \"Driver 1 (Named Insured) Information\")"
//				+ " insert into autodata(quoteTitle,zipCode,infoTitle,liab5Yrs,ownOrRent,emailErrorMsg,email,driverTtitle) "
//				+ "values(\"Car insurance with flexible payment options.\", \"54111\", \"General Info\", \"No\",\n"
//				+ "			\"Rent\", \"Please provide a valid email address.\", \"john3.doe@test.com\", \"Driver 1 (Named Insured) Information\")");
	}
	
	@Test
	public void jdbcMisc() throws ClassNotFoundException, SQLException {
		Class.forName("org.postgresql.Driver");
		Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/employees_db", "postgres", "hr");
		Statement statement = connection.createStatement();
		ResultSet set = statement.executeQuery("select * from autodata");
		ResultSetMetaData metaData = set.getMetaData();
		int length = metaData.getColumnCount();
		while(set.next()) {
			for(int i = 1; i < length; i++) {
				System.out.print(set.getString(i)+",");
			}
			System.out.println();
		}
	}
	
	@Test
	public void unitTestJDBCClass() {
		Object[][] objects = JDBCUtil.getData();
		for(Object[] arr:objects) {
			System.out.println(arr[1]);
		}
	}
}
