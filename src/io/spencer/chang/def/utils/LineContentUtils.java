package io.spencer.chang.def.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 获取行对应的内容
 * 
 * @author Spencer
 *
 */
public class LineContentUtils {
	/**
	 * 获取行对应的内容
	 * 
	 * @param tableName
	 *            数据源表名称
	 * @param queryColumnName
	 *            查询字段名称
	 * @param conditions
	 *            条件
	 * @return
	 * @throws Exception
	 */
	public static ArrayList<String> getContents(String tableName, String queryColumnName,
			HashMap<String, String> conditions) throws Exception {
		ArrayList<String> contents = new ArrayList<String>();
		ArrayList<String> queryColumns = new ArrayList<String>();
		queryColumns.add(queryColumnName);
		StringBuffer sql = SqlUtils.generateSql(tableName, queryColumns,
				conditions);
		//System.out.println(sql);
		Connection conn = DbUtils.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			contents.add(rs.getString(1));
		}
		DbUtils.close(conn, ps, rs);
		return contents;
	}

}
