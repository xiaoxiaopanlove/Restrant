package com.service;

import java.util.List;

import com.entity.Meal;
import com.entity.Orderdts;
import com.entity.Orders;
import com.entity.User;

public interface UserService {

	/**
	 * 用户登录
	 * @param user
	 * @return
	 */
	public List<User> checkLogin(User user);

	/**
	 * 注册用户
	 * @param user
	 * @return
	 */
	public int addUser(User user);

	/**
	 * 验证用户名是否可用
	 * @param loginName
	 * @return
	 */
	public int checkloginName(String loginName);

	/**
	 * 修改个人用户信息
	 * @param user
	 * @return
	 */
	public int modifyUser(User user);

	/**
	 * 获取订购餐品信息
	 * @param mealId
	 * @return
	 */
	public Meal getMealInfo(int mealId);

	/**
	 * 新增订单
	 * @param orders
	 * @return
	 */
	public int addOrders(Orders orders);

	/**
	 * 新增订单明细信息
	 * @param orderdts
	 */
	public void addOrderdts(Orderdts orderdts);

	/**
	 * 获取用户订单
	 * @param id
	 * @return
	 */
	public List<Orders> getUserOrders(int userId);

	/**
	 * 删除订单
	 * @param oId
	 * @return
	 */
	public int deleteOrders(int oId);

	/**
	 * 订单详情
	 * @param oId
	 * @return
	 */
	public List<Orderdts> getOrderdts(int oId);
	
}
