package com.jxy.springMvc.entity;

public class TicketEntity {
	private long ticketId;//ID
	private String ticketNum;//发票号码
	private String ticketCode;//发票代码
	private double moneyAccount;//金额
	private String customerName;//客户名
	private long customerId;//客户ID
	private String reasonPayment;//原因
	private String ticketTime;//票据时间
	private String createTime;//创建时间
	private String pic;//图片
	private String state;//状态
	private String company;//公司
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	public long getTicketId() {
		return ticketId;
	}
	public void setTicketId(long ticketId) {
		this.ticketId = ticketId;
	}
	public String getTicketNum() {
		return ticketNum;
	}
	public void setTicketNum(String ticketNum) {
		this.ticketNum = ticketNum;
	}
	public double getMoneyAccount() {
		return moneyAccount;
	}
	public void setMoneyAccount(double moneyAccount) {
		this.moneyAccount = moneyAccount;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getReasonPayment() {
		return reasonPayment;
	}
	public void setReasonPayment(String reasonPayment) {
		this.reasonPayment = reasonPayment;
	}
	public String getTicketTime() {
		return ticketTime;
	}
	public void setTicketTime(String ticketTime) {
		this.ticketTime = ticketTime;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getTicketCode() {
		return ticketCode;
	}
	public void setTicketCode(String ticketCode) {
		this.ticketCode = ticketCode;
	}
}
