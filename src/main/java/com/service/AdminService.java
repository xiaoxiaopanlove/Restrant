package com.service;

import java.util.List;

import com.entity.Admin;
import com.entity.Meal;
import com.entity.MealSeries;
import com.entity.Page;

public interface AdminService {

	/**
	 * 管理员登录
	 * @param admin
	 * @return
	 */
	public List<Admin> checkLogin(Admin admin);

	/**
	 * 餐品管理
	 * @param page
	 * @return
	 */
	public Page getPage(Page page);

	/**
	 * 获取餐品信息
	 * @param mealId
	 * @return
	 */
	public Meal getMealInfo(int mealId);

	/**
	 * 获取所有菜系
	 * @return
	 */
	public List<MealSeries> getMealSeries();

	/**
	 * 修改餐品信息
	 * @param meal
	 * @return
	 */
	public int UpdateMeal(Meal meal);

	/**
	 * 删除餐品信息
	 * @param mealId
	 * @return
	 */
	public String deleteMeal(int mealId);

	/**
	 * 添加餐品信息
	 * @param meal
	 * @return
	 */
	public int AddMeal(Meal meal);

	/**
	 * 获取订单管理分页
	 * @param page
	 * @return
	 */
	public Page getOrderPage(Page page);

	/**
	 * 处理订单
	 * @param oId
	 * @return
	 */
	public int handleOrders(int oId);

}
