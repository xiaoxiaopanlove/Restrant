package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.mapping.FetchType;

import com.aop.MealSQL;
import com.entity.Meal;
import com.entity.Page;

public interface MealDao {
	/**
	 * 获取分页Meal的总数
	 * @param page
	 * @return
	 */
	@SelectProvider(type=MealSQL.class,method="getMealCount")
	public int getMealCount(Page page);
	
	/**
	 * 获取分页Meal信息
	 * @param page
	 * @return
	 */
	@SelectProvider(type=MealSQL.class,method="getMeals")
	@Results({
		@Result(column="MealSeriesId",property="mealSeries",
				one=@One(
						select="com.dao.MealSeriesDao.getMealSeriesInfo",
						fetchType=FetchType.EAGER
						))
	})
	public List<Meal> getMeals(Page page);

	/**
	 * 根mealId查询菜品信息
	 * @param mealId
	 * @return
	 */
	@Select("select * from meal where MealId=#{mealId}")
	public Meal getMealInfo(@Param("mealId")int mealId);
	/**
	 * 修改餐品信息
	 * @param meal
	 * @return
	 */
	@UpdateProvider(type=MealSQL.class,method="UpdateMeal")
	public int UpdateMeal(Meal meal);

	/**
	 * 删除餐品信息
	 * @param mealId
	 * @return
	 */
	@Delete("delete from meal where MealId=#{mealId}")
	public int deleteMeal(int mealId);

	/**
	 * 添加餐品信息
	 * @param meal
	 * @return
	 */
	@InsertProvider(type=MealSQL.class,method="AddMeal")
	public int AddMeal(Meal meal);
}
