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

	// 数据源表
	private final static String USER_TABLES = "USER_TABLES";
	//列
	private final static String USER_TAB_COLUMNS = "USER_TAB_COLUMNS";
	//表描述
	private final static String USER_TAB_COMMENTS = "USER_TAB_COMMENTS";
	//列描述
	private final static String USER_COL_COMMENTS = "USER_COL_COMMENTS";
	//索引
	private final static String USER_IND_COLUMNS = "USER_IND_COLUMNS";
	// 文件默认保存在当然操作系统用户目录下
	private final static String FILEPATH = System.getProperty("user.home");
	// 文件类型默认未pck
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
	public static void export(String tableName, String tableComment, ArrayList<String> queryColumnNames,
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

	public static void main(String[] args) throws Exception {
		// 获取表名
		ArrayList<String> queryColumnNames = new ArrayList<String>();
		queryColumnNames.add("TABLE_NAME");
		HashMap<String, String> conditions = new HashMap<String, String>(16);
		conditions.put("instr(TABLE_NAME,'$')", "0");
		conditions.put("STATUS", "VALID");
		long startTimeTableNames = System.currentTimeMillis();
		ArrayList<String> tableNames = TablesUtils.getTableNames(USER_TABLES, queryColumnNames, conditions);
		long endTimeTableNames = System.currentTimeMillis();
		float secondsTableNames = (endTimeTableNames - startTimeTableNames) / 1000F;
		System.out.println("查询TableNames耗时：" + Float.toString(secondsTableNames) + " second.");

		// 表描述
		ArrayList<String> queryColumnNames2 = new ArrayList<String>();
		queryColumnNames2.add("TABLE_NAME");
		queryColumnNames2.add("COMMENTS");
		HashMap<String, String> conditions2 = new HashMap<String, String>(16);
		conditions2.put("instr(TABLE_NAME,'$')", "0");
		conditions2.put("TABLE_TYPE", "TABLE");
		long startTimeTableComments = System.currentTimeMillis();
		HashMap<String, String> tableComments = TablesUtils.getTableComments(USER_TAB_COMMENTS, queryColumnNames2,
				conditions2);
		long endTimeTableComments = System.currentTimeMillis();
		float secondsTableComments = (endTimeTableComments - startTimeTableComments) / 1000F;
		System.out.println("查询TableNames耗时：" + Float.toString(secondsTableComments) + " second.");
		
		// 创建表文件
		ArrayList<String> queryColumnNames1 = new ArrayList<String>();
		queryColumnNames1.add("TABLE_NAME");
		queryColumnNames1.add("COLUMN_NAME");
		queryColumnNames1.add("DATA_TYPE");
		queryColumnNames1.add("DATA_LENGTH");
		queryColumnNames1.add("NULLABLE");
		queryColumnNames1.add("COLUMN_ID");
		queryColumnNames1.add("DATA_DEFAULT");
		HashMap<String, String> conditions1 = new HashMap<String, String>(16);
		String tableComment = null;
		long startTimeTables = System.currentTimeMillis();
		for (String tn : tableNames) {
			conditions1.put("TABLE_NAME", tn);
			tableComment = tableComments.get(tn);
			TablesExport.export(USER_TAB_COLUMNS, tableComment, queryColumnNames1, conditions1, FILEPATH, FILETYPE);
		}
		long endTimeTables = System.currentTimeMillis();
		float secondsTables = (endTimeTables - startTimeTables) / 1000F;
		System.out.println("写入表sql文件，耗时：" + Float.toString(secondsTables) + " second.");

	}
}
