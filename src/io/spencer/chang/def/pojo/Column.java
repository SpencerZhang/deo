package io.spencer.chang.def.pojo;

import java.io.Serializable;
/**
 * 列
 * @author Spencer
 *
 */
public class Column implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2600772517308196562L;
	/**
	 * 表名
	 */
	public String tableName;
	/**
	 * 列名
	 */
	public String columnName;
	/**
	 * 数据类型
	 */
	public String dataType;
	/**
	 * 长度
	 */
	public int dataLength;
	/**
	 * 是否可为null
	 */
	public String nullAble;
	/**
	 * 排序
	 */
	public int columnId;
	/**
	 * 默认值
	 */
	public String dataDefault;

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

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public int getDataLength() {
		return dataLength;
	}

	public void setDataLength(int dataLength) {
		this.dataLength = dataLength;
	}

	public String getNullAble() {
		return nullAble;
	}

	public void setNullAble(String nullAble) {
		this.nullAble = nullAble;
	}

	public int getColumnId() {
		return columnId;
	}

	public void setColumnId(int columnId) {
		this.columnId = columnId;
	}

	public String getDataDefault() {
		return dataDefault;
	}

	public void setDataDefault(String dataDefault) {
		this.dataDefault = dataDefault;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + columnId;
		result = prime * result + ((columnName == null) ? 0 : columnName.hashCode());
		result = prime * result + ((dataDefault == null) ? 0 : dataDefault.hashCode());
		result = prime * result + dataLength;
		result = prime * result + ((dataType == null) ? 0 : dataType.hashCode());
		result = prime * result + ((nullAble == null) ? 0 : nullAble.hashCode());
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
		Column other = (Column) obj;
		if (columnId != other.columnId) {
			return false;
		}
		if (columnName == null) {
			if (other.columnName != null) {
				return false;
			}
		} else if (!columnName.equals(other.columnName)) {
			return false;
		}
		if (dataDefault == null) {
			if (other.dataDefault != null) {
				return false;
			}
		} else if (!dataDefault.equals(other.dataDefault)) {
			return false;
		}
		if (dataLength != other.dataLength) {
			return false;
		}
		if (dataType == null) {
			if (other.dataType != null) {
				return false;
			}
		} else if (!dataType.equals(other.dataType)) {
			return false;
		}
		if (nullAble == null) {
			if (other.nullAble != null) {
				return false;
			}
		} else if (!nullAble.equals(other.nullAble)) {
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
		return "Column [tableName=" + tableName + ", columnName=" + columnName + ", dataType=" + dataType
				+ ", dataLength=" + dataLength + ", nullAble=" + nullAble + ", columnId=" + columnId
				+ ", dataDefault=" + dataDefault + "]";
	}
}
