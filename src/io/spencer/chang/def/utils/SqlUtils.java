package io.spencer.chang.def.utils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * sql 工具类
 * 
 * @author Spencer
 *
 */
public class SqlUtils {
	public final static String SELECT = "SELECT";
	public final static String UPDATE = "UPDATE";
	public final static String DELETE = "DELETE";
	public final static String FROM = "FROM";
	public final static String DUAL = "DUAL";
	public final static String WHERE = "WHERE";
	public final static String AND = "AND";
	public final static String LIKE = "LIKE";
	public final static String IN = "IN";
	public final static String EXISTS = "EXISTS";
	public final static String NOTEXISTS = "NOT EXISTS";
	public final static String ISNULL = "IS NULL";
	public final static String ISNOTNULL = "IS NOT NULL";
	public final static String NOTNULL = "NOT NULL";
	public final static String BETWEEN = "BETWEEN";
	public final static String SPACE = " ";
	public final static String THREEONE = "1 = 1";
	public final static String INTO = "INTO";
	public final static String DISTINCT = "DISTINCT";
	public final static String MAX = "MAX";
	public final static String COUNT = "COUNT";
	public final static String SUM = "SUM";
	public final static String NVL = "NVL";
	public final static String ESCAPECHARACTER = "\'";
	public final static String LEFTPARENTHESE = "(";
	public final static String RIGHTPARENTHESE = ")";
	public final static String EQ = "=";
	public final static String GT = ">";
	public final static String LT = "<";
	public final static String COMMA = ",";

	public static StringBuffer generateSql(String tableName, ArrayList<String> queryColumns,
			HashMap<String, String> conditions) {
		StringBuffer sql = new StringBuffer();
		sql.append(SqlUtils.SELECT).append(SqlUtils.SPACE);
		if (queryColumns != null) {
			queryColumns.forEach((qn) -> {
				sql.append(qn).append(SqlUtils.COMMA).append(SqlUtils.SPACE);
			});
		}
		// 先删除最后一个列的逗号
		sql.delete(sql.length() - 2, sql.length() - 1).append(SqlUtils.FROM).append(SqlUtils.SPACE).append(tableName)
				.append(SqlUtils.SPACE).append(SqlUtils.WHERE).append(SqlUtils.SPACE).append(SqlUtils.THREEONE)
				.append(SqlUtils.SPACE);
		if (conditions != null) {
			conditions.forEach((k, v) -> {
				sql.append(SqlUtils.AND).append(SqlUtils.SPACE).append(k).append(SqlUtils.SPACE).append(SqlUtils.EQ)
						.append(SqlUtils.SPACE).append(SqlUtils.ESCAPECHARACTER).append(v)
						.append(SqlUtils.ESCAPECHARACTER).append(SqlUtils.SPACE);
			});
		}
		return sql;
	}

	public static StringBuffer generateSql(String tableName, HashMap<String, String> functionQueryColumns,
			ArrayList<String> queryColumns, HashMap<String, String> conditions) {
		StringBuffer sql = new StringBuffer();
		sql.append(SqlUtils.SELECT).append(SqlUtils.SPACE);

		if (functionQueryColumns != null) {
			functionQueryColumns.forEach((k, v) -> {
				sql.append(k).append(SqlUtils.LEFTPARENTHESE).append(v).append(SqlUtils.RIGHTPARENTHESE)
						.append(SqlUtils.COMMA).append(SqlUtils.SPACE);
			});
		}
		if (queryColumns != null) {
			queryColumns.forEach((qn) -> {
				sql.append(qn).append(SqlUtils.COMMA).append(SqlUtils.SPACE);
			});
		}
		// 先删除最后一个列的逗号
		sql.delete(sql.length() - 2, sql.length() - 1).append(SqlUtils.FROM).append(SqlUtils.SPACE).append(tableName)
				.append(SqlUtils.SPACE).append(SqlUtils.WHERE).append(SqlUtils.SPACE).append(SqlUtils.THREEONE)
				.append(SqlUtils.SPACE);
		if (conditions != null) {
			conditions.forEach((k, v) -> {
				sql.append(SqlUtils.AND).append(SqlUtils.SPACE).append(k).append(SqlUtils.SPACE).append(SqlUtils.EQ)
						.append(SqlUtils.SPACE).append(SqlUtils.ESCAPECHARACTER).append(v)
						.append(SqlUtils.ESCAPECHARACTER).append(SqlUtils.SPACE);
			});
		}
		return sql;
	}
}
