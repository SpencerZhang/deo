package io.spencer.chang.def.core;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import io.spencer.chang.def.pojo.Column;
import io.spencer.chang.def.pojo.ColumnComments;
import io.spencer.chang.def.pojo.TableIndex;
import io.spencer.chang.def.utils.FileUtils;
import io.spencer.chang.def.utils.TableIndexsUtils;
import io.spencer.chang.def.utils.TablesColumnsUtils;
import io.spencer.chang.def.utils.TablesUtils;

/**
 * tables 导出
 * 
 * @author Spencer
 *
 */
public class TablesExport {

	/**
	 * 数据源表
	 */
	private final static String USER_TABLES = "USER_TABLES";
	/**
	 * 列
	 */
	private final static String USER_TAB_COLUMNS = "USER_TAB_COLUMNS";
	/**
	 * 表描述
	 */
	private final static String USER_TAB_COMMENTS = "USER_TAB_COMMENTS";
	/**
	 * 列描述
	 */
	private final static String USER_COL_COMMENTS = "USER_COL_COMMENTS";
	/**
	 * 索引
	 */
	private final static String USER_IND_COLUMNS = "USER_IND_COLUMNS";
	/**
	 * 文件默认保存在当然操作系统用户目录下
	 */
	private final static String FILEPATH = System.getProperty("user.home");
	/**
	 * 文件类型默认pck
	 */
	private final static String FILETYPE = ".sql";

	/**
	 * 
	 * @param tableName
	 *            数据源表
	 * @param queryColumnNames
	 *            列名
	 * @param conditions
	 *            表名
	 * @param filePath
	 * @param fileType
	 */
	private static void export(String tableName, String tableComment, ArrayList<String> queryColumnNames,
			HashMap<String, String> conditions, String filePath, String fileType) {
		String dataTableName = conditions.get("TABLE_NAME");
		String fileFullPath = filePath + "/" + dataTableName + fileType;
		File file = new File(fileFullPath);
		try {
			// 创建脚本头部内容
			FileUtils.createFileHeader(file, dataTableName);
			// 列
			ArrayList<Column> columns = TablesColumnsUtils.getColumnNames(tableName, queryColumnNames, conditions);
			// 写入脚本列内容
			FileUtils.writeFileColumns(file, columns);
			// 写入表描述
			FileUtils.writeFileTableComment(file, dataTableName, tableComment);
			// 列描述
			ArrayList<String> queryColumnNames1 = new ArrayList<String>();
			queryColumnNames1.add("TABLE_NAME");
			queryColumnNames1.add("COLUMN_NAME");
			queryColumnNames1.add("COMMENTS");

			ArrayList<ColumnComments> columnComments = TablesColumnsUtils.getColumnComments(USER_COL_COMMENTS,
					queryColumnNames1, conditions);

			// 写入列描述
			FileUtils.writeFileColumnsComments(file, columnComments);
			// 索引
			ArrayList<String> queryColumnNames2 = new ArrayList<String>();
			queryColumnNames2.add("TABLE_NAME");
			queryColumnNames2.add("COLUMN_NAME");
			queryColumnNames2.add("INDEX_NAME");
			ArrayList<TableIndex> indexs = TableIndexsUtils.getTableIndexs(USER_IND_COLUMNS, queryColumnNames2,
					conditions);
			// 写入索引
			FileUtils.writeFileTableIndexs(file, indexs);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void export() {
		// 获取表名
		ArrayList<String> queryColumnNames = new ArrayList<String>();
		queryColumnNames.add("TABLE_NAME");
		HashMap<String, String> conditions = new HashMap<String, String>(16);
		conditions.put("instr(TABLE_NAME,'$')", "0");
		conditions.put("STATUS", "VALID");
		try {
			ArrayList<String> tableNames = TablesUtils.getTableNames(USER_TABLES, queryColumnNames, conditions);
			
			// 获取所有表描述
			ArrayList<String> queryColumnCommentsNames = new ArrayList<String>();
			queryColumnCommentsNames.add("TABLE_NAME");
			queryColumnCommentsNames.add("COMMENTS");
			HashMap<String, String> conditionsColumnComments = new HashMap<String, String>(16);
			conditionsColumnComments.put("instr(TABLE_NAME,'$')", "0");
			conditionsColumnComments.put("TABLE_TYPE", "TABLE");
			HashMap<String, String> tableComments = TablesUtils.getTableComments(USER_TAB_COMMENTS, queryColumnCommentsNames,
					conditionsColumnComments);
			
			// 创建表文件
			ArrayList<String> queryTablesColumnNames = new ArrayList<String>();
			queryTablesColumnNames.add("TABLE_NAME");
			queryTablesColumnNames.add("COLUMN_NAME");
			queryTablesColumnNames.add("DATA_TYPE");
			queryTablesColumnNames.add("DATA_LENGTH");
			queryTablesColumnNames.add("NULLABLE");
			queryTablesColumnNames.add("COLUMN_ID");
			queryTablesColumnNames.add("DATA_DEFAULT");
			HashMap<String, String> conditionsTablesColumns = new HashMap<String, String>(16);
			String tableComment = null;
			for (String tn : tableNames) {
				conditionsTablesColumns.put("TABLE_NAME", tn);
				tableComment = tableComments.get(tn);
				TablesExport.export(USER_TAB_COLUMNS, tableComment, queryTablesColumnNames, conditionsTablesColumns, FILEPATH, FILETYPE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
