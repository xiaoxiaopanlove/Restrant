package com.entity;

import java.math.BigDecimal;

import org.springframework.web.multipart.MultipartFile;

public class Meal {
 private int mealId;
 private int mealSeriesId;
 private String mealName;
 private String mealSummarize;
 private String mealDescription;
 private BigDecimal mealPrice;
 private String mealImage;
 private MealSeries mealSeries;
 private MultipartFile images;
 
 
public MultipartFile getImages() {
	return images;
}
public void setImages(MultipartFile images) {
	this.images = images;
}
public MealSeries getMealSeries() {
	return mealSeries;
}
public void setMealSeries(MealSeries mealSeries) {
	this.mealSeries = mealSeries;
}
public int getMealId() {
	return mealId;
}
public void setMealId(int mealId) {
	this.mealId = mealId;
}
public int getMealSeriesId() {
	return mealSeriesId;
}
public void setMealSeriesId(int mealSeriesId) {
	this.mealSeriesId = mealSeriesId;
}
public String getMealName() {
	return mealName;
}
public void setMealName(String mealName) {
	this.mealName = mealName;
}
public String getMealSummarize() {
	return mealSummarize;
}
public void setMealSummarize(String mealSummarize) {
	this.mealSummarize = mealSummarize;
}
public String getMealDescription() {
	return mealDescription;
}
public void setMealDescription(String mealDescription) {
	this.mealDescription = mealDescription;
}
public BigDecimal getMealPrice() {
	return mealPrice;
}
public void setMealPrice(BigDecimal mealPrice) {
	this.mealPrice = mealPrice;
}
public String getMealImage() {
	return mealImage;
}
public void setMealImage(String mealImage) {
	this.mealImage = mealImage;
}

 
}
