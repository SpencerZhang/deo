package io.spencer.chang.def.pojo;

import java.io.Serializable;

public class TableIndex implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -154577242101670601L;
	//表名
	public String table_name;
	// 列名
	public String column_name;
	// 索引描述
	public String index_name;
	public String getTable_name() {
		return table_name;
	}
	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}
	public String getColumn_name() {
		return column_name;
	}
	public void setColumn_name(String column_name) {
		this.column_name = column_name;
	}
	public String getIndex_name() {
		return index_name;
	}
	public void setIndex_name(String index_name) {
		this.index_name = index_name;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((column_name == null) ? 0 : column_name.hashCode());
		result = prime * result + ((index_name == null) ? 0 : index_name.hashCode());
		result = prime * result + ((table_name == null) ? 0 : table_name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TableIndex other = (TableIndex) obj;
		if (column_name == null) {
			if (other.column_name != null)
				return false;
		} else if (!column_name.equals(other.column_name))
			return false;
		if (index_name == null) {
			if (other.index_name != null)
				return false;
		} else if (!index_name.equals(other.index_name))
			return false;
		if (table_name == null) {
			if (other.table_name != null)
				return false;
		} else if (!table_name.equals(other.table_name))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "TableIndex [table_name=" + table_name + ", column_name=" + column_name + ", index_name=" + index_name
				+ "]";
	}
}
