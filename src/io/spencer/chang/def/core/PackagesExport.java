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
	private final static String SOURCE_TABLE = "ALL_SOURCE";
	/**
	 * 导出类型
	 */
	private final static String TYPE_PACKAGE_HEADER = "PACKAGE";
	/**
	 * 导出类型
	 */
	private final static String TYPE_PACKAGE_BODY = "PACKAGE BODY";
	/**
	 * 文件默认保存在当然操作系统用户目录下
	 */
	private final static String FILEPATH = System.getProperty("user.home");
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
	public static void export(ArrayList<String> packageNames, String type, String owner, String tableName,
			String filePath, String fileType) {
		//final String QUERY_MAX_NAME = "LINE";
		final String queryContentName = "TEXT";
		try {
			packageNames.forEach((s) -> {
				HashMap<String, String> maxLineConditions = new HashMap<String, String>(16);
				maxLineConditions.put("TYPE", type);
				maxLineConditions.put("OWNER", owner);
				maxLineConditions.put("NAME", s);
				
				try {
					String fileFullPath = filePath + "/" + s + fileType;
					File file = new File(fileFullPath);
					HashMap<String, String> contentConditions = new HashMap<String, String>(16);
					contentConditions.put("TYPE", type);
					contentConditions.put("OWNER", owner);
					contentConditions.put("NAME", s);
					ArrayList<String> contents = LineContentUtils.getContents(tableName, queryContentName,
							contentConditions);
					contents.forEach((c) -> {
						String content = null;
						if (type == TYPE_PACKAGE_HEADER) {
							content = c.replace("PACKAGE", "CREATE OR REPLACE PACKAGE");
						}else if (type == TYPE_PACKAGE_BODY) {
							content = c.replace("PACKAGE BODY", "CREATE OR REPLACE PACKAGE BODY");
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

	public static void main(String[] args) {
		final String queryName = "NAME";
		HashMap<String, String> conditions = new HashMap<String, String>(16);
		//数据库用户名
		conditions.put("OWNER", "数据库用户名");
		conditions.put("TYPE", TYPE_PACKAGE_HEADER);
		ArrayList<String> packageNames;
		try {
			long startTimePackageNames = System.currentTimeMillis();
			packageNames = PackagesUtils.getPackageNames(SOURCE_TABLE, queryName, conditions);
			long endTimePackageNames = System.currentTimeMillis();
			float secondsPackageNames = (endTimePackageNames - startTimePackageNames) / 1000F;
			System.out.println("查询PackageNames耗时：" + Float.toString(secondsPackageNames) + " second.");

			long startTimePackage = System.currentTimeMillis();
			//数据库用户名
			PackagesExport.export(packageNames, TYPE_PACKAGE_HEADER, "数据库用户名", SOURCE_TABLE, FILEPATH,
					FILETYPE);
			long endTimePackage = System.currentTimeMillis();
			float secondsPackage = (endTimePackage - startTimePackage) / 1000F;
			System.out.println("写入Package耗时：" + Float.toString(secondsPackage) + " second.");

			long startTimePackageBody = System.currentTimeMillis();
			//数据库用户名
			PackagesExport.export(packageNames, TYPE_PACKAGE_BODY, "数据库用户名", SOURCE_TABLE, FILEPATH, FILETYPE);
			long endTimePackageBody = System.currentTimeMillis();
			float secondsPackageBody = (endTimePackageBody - startTimePackageBody) / 1000F;
			System.out.println("写入PackageBody耗时：" + Float.toString(secondsPackageBody) + " second.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
