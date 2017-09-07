package com.ssm.rest.demo.model;


/**
 * 
 * 类名称：ConditionQueryModel   
 * 类描述：自定义Model：表格查询条件  
 * 
 * @version 1.0  
 *
 */
public class ConditionQueryModel {
	/** 
	 * 当前页码
	 */
	private int offset;
	/** 
	 * 每页显示的条数
	 */
	private int limit;
	/** 
	 * 查询字段
	 */
	private String fields;
	/** 
	 * 查询值
	 */
	private String query;
	/** 
	 * 排序样式
	 */
	private String order;
	/** 
	 * 排序字段
	 */
	private String sort;
	/** 
	 * 精确查找参数
	 */
	private String paramPairs;

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public String getFields() {
		return fields;
	}

	public void setFields(String fields) {
		this.fields = fields;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getParamPairs() {
		return paramPairs;
	}

	public void setParamPairs(String paramPairs) {
//		try {
//			this.paramPairs = new String(paramPairs.getBytes("iso8859-1"),
//					"UTF-8");
			// 由于前后分开开发，数据由后台构造而成，所以不需要转码。
			 this.paramPairs = paramPairs;
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
	}

	@Override
	public String toString() {
		return "ConditionQueryModel [当前页码=" + offset + ", 每页显示的条数=" + limit
				+ ", 查询字段=" + fields + ", 查询值=" + query + ", 排序样式="
				+ order + ", 排序字段=" + sort + ", 精确查找参数=" + paramPairs + "]";
	}
	
}
