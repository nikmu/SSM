package com.ssm.rest.demo.common.mybatis;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

public class StringArrayTypeHandler extends BaseTypeHandler<String[]>{

	@Override
	public String[] getNullableResult(ResultSet rs, String columnName) throws SQLException {
		return this.getStringArray(rs.getString(columnName));
	}

	@Override
	public String[] getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return this.getStringArray(rs.getString(columnIndex));
	}

	@Override
	public String[] getNullableResult(CallableStatement cs, int columnIdex) throws SQLException {
		return this.getStringArray(cs.getString(columnIdex));
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, String[] param, JdbcType jdbcType)
			throws SQLException {
		StringBuffer result = new StringBuffer();
		for (String value : param) {
			result.append(value).append(",");
		}
		result.deleteCharAt(result.length()-1);
		ps.setString(i, result.toString());
	}
	
	private String[] getStringArray(String columnValue) {
		if(columnValue != null) {
			return columnValue.split(",");
		}
		return null;
	}
}
