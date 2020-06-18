package com.entity;

import java.util.List;

public class Page {
	private int querySeriesId;
	private String queryMealName;
	private int pageNum;
	private int pageCountNum;
	private int countNum;
	private int pageSize;
	private List<Meal> meals;
	private List<MealSeries> mealSeries;
	
	private int oId;
	private String orderState;
	private List<Orders> orders;
	
	public List<Orders> getOrders() {
		return orders;
	}
	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}

	public int getoId() {
		return oId;
	}
	public void setoId(int oId) {
		this.oId = oId;
	}
	public String getOrderState() {
		return orderState;
	}
	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}
	public int getCountNum() {
		return countNum;
	}
	public void setCountNum(int countNum) {
		this.countNum = countNum;
		this.pageCountNum = countNum%this.pageSize==0?countNum/this.pageSize:(countNum/this.pageSize+1);
	}
	
	public List<Meal> getMeals() {
		return meals;
	}
	public void setMeals(List<Meal> meals) {
		this.meals = meals;
	}
	public List<MealSeries> getMealSeries() {
		return mealSeries;
	}
	public void setMealSeries(List<MealSeries> mealSeries) {
		this.mealSeries = mealSeries;
	}
	public int getPageCountNum() {
		return pageCountNum;
	}

	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getQuerySeriesId() {
		return querySeriesId;
	}
	public void setQuerySeriesId(int querySeriesId) {
		this.querySeriesId = querySeriesId;
	}
	public String getQueryMealName() {
		return queryMealName;
	}
	public void setQueryMealName(String queryMealName) {
		this.queryMealName = queryMealName;
	}
	
}
