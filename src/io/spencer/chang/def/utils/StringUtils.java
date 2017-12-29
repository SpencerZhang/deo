package io.spencer.chang.def.utils;
/**
 * String 工具类
 * @author Spencer
 *
 */
public class StringUtils {
	/**
	 * 把参数转换成1,2,格式String
	 * @param lines
	 * @return
	 */
	public static String covert2String(int lines) {
		StringBuffer lineArr = new StringBuffer();
		for (int i = 1; i <= lines; i++) {
			if (i == lines)
				lineArr.append(i);
			else
				lineArr.append(i + ",");
		}
		return lineArr.toString();
	}
}
