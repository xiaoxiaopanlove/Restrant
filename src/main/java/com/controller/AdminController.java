package com.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.entity.Admin;
import com.entity.Meal;
import com.entity.MealSeries;
import com.entity.Page;
import com.service.AdminService;
import com.tool.Content;

@Controller
public class AdminController {
	@Autowired
	private AdminService adminService;
	/**
	 * 管理员登录
	 * @param admin
	 * @param model
	 * @param session
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/admin_login",method=RequestMethod.POST)
	public void admin_login(@ModelAttribute Admin admin,
			Model model,
			HttpSession session,
			HttpServletResponse response
			) throws IOException{
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		List<Admin> admins=adminService.checkLogin(admin);
		if(admins.size()>0){
			session.setAttribute("admin", admins.get(0));
			out.println("<script type='text/javascript'>");
			out.println("alert('登录成功!');location.href='"+session.getAttribute("url")+"';");
			out.println("</script>");
		}else{
			out.println("<script type='text/javascript'>");
			out.println("alert('登录失败!请输入正确的登录名和密码！');");
			out.println("location.href=history.go(-1);");
			out.println("</script>");
		}
	}
	
	/**
	 * 餐品管理
	 * @param querySeriesId
	 * @param pageNum
	 * @param queryMealName
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/toManageMeal")
	public String toManageMeal(
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
		page=adminService.getPage(page);
		model.addAttribute("page", page);
		return "managemeal";
	}
	
	/**
	 * 跳转修改餐品页面
	 * @param mealId
	 * @param request
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/toUpdateMeal")
	public String toUpdateMeal(
				@RequestParam("mealId") int mealId,
				HttpServletRequest request,
				HttpSession session,
				Model model
			){
		session.setAttribute("manageMealurl", request.getHeader("Referer"));
		Meal meal=adminService.getMealInfo(mealId);
		List<MealSeries> mealSeries=adminService.getMealSeries();
		model.addAttribute("meal", meal);
		model.addAttribute("mealSeries", mealSeries);
		return "updateMeal";
		}
	/**
	 * 修改餐品信息
	 * @param session
	 * @param response
	 * @param meal
	 * @param request
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping(value="/doUpdateMeal",method=RequestMethod.POST)
	public void doUpdateMeal(
			HttpSession session,
			HttpServletResponse response,
			@ModelAttribute Meal meal,
			HttpServletRequest request
			) throws IllegalStateException, IOException{
			MultipartFile image=meal.getImages();
			if(!image.isEmpty()){
				// 上传文件路径
				String path = request.getServletContext().getRealPath("/mealimages/");
				// 上传文件名
				String filename = image.getOriginalFilename();
				meal.setMealImage(filename);
				File filepath = new File(path,filename);
				// 判断路径是否存在，如果不存在就创建一个
				if (!filepath.getParentFile().exists()){ 
				filepath.getParentFile().mkdirs();
				}
				// 将上传文件保存到一个目标文件当中
				image.transferTo(new File(path+File.separator+ filename));
			}
			response.setContentType("text/html;charset=UTF-8");
			response.setCharacterEncoding("utf-8");
			PrintWriter out=response.getWriter();
			int row=adminService.UpdateMeal(meal);
			if(row>0){
				out.println("<script type='text/javascript'>");
				out.println("alert('修改成功!');location.href='"+session.getAttribute("manageMealurl")+"'");
				out.println("</script>");
			}else{
				out.println("<script type='text/javascript'>");
				out.println("alert('修改失败!')");
				out.println("</script>");
			}
	}
	
	/**
	 * 删除餐品信息
	 * @param response
	 * @param mealId
	 * @throws IOException 
	 */
	@RequestMapping(value="/deleteMeal")
	public void deleteMeal(
			HttpServletResponse response,
			@RequestParam("mealId") int mealId
			) throws IOException{
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		String result=adminService.deleteMeal(mealId);
		if(result.equals("yes")){
			out.println("<script type='text/javascript'>");
			out.println("alert('删除成功!');");
			out.println("self.location=document.referrer;");
			out.println("</script>");
		}else{
			out.println("<script type='text/javascript'>");
			out.println("alert('删除失败!还有订单包含该商品，请先处理！！')");
			out.println("self.location=document.referrer;");
			out.println("</script>");
		}
	}
	
