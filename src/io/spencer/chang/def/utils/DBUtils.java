package io.spencer.chang.def.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 获取数据源
 * 
 * @author Spencer
 *
 */
public class DBUtils {
	// 数据库的用户名
	private static String USERNAME;

	// 数据库的密码
	private static String PASSWORD;

	// 数据库的驱动信息
	private static String DRIVER;

	// 访问数据库的地址
	private static String URL;

	static {
		try {
			InputStream inStream = DBUtils.class.getResourceAsStream("./jdbc.properties");
			Properties prop = new Properties();
			prop.load(inStream);
			USERNAME = prop.getProperty("jdbc.username");
			PASSWORD = prop.getProperty("jdbc.password");
			DRIVER = prop.getProperty("jdbc.driver");
			URL = prop.getProperty("jdbc.url");
		} catch (Exception e) {
			throw new RuntimeException("读取数据库配置文件异常！", e);
		}
	}

	/**
	 * 获取数据库链接
	 * 
	 * @return
	 */
	public static Connection getConnection() {

		Connection conn = null;
		// 建立连接
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void close(Connection conn, PreparedStatement ps, ResultSet rs) {
		try {
			conn.close();
			ps.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		getConnection();
	}
}
