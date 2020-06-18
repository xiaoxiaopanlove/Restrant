package com.entity;

public class ShopCart {
	private Meal meal;
	private int mealCount;
	
	public ShopCart() {
		super();
	}
	public ShopCart(Meal meal, int mealCount) {
		super();
		this.meal = meal;
		this.mealCount = mealCount;
	}
	public Meal getMeal() {
		return meal;
	}
	public void setMeal(Meal meal) {
		this.meal = meal;
	}
	public int getMealCount() {
		return mealCount;
	}
	public void setMealCount(int mealCount) {
		this.mealCount = mealCount;
	}
	@Override
	public String toString() {
		return "ShopCart [meal=" + meal + ", mealCount=" + mealCount + "]";
	}
	
}
