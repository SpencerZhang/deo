package io.spencer.chang.def.export;

import io.spencer.chang.def.core.PackagesExport;
import io.spencer.chang.def.core.TablesExport;

/**
 * 导出类
 * 
 * @author Spencer
 *
 */
public class Export {

	public static void main(String[] args) {
		// package导出
		PackagesExport packagesExport = new PackagesExport();
		packagesExport.export();
		// table导出
		TablesExport tableExport = new TablesExport();
		tableExport.export();
	}

}
