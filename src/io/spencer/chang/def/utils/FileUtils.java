package io.spencer.chang.def.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import io.spencer.chang.def.pojo.Column;
import io.spencer.chang.def.pojo.ColumnComments;
import io.spencer.chang.def.pojo.TableIndex;

/**
 * 写入文件
 * 
 * @author Spencer
 *
 */
public class FileUtils {
	/**
	 * 创建脚本头部内容
	 * 
	 * @param file
	 * @param tableName
	 */
	public static void createFileHeader(File file, String tableName) {
		StringBuffer fileHeader = new StringBuffer();
		fileHeader.append("set define off").append("\n").append("spool ").append(tableName).append(".log").append("\n")
				.append("\n").append("prompt").append("\n").append("prompt Creating table ").append(tableName)
				.append("\n").append("prompt ================================").append("\n").append("prompt")
				.append("\n").append("\n").append("create table ").append(tableName).append("\n")
				.append(SqlUtils.LEFTPARENTHESE).append("\n");
		// System.out.println(fileHeader);
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new FileWriter(file));
			out.write(fileHeader.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 写入脚本列内容
	 * 
	 * @param file
	 * @param columns
	 */
	public static void writeFileColumns(File file, ArrayList<Column> columns) {
		StringBuffer fileColumns = new StringBuffer();
		for (Column column : columns) {
			fileColumns.append(column.getColumnName()).append(SqlUtils.SPACE).append(column.getDataType())
					.append(SqlUtils.SPACE);
			if (column.getDataType().matches("VARCHAR2")) {
				fileColumns.append(SqlUtils.LEFTPARENTHESE).append(column.getDataLength())
						.append(SqlUtils.RIGHTPARENTHESE).append(SqlUtils.SPACE);
			}
			if (column.getNullAble().matches("N")) {
				fileColumns.append(SqlUtils.NOTNULL);
			}
			fileColumns.append(SqlUtils.COMMA).append("\n");
		}
		fileColumns.append(SqlUtils.RIGHTPARENTHESE).append(";").append("\n");
		// System.out.println(fileColumns);
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));
			out.write(fileColumns.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 写入表的描述
	 * 
	 * @param file
	 * @param columns
	 */
	public static void writeFileTableComment(File file, String tableName, String tableComment) {
		StringBuffer fileTableComment = new StringBuffer();
		// is '应收发票类型';
		if (tableComment != null && tableComment != "") {
			fileTableComment.append("comment on table").append(SqlUtils.SPACE).append(tableName).append("\n")
					.append("is").append(SqlUtils.SPACE).append(tableComment);
		}
		// System.out.println(fileTableComment);
		if (fileTableComment != null) {
			BufferedWriter out = null;
			try {
				out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));
				out.write(fileTableComment.toString());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					out.flush();
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 写入脚本列描述
	 * 
	 * @param file
	 * @param columns
	 */
	public static void writeFileColumnsComments(File file, ArrayList<ColumnComments> columnComments) {
		StringBuffer fileColumnsComments = new StringBuffer();
		for (ColumnComments columnComment : columnComments) {
			if (columnComment.getComments() != "" && columnComment.getComments() != null) {
				fileColumnsComments.append("comment on column ").append(columnComment.getTableName()).append(".")
						.append(columnComment.getColumnName()).append("\n").append("is ")
						.append(columnComment.getComments()).append(";").append("\n");
			}
		}
		// System.out.println(fileColumnsComments);
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));
			out.write(fileColumnsComments.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 写入脚本索引
	 * 
	 * @param file
	 * @param columns
	 */
	public static void writeFileTableIndexs(File file, ArrayList<TableIndex> indexs) {
		// TODO
		StringBuffer fileTableIndexs = new StringBuffer();
		StringBuffer fileTableIndexsPK = new StringBuffer();
		StringBuffer fileTableIndexsU = new StringBuffer();
		for (TableIndex index : indexs) {
			System.out.println(index.getIndexName());
			System.out.println(index.getIndexName().endsWith("_PK"));
			if (index.getIndexName().endsWith("_PK")) {
				if (fileTableIndexsPK.toString().startsWith("alter table")) {
					fileTableIndexsPK.append(SqlUtils.COMMA).append(index.getColumnName());
				} else {
					fileTableIndexsPK.append("alter table").append(SqlUtils.SPACE).append(index.getTableName())
							.append("\n").append("add constraint").append(SqlUtils.SPACE).append(index.getIndexName())
							.append(SqlUtils.SPACE).append("PRIMARY KEY").append(SqlUtils.SPACE)
							.append(SqlUtils.LEFTPARENTHESE).append(index.getColumnName());
				}
			} else {
				// ACP_ACP_REQUISITION_REFS_N1 ACP_ACP_REQUISITION_REFS CSH_TRANSACTION_LINE_ID
				// ACP_ACP_REQUISITION_REFS_N2 ACP_ACP_REQUISITION_REFS ACP_REQUISITION_LINE_ID
				// ACP_ACP_REQUISITION_REFS_U1 ACP_ACP_REQUISITION_REFS WRITE_OFF_ID
				// ACP_ACP_REQUISITION_REFS_PK ACP_ACP_REQUISITION_REFS ACP_REQUISITION_REF_ID

				if (index.getIndexName() != null) {
					// 是否包含当前索引名称，包含就追加：",索引名"
					if (fileTableIndexsU.toString().contains(index.getIndexName())) {
						fileTableIndexsU.append(SqlUtils.COMMA).append(index.getColumnName());
					} else {
						// 判读索引字符串长度，大于0默认已经写入一个索引。追加：");换行符"
						if (fileTableIndexsU.length() > 0) {
							fileTableIndexsU.append(SqlUtils.RIGHTPARENTHESE).append(";").append("\n");
						}
						// 判读索引字符串长度，等于0默认第一次写入
						if (fileTableIndexsU.length() == 0) {
							fileTableIndexsU.append("create unique index").append(SqlUtils.SPACE)
									.append(index.getIndexName()).append(SqlUtils.SPACE).append("on")
									.append(SqlUtils.SPACE).append(index.getTableName()).append(SqlUtils.SPACE)
									.append(SqlUtils.LEFTPARENTHESE).append(index.getColumnName());
						} else {
							// 写入新的索引
							fileTableIndexsU.append("create unique index").append(SqlUtils.SPACE)
									.append(index.getIndexName()).append(SqlUtils.SPACE).append("on")
									.append(SqlUtils.SPACE).append(index.getTableName()).append(SqlUtils.SPACE)
									.append(SqlUtils.LEFTPARENTHESE).append(index.getColumnName());
						}
					}
				}
			}
		}
		// 写入结尾
		if (fileTableIndexsPK.length() > 0) {
			fileTableIndexsPK.append(SqlUtils.RIGHTPARENTHESE).append(";").append("\n");
		}
		if (fileTableIndexsU.length() > 0) {
			fileTableIndexsU.append(SqlUtils.RIGHTPARENTHESE).append(";").append("\n");
		}
		fileTableIndexs.append(fileTableIndexsPK).append(fileTableIndexsU);
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));
			out.write(fileTableIndexs.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 写入脚本底部内容
	 * 
	 * @param file
	 * @param tableName
	 */
	public static void writeFileFooter(File file) {
		StringBuffer fileFooter = new StringBuffer();
		fileFooter.append("\n").append("\n").append("spool off").append("\n");
		// System.out.println(fileFooter);
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));
			out.write(fileFooter.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 追加写入文件
	 * 
	 * @param file
	 * @param content
	 */
	public static void write(File file, String content) {
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));
			out.write(content);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 写入新文件
	 * 
	 * @param file
	 * @param content
	 */
	public static void writeNewFile(File file, String content) {
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new FileWriter(file));
			out.write(content);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
