package com.service;

import com.entity.Meal;
import com.entity.Page;

public interface ShowService {
	/**
	 * 获取分页数据
	 * @param page
	 * @return
	 */
	public Page getPage(Page page);

	/**
	 * 根据mealId查询菜品信息
	 * @param mealId
	 * @return
	 */
	public Meal getMealInfo(int mealId);

}
