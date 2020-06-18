package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.MealDao;
import com.dao.MealSeriesDao;
import com.entity.Meal;
import com.entity.Page;
import com.service.ShowService;

@Service("showService")
public class ShowServiceImpl implements ShowService{
	@Autowired
	private MealDao mealDao;
	@Autowired
	private MealSeriesDao mealSeriesDao;
	
	public MealDao getMealDao() {
		return mealDao;
	}

	public void setMealDao(MealDao mealDao) {
		this.mealDao = mealDao;
	}

	public MealSeriesDao getMealSeriesDao() {
		return mealSeriesDao;
	}

	public void setMealSeriesDao(MealSeriesDao mealSeriesDao) {
		this.mealSeriesDao = mealSeriesDao;
	}

	public Page getPage(Page page) {
		page.setCountNum(mealDao.getMealCount(page));
		page.setMeals(mealDao.getMeals(page));
		page.setMealSeries(mealSeriesDao.getMealSeries());
		return page;
	}

	
	public Meal getMealInfo(int mealId) {
		return mealDao.getMealInfo(mealId);
	}

}
