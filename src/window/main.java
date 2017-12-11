package window;

import java.sql.SQLException;
import java.util.Properties;

import com.mysql.jdbc.*;

public class main {
	public static void main(String[] args) throws SQLException {
		Driver driver = new com.mysql.jdbc.Driver();
		String url = "jdbc:mysql://123.207.39.128/Student";
		Properties info = new Properties();
		info.put("user", "root");
		info.put("password", "XIEhanyang033~!");
		java.sql.Connection conn = driver.connect(url, info);
		System.out.println("666");
	}
}
