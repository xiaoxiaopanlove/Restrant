package com.aop;

import org.apache.ibatis.jdbc.SQL;

import com.entity.Meal;
import com.entity.Page;

public class MealSQL {
	
	/**
	 * 获取分页Meal总数
	 * @param page
	 * @return
	 */
	public String getMealCount(final Page page){
		return new SQL(){
			{
				SELECT("count(MealId)");
				FROM("meal");
				if(page.getQuerySeriesId()!=0){
					WHERE("MealSeriesId =" + page.getQuerySeriesId());
				}
				if(page.getQueryMealName()!=null){
					WHERE("MealName like '%"+page.getQueryMealName()+"%'");
				}
			}
		}.toString();
	}
	
	/**
	 * 获取分页Meal信息
	 * @param page
	 * @return
	 */
	public String getMeals(final Page page){
		String sql= new SQL(){
			{
				SELECT("*");
				FROM("meal");
				if(page.getQuerySeriesId()!=0){
					WHERE("MealSeriesId =" + page.getQuerySeriesId());
				}
				if(page.getQueryMealName()!=null){
					WHERE("MealName like '%"+page.getQueryMealName()+"%'");
				}
			}
		}.toString();
		return sql+" limit "+(page.getPageNum()-1)*page.getPageSize()+","+page.getPageSize();
	}
	
	/**
	 * 修改餐品信息
	 * @param meal
	 * @return
	 */
	public String UpdateMeal(final Meal meal){
		return new SQL(){
			{
				UPDATE("meal");
				if(meal.getMealSeriesId()!=0){
					SET("MealSeriesId = #{mealSeriesId}");
				}
				if(meal.getMealName()!=null){
					SET("MealName = #{mealName}");
				}
				if(meal.getMealSummarize()!=null){
					SET("MealSummarize = #{mealSummarize}");
				}
				if(meal.getMealDescription()!=null){
					SET("MealDescription = #{mealDescription}");
				}
				if(meal.getMealPrice()!=null){
					SET("MealPrice = #{mealPrice}");
				}
				if(meal.getMealImage()!=null){
					SET("MealImage = #{mealImage}");
				}
				WHERE(" MealID = #{mealId}");
			}
		}.toString();
	}
	
	/**
	 * 添加餐品信息
	 * @param meal
	 * @return
	 */
	public String AddMeal(final Meal meal){
		return new SQL(){
			{
				INSERT_INTO("meal");
				if(meal.getMealSeriesId()!=0){
					VALUES("MealSeriesId", "#{mealSeriesId}");
				}
				if(meal.getMealName()!=null){
					VALUES("MealName", "#{mealName}");
				}
				if(meal.getMealSummarize()!=null){
					VALUES("MealSummarize", "#{mealSummarize}");
				}
				if(meal.getMealDescription()!=null){
					VALUES("MealDescription", "#{mealDescription}");
				}
				if(meal.getMealPrice()!=null){
					VALUES("MealPrice", "#{mealPrice}");
				}
				if(meal.getMealImage()!=null){
					VALUES("MealImage", "#{mealImage}");
				}
			}
		}.toString();
	}
}
