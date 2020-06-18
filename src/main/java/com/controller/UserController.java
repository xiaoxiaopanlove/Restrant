package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.entity.Meal;
import com.entity.Orderdts;
import com.entity.Orders;
import com.entity.ShopCart;
import com.entity.User;
import com.service.UserService;
import com.tool.Content;

@Controller
public class UserController {
	@Autowired
	UserService userService;

	/**
	 * 
	 * 用户登录
	 * @param user
	 * @param model
	 * @param session
	 * @throws IOException 
	 */
	@RequestMapping(value="user_login",method=RequestMethod.POST)
	public void user_login(
			@ModelAttribute User user,
			Model model,
			HttpSession session,
			HttpServletResponse response
			) throws IOException{
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		List<User> users=userService.checkLogin(user);
		if(users.size()>0){
			session.setAttribute("user", users.get(0));
			out.println("<script type='text/javascript'>");
			out.println("alert('登录成功!');location.href='"+session.getAttribute("url")+"';");
			out.println("</script>");
		}else{
			out.println("<script type='text/javascript'>");
			out.println("alert('登录失败!请输入正确的用户名和密码！');");
			out.println("location.href=history.go(-1);");
			out.println("</script>");
		}
	}
	
	/**
	 * 跳转到用户注册页面
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/to_register")
	public String to_register(
			HttpServletRequest request,
			HttpSession session
			){
		if(request.getHeader("Referer").contains("show")){
			session.setAttribute("url", request.getHeader("Referer"));
		}
		return "register";
	}
	
	/**
	 * 验证用户名是否可用
	 * @param loginName
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/checkloginName")
	public void checkloginName(
			@RequestParam("loginName") String loginName,
			HttpServletResponse response
		) throws IOException{
		String result=null;
		if(loginName==null||loginName==""){
			result="kong";
		}else{
			int row=userService.checkloginName(loginName);
			if(row==0){
				result="yes";
			}else{
				result="no";
			}
		}
		response.getWriter().print(result);
	}
	
	/**
	 * 注册用户
	 * @param user
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/register")
	public void register(
			@ModelAttribute User user,
			HttpServletResponse response,
			HttpServletRequest request
			) throws IOException{
		/*System.out.println(request.getContextPath());*/
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		int row = userService.addUser(user);
		if(row>0){
			out.println("<script type='text/javascript'>");
			out.println("alert('注册成功!');location.href='"+request.getContextPath()+"/to_login?role=user'");
			out.println("</script>");
		}else{
			out.println("<script type='text/javascript'>");
			out.println("alert('注册失败!')");
			out.println("</script>");
		}
	}
	/**
	 * 跳转修改用户信息页面
	 * @return
	 */
	@RequestMapping(value="/update_userInfo")
	public String update_userInfo(){
		return "modifyMyInfo";
	}
	
	/**
	 * 修改用户信息
	 * @param user
	 * @param response
	 * @param request
	 * @param session
	 * @throws IOException
	 */
	@RequestMapping(value="/modifyUser")
	public void modifyUser(
			@ModelAttribute User user,
			HttpServletResponse response,
			HttpServletRequest request,
			HttpSession session
			) throws IOException{
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		int row = userService.modifyUser(user);
		if(row>0){
			session.removeAttribute("user");
			out.println("<script type='text/javascript'>");
			out.println("alert('修改成功!');location.href='"+request.getContextPath()+"/to_login?role=user'");
			out.println("</script>");
		}else{
			out.println("<script type='text/javascript'>");
			out.println("alert('修改失败!')");
			out.println("</script>");
		}
	}
	
	/**
	 * 将餐品添加至购物车
	 * @param session
	 * @throws IOException 
	 */
	@RequestMapping(value="/add_to_shopcart",method=RequestMethod.POST)
	public void add_to_shopcart(HttpSession session,
			@RequestParam(value = "mealId") int mealId,
			@RequestParam(value = "mealCount") int mealCount,
			HttpServletResponse response
			) throws IOException {
		Map<Integer, ShopCart> shopCart = (HashMap<Integer, ShopCart>) session.getAttribute("shopCart");
		boolean flag=false;
		if (shopCart == null) {
			shopCart = new HashMap<Integer, ShopCart>();
		} else {
			for (Integer key : shopCart.keySet()) {
				if (key == mealId) {
					ShopCart shop = shopCart.get(key);
					shop.setMealCount(shop.getMealCount() + mealCount);
					shopCart.put(mealId, shop);
					flag=true;
				}
			}
		}
		if(!flag){
			Meal meal = userService.getMealInfo(mealId);
			meal.setMealPrice(new BigDecimal(meal.getMealPrice().doubleValue()*Content.DISCOUNT_PRICE));
			ShopCart shop=new ShopCart(meal, mealCount);
			shopCart.put(mealId, shop);
		}
		session.setAttribute("shopCart", shopCart);
		response.getWriter().print("ture");
		
		/* for (Integer key : shopCart.keySet()) {
			   System.out.println("key= "+ key + " and value= " + shopCart.get(key).toString());
			  }*/
	}
	/**
	 * 跳转到购物车界面
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/toMyShopCart")
	public String toMyShopCart(
			HttpSession session,
			HttpServletRequest request
			){
		if(request.getHeader("Referer").contains("show")){
			session.setAttribute("showurl", request.getHeader("Referer"));
		}
		Map<Integer, ShopCart> shopCart = (HashMap<Integer, ShopCart>) session.getAttribute("shopCart");
		double sumprice=0;
		if(shopCart!=null){
			 for (Integer key : shopCart.keySet()) {
				 ShopCart shop = shopCart.get(key);
				 sumprice+=shop.getMealCount()*shop.getMeal().getMealPrice().doubleValue();
			 }
		}
		session.setAttribute("sumprice", sumprice);
		return "shopCart";
	}
	
	/**
	 * 修改数量
	 * @param session
	 * @param mealId
	 * @param mealCount
	 * @return
	 */
	@RequestMapping(value="/updateMealCount")
	public String updateMealCount(
			HttpSession session,
			@RequestParam("mealId") int mealId,
			@RequestParam("mealCount") int mealCount
			){
		Map<Integer, ShopCart> shopCart = (HashMap<Integer, ShopCart>) session.getAttribute("shopCart");
		for (Integer key : shopCart.keySet()) {
			if (key == mealId) {
				ShopCart shop = shopCart.get(key);
				shop.setMealCount(mealCount);
				shopCart.put(mealId, shop);
			}
		}
		return "redirect:toMyShopCart";
	}
	/**
	 * 删除购物车内的订单
	 * @param session
	 * @param mealId
	 * @return
	 */
	@RequestMapping("/deleteOrders")
	public String deleteOrders(
			HttpSession session,
			@RequestParam("mealId") int mealId
			){
		Map<Integer, ShopCart> shopCart = (HashMap<Integer, ShopCart>) session.getAttribute("shopCart");
		for (Integer key : shopCart.keySet()) {
			if (key == mealId) {
				shopCart.remove(key);
			}
		}
		return "redirect:toMyShopCart";
	}
	/**
	 * 清空购物车
	 * @param session
	 * @return
	 */
	@RequestMapping("/clearCart")
	public String clearCart(
			HttpSession session
			){
		Map<Integer, ShopCart> shopCart = (HashMap<Integer, ShopCart>) session.getAttribute("shopCart");
		shopCart.clear();
		return "redirect:toMyShopCart";
	}
	
	/**
	 * 生成订单
	 * @param request
	 * @param session
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/addOrders")
	public void addOrders(
			HttpServletRequest request,
			HttpSession session,
			HttpServletResponse response
			) throws IOException{
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		Map<Integer, ShopCart> shopCart = (HashMap<Integer, ShopCart>) session.getAttribute("shopCart");
		if(shopCart==null){
			out.println("<script type='text/javascript'>");
			out.println("alert('提交失败!您的购物车是空的...');");
			out.println("location.href=history.go(-1);");
			out.println("</script>");
		}else{
			Orders orders=new Orders();
			User user=(User)session.getAttribute("user");
			orders.setUserId(user.getId());
			orders.setOrderPrice(new BigDecimal(session.getAttribute("sumprice").toString()));
			int row=userService.addOrders(orders);
			if(row>0){
				Orderdts orderdts=null;
				for (Integer key : shopCart.keySet()) {
					orderdts =new Orderdts();
					orderdts.setoId(orders.getoId());
					orderdts.setMealId(key);
					orderdts.setMealPrice(shopCart.get(key).getMeal().getMealPrice());
					orderdts.setMealCount(shopCart.get(key).getMealCount());
					userService.addOrderdts(orderdts);
				}
				shopCart.clear();
				out.println("<script type='text/javascript'>");
				out.println("alert('生成订单成功！');location.href='"+request.getContextPath()+"/toMyShopCart'");
				out.println("</script>");
			}else{
				out.println("<script type='text/javascript'>");
				out.println("alert('生成订单失败，请联系管理员！');location.href='"+request.getContextPath()+"/toMyShopCart'");
				out.println("</script>");
			}
		}
	}
	/**
	 * 获取用户订单
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/toMyOrders")
	public String toMyOrders(
			Model model,
			HttpSession session
			){
		User user=(User) session.getAttribute("user");
		List<Orders> orders=userService.getUserOrders(user.getId());
		double ordersSum=0;
		for(Orders order:orders){
			ordersSum+=order.getOrderPrice().doubleValue();
		}
		model.addAttribute("ordersSum", ordersSum);
		model.addAttribute("orders", orders);
		return "myorders";
	}
	/**
	 * 删除订单
	 * @param oId
	 * @param response
	 * @param request
	 * @throws IOException
	 */
	@RequestMapping(value="/deleteOrderdts")
	public void deleteOrderdts(
		@RequestParam("oId")int oId,
		HttpServletResponse response,
		HttpServletRequest request
		) throws IOException{
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		int row=userService.deleteOrders(oId);
		if(row>0){
			out.println("<script type='text/javascript'>");
			out.println("alert('删除成功！');location.href='"+request.getContextPath()+"/toMyOrders'");
			out.println("</script>");
		}else{
			out.println("<script type='text/javascript'>");
			out.println("alert('删除失败！');");
			out.println("location.href=history.go(-1);");
			out.println("</script>");
		}
	}
	/**
	 * 订单明细
	 * @param model
	 * @param oId
	 * @return
	 */
	@RequestMapping(value="/toOrdersDetails")
	public String toOrdersDetails(
			Model model,
			@RequestParam("oId") int oId
			){
		List<Orderdts> orderdts=userService.getOrderdts(oId);
		double orderSum = 0;
		for(Orderdts order:orderdts){
			orderSum+=order.getMealPrice().doubleValue()*order.getMealCount();
		}
		model.addAttribute("orderSum", orderSum);
		model.addAttribute("orderdts", orderdts);
		return "myordersdetails";
	}
}
