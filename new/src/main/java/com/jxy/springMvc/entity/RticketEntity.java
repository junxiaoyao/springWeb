package com.jxy.springMvc.entity;

public class RticketEntity {
	private long rTicketId;//ID
	private String ticketNum;//发票代码
	private String ticketCode;//发票代码
	private long userId;//办理人
	private long ticketId;//代开票ID
	private long rType;//类型ID
	private long customerId;//客户ID
	private String customerName;//客户名
	private String createTime;//创建时间
	private String remark;//备注
	private String type;//类型
	private String pic;//图片
	private String realName;//客户名
	public long getrTicketId() {
		return rTicketId;
	}
	public void setrTicketId(long rTicketId) {
		this.rTicketId = rTicketId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getTicketId() {
		return ticketId;
	}
	public void setTicketId(long ticketId) {
		this.ticketId = ticketId;
	}
	public long getrType() {
		return rType;
	}
	public void setrType(long rType) {
		this.rType = rType;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getTicketNum() {
		return ticketNum;
	}
	public void setTicketNum(String ticketNum) {
		this.ticketNum = ticketNum;
	}
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getTicketCode() {
		return ticketCode;
	}
	public void setTicketCode(String ticketCode) {
		this.ticketCode = ticketCode;
	}
	
	
}
