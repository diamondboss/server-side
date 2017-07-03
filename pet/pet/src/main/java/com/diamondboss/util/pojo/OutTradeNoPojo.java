package com.diamondboss.util.pojo;

/**
 * 数据库水平拆分表信息pojo
 * 
 * @author John
 * @since 2017-06-24
 *  
 */
public class OutTradeNoPojo {

	public OutTradeNoPojo(){
		
	}
	
	/**
	 * 表名id
	 */
	private String tableId;
	
	/**
	 * 对应表中的主键id
	 */
	private String id;

	/**
	 * 表名id
	 * @return
	 */
	public String getTableId() {
		return tableId;
	}

	/**
	 * 表名id
	 * @param tableId
	 */
	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

	/**
	 * 对应表中的主键id
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * 对应表中的主键id
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}
	
}
