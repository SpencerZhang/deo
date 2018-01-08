package io.spencer.chang.def.pojo;

import java.io.Serializable;
/**
 * 索引
 * @author Spencer
 *
 */
public class TableIndex implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -154577242101670601L;
	/**
	 * 表名
	 */
	public String tableName;
	/**
	 * 列名
	 */
	public String columnName;
	/**
	 * 索引描述
	 */
	public String indexName;

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

	public String getIndexName() {
		return indexName;
	}

	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((columnName == null) ? 0 : columnName.hashCode());
		result = prime * result + ((indexName == null) ? 0 : indexName.hashCode());
		result = prime * result + ((tableName == null) ? 0 : tableName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		TableIndex other = (TableIndex) obj;
		if (columnName == null) {
			if (other.columnName != null) {
				return false;
			}
		} else if (!columnName.equals(other.columnName)) {
			return false;
		}
		if (indexName == null) {
			if (other.indexName != null) {
				return false;
			}
		} else if (!indexName.equals(other.indexName)) {
			return false;
		}
		if (tableName == null) {
			if (other.tableName != null) {
				return false;
			}
		} else if (!tableName.equals(other.tableName)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "TableIndex [tableName=" + tableName + ", columnName=" + columnName + ", indexName=" + indexName
				+ "]";
	}
}
