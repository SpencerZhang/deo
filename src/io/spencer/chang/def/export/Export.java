package io.spencer.chang.def.export;

import io.spencer.chang.def.core.PackagesExport;
import io.spencer.chang.def.core.TablesExport;
import io.spencer.chang.def.utils.DirectoryUtils;

/**
 * 导出类
 * 
 * @author Spencer
 *
 */
public class Export {

	public static void main(String[] args) {
		//创建目录
		String packagePath;
		String tablePath;
		try {
			packagePath = DirectoryUtils.createNestedFolders("packages");
			tablePath = DirectoryUtils.createNestedFolders("tables");
			// package导出
			PackagesExport packagesExport = new PackagesExport();
			packagesExport.export(packagePath);
			// table导出
			TablesExport tableExport = new TablesExport();
			tableExport.export(tablePath);
		} catch (Error e) {
			e.printStackTrace();
		}
	}

}
