package mvc.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JdbcCloser {
	
	public static void close(Connection obj) {
		try {
			if(obj != null) obj.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void close(PreparedStatement obj) {
		try {
			if(obj != null) obj.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet obj) {
		try {
			if(obj != null) obj.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
