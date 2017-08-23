package com.diamondboss.petsHotel.vo;

/**
 * 酒店端钱包明细信息
 * 
 * @author John
 * @since 2017-08-21
 *  
 */
public class HotelWalletDetail {

	/**
	 * 明细金额
	 */
	private String amt;
	
	/**
	 * 明细类别
	 */
	private String kind;
	
	/**
	 * 明细时间
	 */
	private String time;

	/**
	 * 明细状态
	 */
	private String status;
	
	/**
	 * 明细金额
	 * @return
	 */
	public String getAmt() {
		return amt;
	}

	/**
	 * 明细金额
	 * @param amt
	 */
	public void setAmt(String amt) {
		this.amt = amt;
	}

	/**
	 * 明细类别
	 * @return
	 */
	public String getKind() {
		return kind;
	}

	/**
	 * 明细类别
	 * @param kind
	 */
	public void setKind(String kind) {
		this.kind = kind;
	}

	/**
	 * 明细时间
	 * @return
	 */
	public String getTime() {
		return time;
	}

	/**
	 * 明细时间
	 * @param time
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * 明细状态
	 * @return
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * 明细备注
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
}
