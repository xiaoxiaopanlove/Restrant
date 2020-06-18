package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.AdminDao;
import com.dao.MealDao;
import com.dao.MealSeriesDao;
import com.dao.OrderDao;
import com.entity.Admin;
import com.entity.Meal;
import com.entity.MealSeries;
import com.entity.Page;
import com.service.AdminService;
@Service("adminService")
public class AdminServiceImpl implements AdminService{
	@Autowired
	private AdminDao adminDao;
	@Autowired
	private MealDao mealDao;
	@Autowired
	private MealSeriesDao mealSeriesDao;
	@Autowired
	private OrderDao orderDao;
	
	public AdminDao getAdminDao() {
		return adminDao;
	}

	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

	@Override
	public List<Admin> checkLogin(Admin admin) {
		return adminDao.checkLogin(admin);
	}

	@Override
	public Page getPage(Page page) {
		page.setCountNum(mealDao.getMealCount(page));
		page.setMeals(mealDao.getMeals(page));
		page.setMealSeries(mealSeriesDao.getMealSeries());
		return page;
	}

	@Override
	public Meal getMealInfo(int mealId) {
		return mealDao.getMealInfo(mealId);
	}

	@Override
	public List<MealSeries> getMealSeries() {
		return mealSeriesDao.getMealSeries();
	}

	@Override
	public int UpdateMeal(Meal meal) {
		return mealDao.UpdateMeal(meal);
	}

	@Override
	public String deleteMeal(int mealId) {
		String result="no";
		if(orderDao.checkmealId(mealId)==0){
			result="yes";
			mealDao.deleteMeal(mealId);
		}
		return result;
	}

	@Override
	public int AddMeal(Meal meal) {
		return mealDao.AddMeal(meal);
	}

	@Override
	public Page getOrderPage(Page page) {
		page.setCountNum(orderDao.getOrderCount(page));
		page.setOrders(orderDao.getOrders(page));
		return page;
	}

	@Override
	public int handleOrders(int oId) {
		return orderDao.handleOrders(oId);
	}

}
