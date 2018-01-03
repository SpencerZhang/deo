package io.spencer.chang.def.pojo;

import java.io.Serializable;
/**
 * 列描述
 * @author Spencer
 *
 */
public class ColumnComments implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1422290598458014369L;
	/**
	 * 表名
	 */
	public String tableName;
	/**
	 * 列名
	 */
	public String columnName;
	/**
	 * 列描述
	 */
	public String comments;
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((columnName == null) ? 0 : columnName.hashCode());
		result = prime * result + ((comments == null) ? 0 : comments.hashCode());
		result = prime * result + ((tableName == null) ? 0 : tableName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;}
		if (obj == null) {
			return false;}
		if (getClass() != obj.getClass()) {
			return false;}
		ColumnComments other = (ColumnComments) obj;
		if (columnName == null) {
			if (other.columnName != null) {
				return false;}
		} else if (!columnName.equals(other.columnName)) {
			return false;}
		if (comments == null) {
			if (other.comments != null) {
				return false;}
		} else if (!comments.equals(other.comments)) {
			return false;}
		if (tableName == null) {
			if (other.tableName != null) {
				return false;}
		} else if (!tableName.equals(other.tableName)) {
			return false;}
		return true;
	}
	@Override
	public String toString() {
		return "ColumnComments [tableName=" + tableName + ", columnName=" + columnName + ", comments=" + comments
				+ "]";
	}
}
