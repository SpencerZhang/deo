package io.spencer.chang.def.pojo;

import java.io.Serializable;

public class Column implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2600772517308196562L;
	//表名
	public String table_name;
	// 列名
	public String column_name;
	// 数据类型
	public String data_type;
	// 长度
	public int data_length;
	// 是否可为null
	public String nullable;
	// 排序
	public int column_id;
	// 默认值
	public String data_default;
	
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
	public String getData_type() {
		return data_type;
	}
	public void setData_type(String data_type) {
		this.data_type = data_type;
	}
	public int getData_length() {
		return data_length;
	}
	public void setData_length(int data_length) {
		this.data_length = data_length;
	}
	public String getNullable() {
		return nullable;
	}
	public void setNullable(String nullable) {
		this.nullable = nullable;
	}
	public int getColumn_id() {
		return column_id;
	}
	public void setColumn_id(int column_id) {
		this.column_id = column_id;
	}
	public String getData_default() {
		return data_default;
	}
	public void setData_default(String data_default) {
		this.data_default = data_default;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + column_id;
		result = prime * result + ((column_name == null) ? 0 : column_name.hashCode());
		result = prime * result + ((data_default == null) ? 0 : data_default.hashCode());
		result = prime * result + data_length;
		result = prime * result + ((data_type == null) ? 0 : data_type.hashCode());
		result = prime * result + ((nullable == null) ? 0 : nullable.hashCode());
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
		Column other = (Column) obj;
		if (column_id != other.column_id)
			return false;
		if (column_name == null) {
			if (other.column_name != null)
				return false;
		} else if (!column_name.equals(other.column_name))
			return false;
		if (data_default == null) {
			if (other.data_default != null)
				return false;
		} else if (!data_default.equals(other.data_default))
			return false;
		if (data_length != other.data_length)
			return false;
		if (data_type == null) {
			if (other.data_type != null)
				return false;
		} else if (!data_type.equals(other.data_type))
			return false;
		if (nullable == null) {
			if (other.nullable != null)
				return false;
		} else if (!nullable.equals(other.nullable))
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
		return "Column [table_name=" + table_name + ", column_name=" + column_name + ", data_type=" + data_type
				+ ", data_length=" + data_length + ", nullable=" + nullable + ", column_id=" + column_id
				+ ", data_default=" + data_default + "]";
	}
}
