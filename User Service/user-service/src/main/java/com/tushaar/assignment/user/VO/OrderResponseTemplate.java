package com.tushaar.assignment.user.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseTemplate {
	
	public enum status {
		PENDING,
		APPROVED,
		REJECTED
	}
	
	private Long orderId;
	private Long userId;
	private String name;
	private String address;
	private String type;
	private String time;
	private status orderStatus;
	
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public status getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(status orderStatus) {
		this.orderStatus = orderStatus;
	}
	@Override
	public String toString() {
		return "OrderResponseTemplate [orderId=" + orderId + ", userId=" + userId + ", name=" + name + ", address="
				+ address + ", type=" + type + ", time=" + time + ", orderStatus=" + orderStatus + "]";
	}
	
}
