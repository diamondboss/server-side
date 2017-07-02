package com.diamondboss.util.vo;

/**
 * 检查支付宝结果Vo
 * @author xzf
 *
 */
public class checkAliPayVo {
	
	/**
	 * 返回状态
	 */
	private String resultStatus;
	
	/**
	 * 结果
	 */
	private String result;
	
	/**
	 * 描述
	 */
	private String memo;

	public String getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(String resultStatus) {
		this.resultStatus = resultStatus;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
}
