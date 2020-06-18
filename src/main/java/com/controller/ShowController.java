package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.entity.Meal;
import com.entity.Page;
import com.service.ShowService;
import com.tool.Content;

@Controller
public class ShowController {
	@Autowired
	private ShowService showService;
	/**
	 * 首次访问/show.html
	 * @param model
	 * @param querySeriesId
	 * @param pageNum
	 * @param queryMealName
	 * @return
	 */
	@RequestMapping(value="/show.html")
	public String Show_html(
			@RequestParam(value="querySeriesId",required=false) String querySeriesId,
			@RequestParam(value="pageNum",required=false) String pageNum,
			@RequestParam(value="queryMealName",required=false)String queryMealName,
			Model model
			){
		Page page=new Page();
		if(querySeriesId!=null&&querySeriesId!=""){
			page.setQuerySeriesId(Integer.parseInt(querySeriesId));
		}
		if(queryMealName!=null&&queryMealName!=""){
			page.setQueryMealName(queryMealName);
		}
		if(pageNum==null||pageNum==""){
			page.setPageNum(1);
		}else{
			page.setPageNum(Integer.parseInt(pageNum));
		}
		page.setPageSize(Content.PAGE_SIZE);
		page=showService.getPage(page);
		model.addAttribute("page", page);
		return "show";
	}
	
	/**
	 * 根据mealId查询菜品信息
	 * @param mealId
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/showDetails")
	public String showDetails(
			@RequestParam("mealId")int mealId,
			Model model
			){
		Meal meal=new Meal();
		meal=showService.getMealInfo(mealId);
		model.addAttribute("meal", meal);
		return "details";
	}
}
