package io.spencer.chang.def.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import io.spencer.chang.def.pojo.TableIndex;

/**
 * 表的索引工具类
 * @author Spencer
 *
 */
public class TableIndexsUtils {
	/**
	 * 
	 * @param tableName
	 * @param queryColumnNames
	 * @param conditions
	 * @return
	 * @throws Exception
	 */
	public static ArrayList<TableIndex> getTableIndexs(String tableName, ArrayList<String> queryColumnNames,
			HashMap<String, String> conditions) throws Exception {
		ArrayList<TableIndex> indexs = new ArrayList<TableIndex>();
		// 组建sql
		StringBuffer sql = SqlUtils.generateSql(tableName, queryColumnNames, conditions);
		//System.out.println(sql);
		Connection conn = DbUtils.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			TableIndex index = new TableIndex();
			index.setTable_name(rs.getString(1));
			index.setColumn_name(rs.getString(2));
			index.setIndex_name(rs.getString(3));
			indexs.add(index);
		}
		DbUtils.close(conn, ps, rs);
		return indexs;
	}
}
