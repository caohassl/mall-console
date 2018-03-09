package com.cmr.entities;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InsertWorkRequest implements Serializable{
	//private static final long serialVersionUID = 1L;
	private String orderId;
	private String orderCode;
	private Integer orderStatus;
	private String orderTypeId;
	private String orderTypeDetailId;
	private String sourceTypeName;
	private Integer sourceSystem;
	private String createUserName;
	private String assignUserName;
	private String endUserName;
	private String ownerRoomSign;
	private String informRoomSign;
	private String informUserName;
	private String informUserMobile;
	private String informPhone;
	private String orderMemo;
	private String orderCreateTime;
	private String orderFinishTime;
	private String endUserId;
	private String reviewMemo;
	private String reviewType;
	private String webhook;
	private String reserveTime;//预约时间
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOrderTypeId() {
		return orderTypeId;
	}

	public void setOrderTypeId(String orderTypeId) {
		this.orderTypeId = orderTypeId;
	}

	public String getOrderTypeDetailId() {
		return orderTypeDetailId;
	}

	public void setOrderTypeDetailId(String orderTypeDetailId) {
		this.orderTypeDetailId = orderTypeDetailId;
	}

	public String getSourceTypeName() {
		return sourceTypeName;
	}

	public void setSourceTypeName(String sourceTypeName) {
		this.sourceTypeName = sourceTypeName;
	}

	public Integer getSourceSystem() {
		return sourceSystem;
	}

	public void setSourceSystem(Integer sourceSystem) {
		this.sourceSystem = sourceSystem;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public String getAssignUserName() {
		return assignUserName;
	}

	public void setAssignUserName(String assignUserName) {
		this.assignUserName = assignUserName;
	}

	public String getEndUserName() {
		return endUserName;
	}

	public void setEndUserName(String endUserName) {
		this.endUserName = endUserName;
	}

	public String getOwnerRoomSign() {
		return ownerRoomSign;
	}

	public void setOwnerRoomSign(String ownerRoomSign) {
		this.ownerRoomSign = ownerRoomSign;
	}

	public String getInformRoomSign() {
		return informRoomSign;
	}

	public void setInformRoomSign(String informRoomSign) {
		this.informRoomSign = informRoomSign;
	}

	public String getInformUserName() {
		return informUserName;
	}

	public void setInformUserName(String informUserName) {
		this.informUserName = informUserName;
	}

	public String getInformUserMobile() {
		return informUserMobile;
	}

	public void setInformUserMobile(String informUserMobile) {
		this.informUserMobile = informUserMobile;
	}

	public String getInformPhone() {
		return informPhone;
	}

	public void setInformPhone(String informPhone) {
		this.informPhone = informPhone;
	}

	public String getOrderMemo() {
		return orderMemo;
	}

	public void setOrderMemo(String orderMemo) {
		this.orderMemo = orderMemo;
	}

	public String getOrderCreateTime() {
		return orderCreateTime;
	}

	public void setOrderCreateTime(String orderCreateTime) {
		this.orderCreateTime = orderCreateTime;
	}

	public String getOrderFinishTime() {
		return orderFinishTime;
	}

	public void setOrderFinishTime(String orderFinishTime) {
		this.orderFinishTime = orderFinishTime;
	}
	
	
	public String getEndUserId() {
		return endUserId;
	}

	public void setEndUserId(String endUserId) {
		this.endUserId = endUserId;
	}

	public String getReviewMemo() {
		return reviewMemo;
	}

	public void setReviewMemo(String reviewMemo) {
		this.reviewMemo = reviewMemo;
	}

	public String getReviewType() {
		return reviewType;
	}

	public void setReviewType(String reviewType) {
		this.reviewType = reviewType;
	}

	public String getWebhook() {
		return webhook;
	}

	public void setWebhook(String webhook) {
		this.webhook = webhook;
	}

	public String getReserveTime() {
		return reserveTime;
	}

	public void setReserveTime(String reserveTime) {
		this.reserveTime = reserveTime;
	}

	@Override
	public String toString() {
		return "InsertWorkRequest [orderId=" + this.orderId + ", orderCode=" +this.orderCode + ", orderStatus=" + this.orderStatus
				+ ", orderTypeId=" + this.orderTypeId + ", orderTypeDetailId=" + this.orderTypeDetailId + ", sourceTypeName="
				+ this.sourceTypeName + ", sourceSystem=" + this.sourceSystem + ", createUserName=" + this.createUserName
				+ ", assignUserName=" + this.assignUserName + ", endUserName=" + this.endUserName + ", ownerRoomSign="
				+ this.ownerRoomSign + ", informRoomSign=" + this.informRoomSign + ", informUserName=" + this.informUserName
				+ ", informUserMobile=" + this.informUserMobile + ", informPhone=" + this.informPhone + ", orderMemo=" +this.orderMemo
				+ ", orderCreateTime=" + this.orderCreateTime + ", orderFinishTime=" + this.orderFinishTime + ",endUserId="+this.endUserId+",reviewMemo="
						+ this.reviewMemo+",reviewType="+this.reviewType+",webhook="+this.webhook+",reserveTime="+this.reserveTime+"]";
	}
	
	
	
}
