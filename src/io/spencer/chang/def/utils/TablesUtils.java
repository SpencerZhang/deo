package io.spencer.chang.def.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 获取tables名称
 * 
 * @author Spencer
 *
 */
public class TablesUtils {
	/**
	 * 获取表名
	 * 
	 * @param tableName
	 * @param queryColumnNames
	 * @param conditions
	 * @return
	 * @throws Exception
	 */
	public static ArrayList<String> getTableNames(String tableName, ArrayList<String> queryColumnNames,
			HashMap<String, String> conditions) throws Exception {
		ArrayList<String> tableNames = new ArrayList<String>();
		// 组建sql
		StringBuffer sql = SqlUtils.generateSql(tableName, queryColumnNames, conditions);
		Connection conn = DbUtils.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			tableNames.add(rs.getString(1));
		}
		return tableNames;
	}

	/**
	 * 获取用户所有表的描述
	 * 
	 * @param tableName
	 * @param queryColumnNames
	 * @param conditions
	 * @return
	 * @throws Exception
	 */
	public static HashMap<String, String> getTableComments(String tableName, ArrayList<String> queryColumnNames,
			HashMap<String, String> conditions) throws Exception {
		HashMap<String, String> comments = new HashMap<String, String>(16);
		// 组建sql
		StringBuffer sql = SqlUtils.generateSql(tableName, queryColumnNames, conditions);
		Connection conn = DbUtils.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			comments.put("TABLE_NAME", rs.getString(1));
			comments.put("COMMENTS", rs.getString(2));
		}
		DbUtils.close(conn, ps, rs);
		return comments;
	}

	public static void main(String[] args) throws Exception {
		 ArrayList<String> queryColumnNames = new ArrayList<String>();
		 queryColumnNames.add("TABLE_NAME");
		 HashMap<String, String> conditions = new HashMap<String, String>(16);
		 conditions.put("instr(TABLE_NAME,'$')", "0");
		 conditions.put("STATUS", "VALID");
		 System.out.println(getTableNames("USER_TABLES", queryColumnNames,
		 conditions));

		ArrayList<String> queryColumnNames1 = new ArrayList<String>();
		queryColumnNames1.add("TABLE_NAME");
		queryColumnNames1.add("COMMENTS");
		HashMap<String, String> conditions1 = new HashMap<String, String>(16);
		conditions1.put("instr(TABLE_NAME,'$')", "0");
		conditions1.put("TABLE_TYPE", "TABLE");
		getTableComments("USER_TAB_COMMENTS", queryColumnNames1, conditions1);
	}
}
