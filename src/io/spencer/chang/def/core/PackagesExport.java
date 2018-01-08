package io.spencer.chang.def.core;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import io.spencer.chang.def.utils.FileUtils;
import io.spencer.chang.def.utils.LineContentUtils;
import io.spencer.chang.def.utils.PackagesUtils;

/**
 * pkg 导出
 * 
 * @author Spencer
 *
 */
public class PackagesExport {
	/**
	 * 数据源表
	 */
	private final static String SOURCE_TABLE = "USER_SOURCE";
	/**
	 * 导出类型
	 */
	private final static String TYPE_PACKAGE_HEADER = "PACKAGE";
	/**
	 * 导出类型
	 */
	private final static String TYPE_PACKAGE_BODY = "PACKAGE BODY";
	/**
	 * 文件类型默认pck
	 */
	private final static String FILETYPE = ".pck";

	/**
	 * 导出pkg
	 * 
	 * @param packageNames
	 *            pkg名称
	 * @param type
	 *            类型
	 * @param owner
	 *            拥有者
	 * @param tableName
	 *            数据源表
	 * @param filePath
	 *            文件路径
	 * @param fileType
	 *            文件类型
	 */
	private static void export(ArrayList<String> packageNames, String type, String tableName,
			String filePath, String fileType) {
		final String queryContentName = "TEXT";
		try {
			packageNames.forEach((s) -> {
				try {
					String fileFullPath = filePath + "/" + s + fileType;
					File file = new File(fileFullPath);
					HashMap<String, String> contentConditions = new HashMap<String, String>(16);
					contentConditions.put("TYPE", type);
					contentConditions.put("NAME", s);
					ArrayList<String> contents = LineContentUtils.getContents(tableName, queryContentName,
							contentConditions);
					contents.forEach((c) -> {
						String content = null;
						if (type == TYPE_PACKAGE_HEADER) {
							if (c.toUpperCase().startsWith(TYPE_PACKAGE_HEADER)) {
								content = c.toUpperCase().replace("PACKAGE", "CREATE OR REPLACE PACKAGE");
							} else {
								content = c;
							}

						} else if (type == TYPE_PACKAGE_BODY) {
							if (c.toUpperCase().startsWith(TYPE_PACKAGE_BODY)) {
								content = "\n" + "/" + "\n";
								content = content + c.toUpperCase().replace("PACKAGE BODY", "CREATE OR REPLACE PACKAGE BODY");
							} else {
								content = c;
							}
						}
						if (!file.exists()) {
							try {
								file.createNewFile();
							} catch (IOException e) {
								e.printStackTrace();
							}
							FileUtils.writeNewFile(file, content);
						} else {
							FileUtils.write(file, content);
						}
					});
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void export(String filePath) {
		final String queryName = "NAME";
		HashMap<String, String> conditions = new HashMap<String, String>(16);
		conditions.put("TYPE", TYPE_PACKAGE_HEADER);
		ArrayList<String> packageNames;
		try {
			//获取package names
			packageNames = PackagesUtils.getPackageNames(SOURCE_TABLE, queryName, conditions);
			//导出package header
			PackagesExport.export(packageNames, TYPE_PACKAGE_HEADER, SOURCE_TABLE, filePath,
					FILETYPE);
			//导出package body
			PackagesExport.export(packageNames, TYPE_PACKAGE_BODY, SOURCE_TABLE, filePath, FILETYPE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
