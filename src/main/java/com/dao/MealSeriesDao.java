package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.entity.MealSeries;

public interface MealSeriesDao {
	/**
	 * 获取所有菜系
	 * @return
	 */
	@Select("select * from mealseries")
	public List<MealSeries> getMealSeries();
	
	/**
	 * 获取meal的菜系
	 * @param seriesId
	 * @return
	 */
	@Select("select * from mealseries where SeriesId=#{seriesId}")
	public MealSeries getMealSeriesInfo(@Param("seriesId")int seriesId);
}
