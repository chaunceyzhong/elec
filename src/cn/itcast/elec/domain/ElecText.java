package cn.itcast.elec.domain;

import java.util.Date;

@SuppressWarnings("serial")
public class ElecText implements java.io.Serializable {

	/**
	 * 测试ID
	 */
	private String textID;
	/**
	 * 测试名称
	 */
	private String textName;
	/**
	 * 测试日期
	 */
	private Date textDate;
	/**
	 * 测试备注
	 */
	private String textRemark;
	
	public String getTextID() {
		return textID;
	}
	public void setTextID(String textID) {
		this.textID = textID;
	}
	public String getTextName() {
		return textName;
	}
	public void setTextName(String textName) {
		this.textName = textName;
	}
	public Date getTextDate() {
		return textDate;
	}
	public void setTextDate(Date textDate) {
		this.textDate = textDate;
	}
	public String getTextRemark() {
		return textRemark;
	}
	public void setTextRemark(String textRemark) {
		this.textRemark = textRemark;
	}
	
	
}
