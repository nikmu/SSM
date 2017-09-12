package com.ssm.rest.demo.common.mybatis;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

@MappedTypes({List.class})
@MappedJdbcTypes({JdbcType.VARCHAR})
public class LongArrayTypeHandler extends BaseTypeHandler<List<Long>>{

	@Override
	public List<Long> getNullableResult(ResultSet rs, String columnName) throws SQLException {
		return this.getLongArray(rs.getString(columnName));
	}

	@Override
	public List<Long> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return this.getLongArray(rs.getString(columnIndex));
	}

	@Override
	public List<Long> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return this.getLongArray(cs.getString(columnIndex));
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, List<Long> param, JdbcType jdbcType) throws SQLException {
		StringBuffer result = new StringBuffer();
		for (Long value : param) {
			result.append(value).append(",");
		}
		result.deleteCharAt(result.length()-1);
		ps.setString(i, result.toString());
		
	}

	private List<Long> getLongArray(String columnValue) {
		if(columnValue != null) {
			String[] values = columnValue.split(",");
			List<Long> result = new ArrayList<>();
			for(int i = 0; i < values.length; i++) {
				result.add(Long.valueOf(values[i]));
			}
			return result;
		}
		return null;
	}
}
