package io.spencer.chang.def.utils;

import java.io.File;

/**
 * 
 * @author Spencer
 *
 */
public class DirectoryUtils {
	/**
	 * 文件默认保存在当然操作系统用户目录下
	 */
	private final static String FILEPATH = System.getProperty("user.home");
	/**
	 * packages
	 */
	private final static String TYPE_PACKAGE = "packages";
	/**
	 * tables
	 */
	private final static String TYPE_TABLE = "tables";
	
	public static String createNestedFolders(String type) {
		String directoryPath = FILEPATH;
		if(type == TYPE_PACKAGE) {
		 directoryPath = directoryPath + "/deoExport/packages/";
		}else if(type == TYPE_TABLE) {
			directoryPath = directoryPath + "/deoExport/tables/";
		}
		File file = new File(directoryPath);
		if (!file.canWrite()) {
			if (!(file.exists() && file.isDirectory())) {
				if (file.mkdirs()) {
					System.out.println("Success ! Folders created.");
					return file.getAbsolutePath();
				} else {
					throw new Error("Failure ! Folders not created.");
				}
			}
		} else {
			throw new Error("Permission denied!");
		}
		return null;
	}
}
