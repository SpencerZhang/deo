package io.spencer.chang.def.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
/**
 * pkg name + type 查出最大行数
 * @author Spencer
 *
 */
public class MaxLineUtils {
	/**
	 * 获取pkg总行数
	 * @param tableName 数据源表名称
	 * @param queryColumnName 查询字段名称
	 * @param conditions 条件
	 * @return
	 * @throws Exception
	 */
	public static Integer getMaxLine(String tableName, String maxName, HashMap<String, String> conditions)
			throws Exception {
		int lines = 0;
		HashMap<String, String> functionQueryColumns = new HashMap<String, String>(16);
		functionQueryColumns.put("MAX", maxName);
		StringBuffer sql = SQLUtils.generateSql(tableName, functionQueryColumns,
				null, conditions);
		 //System.out.println(sql);
		Connection conn = DBUtils.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			lines = rs.getInt(1);
		}
		DBUtils.close(conn, ps, rs);
		return lines;
	}
}
