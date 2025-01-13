package model.dto;

import java.time.LocalDateTime;

public class OrderCompleteDto {
	private String orderId; // 주문자 아이디
	private LocalDateTime orderDate; // 주문날짜
	private String orderMenuName; // 주문메뉴명
	private int orderMenuPrice; // 주문메뉴가격
	private int orderMno; // 주문자 회원번호 (기피신청/별점기능 구현시 사용)
	private int orderEno; // 주문 가게번호 (기피신청기능 구현시 사용)
	public String orderEntryName; // 주문 가게명

	public OrderCompleteDto(String orderId, LocalDateTime orderDate, String orderMenuName, int orderMenuPrice, int orderMno, int orderEno, String orderEntryName) {
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.orderMenuName = orderMenuName;
		this.orderMenuPrice = orderMenuPrice;
		this.orderMno = orderMno;
		this.orderEno = orderEno;
		this.orderEntryName = orderEntryName;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderMenuName() {
		return orderMenuName;
	}

	public void setOrderMenuName(String orderMenuName) {
		this.orderMenuName = orderMenuName;
	}

	public int getOrderMenuPrice() {
		return orderMenuPrice;
	}

	public void setOrderMenuPrice(int orderMenuPrice) {
		this.orderMenuPrice = orderMenuPrice;
	}

	public int getOrderMno() {
		return orderMno;
	}

	public void setOrderMno(int orderMno) {
		this.orderMno = orderMno;
	}

	public int getOrderEno() {
		return orderEno;
	}

	public void setOrderEno(int orderEno) {
		this.orderEno = orderEno;
	}
	
	public String getOrderEntryName() {
		return orderEntryName;
	}

	public void setOrderEntryName(String orderEntryName) {
		this.orderEntryName = orderEntryName;
	}

	@Override
	public String toString() {
		return "OrderCompleteDto [orderId=" + orderId + ", orderMenuName=" + orderMenuName + ", orderMenuPrice="
				+ orderMenuPrice + ", orderMno=" + orderMno + ", orderEno=" + orderEno + "]";
	}
}