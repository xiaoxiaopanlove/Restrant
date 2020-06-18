package com.entity;

import java.math.BigDecimal;

public class Orderdts {
	private int odId;
	private int oId;
	private int mealId;
	private BigDecimal mealPrice;
	private int mealCount;
	private Meal meal;
	
	public Meal getMeal() {
		return meal;
	}
	public void setMeal(Meal meal) {
		this.meal = meal;
	}
	public int getOdId() {
		return odId;
	}
	public void setOdId(int odId) {
		this.odId = odId;
	}
	public int getoId() {
		return oId;
	}
	public void setoId(int oId) {
		this.oId = oId;
	}
	public int getMealId() {
		return mealId;
	}
	public void setMealId(int mealId) {
		this.mealId = mealId;
	}
	public BigDecimal getMealPrice() {
		return mealPrice;
	}
	public void setMealPrice(BigDecimal mealPrice) {
		this.mealPrice = mealPrice;
	}
	public int getMealCount() {
		return mealCount;
	}
	public void setMealCount(int mealCount) {
		this.mealCount = mealCount;
	}
	
}
