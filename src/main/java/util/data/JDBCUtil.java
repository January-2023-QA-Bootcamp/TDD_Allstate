package util.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtil {

	public static Object[][] getData() {
		Object[][] objects = null;
		try {
		Class.forName("org.postgresql.Driver");
		Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/employees_db", "postgres", "hr");
		Statement statement = connection.createStatement();
		statement.executeQuery("select count(*) from autodata;");
		ResultSet resultSet = statement.getResultSet();
		resultSet.next();
		int rows = resultSet.getInt(1);
		//System.out.println(rows);
		statement.executeQuery("select * from autodata;");
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
		}
		connection.close();
		}catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return objects;
	}
}
