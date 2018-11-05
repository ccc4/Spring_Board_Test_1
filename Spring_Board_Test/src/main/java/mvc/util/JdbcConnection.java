package mvc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnection {
	public static Connection getConnection() {
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:simple_board");
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
		return conn;
	}
}