	/**
	 * 跳转添加餐品页面
	 * @param request
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/toAddMeal")
	public String toAddMeal(
			HttpServletRequest request,
			HttpSession session,
			Model model
			){
		if(!request.getHeader("Referer").contains("toAddMeal")){
			session.setAttribute("AddMealurl", request.getHeader("Referer"));
		}
		List<MealSeries> mealSeries=adminService.getMealSeries();
		model.addAttribute("mealSeries", mealSeries);
		return "addMeal";
	}
	
	/**
	 * 添加餐品
	 * @param session
	 * @param response
	 * @param meal
	 * @param request
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping(value="/doAddMeal",method=RequestMethod.POST)
	public void doAddMeal(
			HttpSession session,
			HttpServletResponse response,
			@ModelAttribute Meal meal,
			HttpServletRequest request
			) throws IllegalStateException, IOException{
			MultipartFile image=meal.getImages();
			if(!image.isEmpty()){
				// 上传文件路径
				String path = request.getServletContext().getRealPath("/mealimages/");
				// 上传文件名
				String filename = image.getOriginalFilename();
				meal.setMealImage(filename);
				File filepath = new File(path,filename);
				// 判断路径是否存在，如果不存在就创建一个
				if (!filepath.getParentFile().exists()){ 
				filepath.getParentFile().mkdirs();
				}
				// 将上传文件保存到一个目标文件当中
				image.transferTo(new File(path+File.separator+ filename));
			}
			response.setContentType("text/html;charset=UTF-8");
			response.setCharacterEncoding("utf-8");
			PrintWriter out=response.getWriter();
			int row=adminService.AddMeal(meal);
			if(row>0){
				out.println("<script type='text/javascript'>");
				out.println("alert('添加成功!');location.href='"+session.getAttribute("AddMealurl")+"'");
				out.println("</script>");
			}else{
				out.println("<script type='text/javascript'>");
				out.println("alert('添加失败!')");
				out.println("</script>");
			}
	}
	
	/**
	 * 跳转订单处理页面
	 * @param model
	 * @param oId
	 * @param orderState
	 * @param pageNum
	 * @return
	 */
	@RequestMapping(value="/toManageOrders",method={RequestMethod.POST, RequestMethod.GET})
	public String toManageOrders(
			Model model,
			@RequestParam(value="oId",required=false) String oId,
			@RequestParam(value="orderState",required=false) String orderState,
			@RequestParam(value="pageNum",required=false) String pageNum
			){
		Page page=new Page();
		if(oId!=null&&oId!=""){
			page.setoId(Integer.parseInt(oId));
		}
		if(orderState!=null&&orderState!=""){
			if(!orderState.equals("全部")){
				page.setOrderState(orderState);
			}
		}
		if(pageNum==null||pageNum==""){
			page.setPageNum(1);
		}else{
			page.setPageNum(Integer.parseInt(pageNum));
		}
		page.setPageSize(Content.PAGE_SIZE);
		page=adminService.getOrderPage(page);
		model.addAttribute("page", page);
		return "manageorders";
	}
	/**
	 * 订单处理
	 * @param oId
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="handleOrders")
	public void handleOrders(
			@RequestParam("oId") int oId,
			HttpServletResponse response
			) throws IOException{
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		int row=adminService.handleOrders(oId);
		if(row>0){
			out.println("<script type='text/javascript'>");
			out.println("alert('处理成功!');");
			out.println("self.location=document.referrer;");
			out.println("</script>");
		}else{
			out.println("<script type='text/javascript'>");
			out.println("alert('处理失败!')");
			out.println("self.location=document.referrer;");
			out.println("</script>");
		}
	}
}
