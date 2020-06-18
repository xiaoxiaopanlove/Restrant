package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.MealDao;
import com.dao.OrderDao;
import com.dao.UserDao;
import com.entity.Meal;
import com.entity.Orderdts;
import com.entity.Orders;
import com.entity.User;
import com.service.UserService;
@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private MealDao mealDao;
	@Autowired
	private OrderDao orderDao;
	
	public OrderDao getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public MealDao getMealDao() {
		return mealDao;
	}

	public void setMealDao(MealDao mealDao) {
		this.mealDao = mealDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}


	public List<User> checkLogin(User user) {
		return userDao.checkLogin(user);
	}

	public int addUser(User user) {
		return userDao.addUser(user);
	}

	public int checkloginName(String loginName) {
		return userDao.checkloginName(loginName);
	}

	public int modifyUser(User user) {
		return userDao.modifyUser(user);
	}

	@Override
	public Meal getMealInfo(int mealId) {
		return mealDao.getMealInfo(mealId);
	}

	@Override
	public int addOrders(Orders orders) {
		return orderDao.addOrders(orders);
	}

	@Override
	public void addOrderdts(Orderdts orderdts) {
		 orderDao.addOrderdts(orderdts);
	}

	@Override
	public List<Orders> getUserOrders(int userId) {
		return orderDao.getUserOrders(userId);
	}

	@Override
	public int deleteOrders(int oId) {
		orderDao.deleteOrderts(oId);
		return orderDao.deleteOrders(oId);
	}

	@Override
	public List<Orderdts> getOrderdts(int oId) {
		return orderDao.getOrderdts(oId);
	}

}
