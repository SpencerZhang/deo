package io.spencer.chang.def.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import io.spencer.chang.def.pojo.Column;
import io.spencer.chang.def.pojo.ColumnComments;

/**
 * 获取表对应的列
 * 
 * @author Spencer
 *
 */
public class TablesColumnsUtils {
	/**
	 * 查询列名称
	 * 
	 * @param tableName
	 *            数据源表名称
	 * @param queryColumnName
	 *            查询字段名称
	 * @param conditions
	 *            条件
	 * @return 返回ArrayList<String>集合
	 * @throws Exception
	 */
	public static ArrayList<Column> getColumnNames(String tableName, ArrayList<String> queryColumnNames,
			HashMap<String, String> conditions) throws Exception {
		ArrayList<Column> columns = new ArrayList<Column>();
		// 组建sql
		StringBuffer sql = SqlUtils.generateSql(tableName, queryColumnNames, conditions);
		//System.out.println(sql);
		Connection conn = DbUtils.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Column column = new Column();
			column.setTable_name(rs.getString(1));
			column.setColumn_name(rs.getString(2));
			column.setData_type(rs.getString(3));
			column.setData_length(rs.getInt(4));
			column.setNullable(rs.getString(5));
			column.setColumn_id(rs.getInt(6));
			column.setData_default(rs.getString(7));
			columns.add(column);
		}
		DbUtils.close(conn, ps, rs);
		return columns;
	}

	public static ArrayList<ColumnComments> getColumnComments(String tableName, ArrayList<String> queryColumnNames,
			HashMap<String, String> conditions) throws Exception {
		ArrayList<ColumnComments> comments = new ArrayList<ColumnComments>();
		// 组建sql
		StringBuffer sql = SqlUtils.generateSql(tableName, queryColumnNames, conditions);
		//System.out.println(sql);
		Connection conn = DbUtils.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			ColumnComments comment = new ColumnComments();
			comment.setTable_name(rs.getString(1));
			comment.setColumn_name(rs.getString(2));
			comment.setComments(rs.getString(3));
			comments.add(comment);
		}
		DbUtils.close(conn, ps, rs);
		return comments;
	}
}
