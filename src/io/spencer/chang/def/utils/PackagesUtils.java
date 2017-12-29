package io.spencer.chang.def.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
/**
 * 找出用户所有的pkg name
 * @author Spencer
 *
 */
public class PackagesUtils {
	/**
	 * 获取pkg名称
	 * @param tableName 数据源表名称
	 * @param queryColumnName 查询字段名称
	 * @param conditions 条件
	 * @return 返回ArrayList<String>集合
	 * @throws Exception
	 */
	public static ArrayList<String> getPackageNames(String tableName, String queryColumnName, HashMap<String, String> conditions)
			throws Exception {
		ArrayList<String> packageNames = new ArrayList<String>();
		HashMap<String, String> functionQueryColumns = new HashMap<String, String>(16);
		functionQueryColumns.put("DISTINCT", queryColumnName);
		StringBuffer sql = SQLUtils.generateSql(tableName, functionQueryColumns,
				null, conditions);
		// System.out.println(sql);
		Connection conn = DBUtils.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			packageNames.add(rs.getString(1));
		}
		DBUtils.close(conn, ps, rs);
		return packageNames;
	}
}
