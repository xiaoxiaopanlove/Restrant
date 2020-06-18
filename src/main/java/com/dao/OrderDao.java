package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.FetchType;

import com.aop.OrdersSQL;
import com.entity.Orderdts;
import com.entity.Orders;
import com.entity.Page;

@Mapper
public interface OrderDao {
	/**
	 * 验证要删除的mealId是都存在订单
	 * @param mealId
	 * @return
	 */
	@Select("select count(ODID) from orderdts where MealId=#{mealId}")
	public int checkmealId(@Param("mealId")int mealId);
	
	/**
	 * 获取订单总数
	 * @param page
	 * @return
	 */
	@SelectProvider(type=OrdersSQL.class,method="getOrderCount")
	public int getOrderCount(Page page);

	/**
	 * 获取订单分页信息
	 * @param page
	 * @return
	 */
	@SelectProvider(type=OrdersSQL.class,method="getOrders")
	public List<Orders> getOrders(Page page);

	/**
	 * 处理订单
	 * @param oId
	 * @return
	 */
	@Update("update orders set OrderState='已处理' where OID=#{oId}")
	public int handleOrders(@Param("oId")int oId);

	/**
	 * 生成订单
	 * @param orders
	 * @return
	 */
	@Insert("insert into orders (UserId,OrderTime,OrderState,OrderPrice) values (#{orders.userId},NOW(),'未处理',#{orders.orderPrice})")
	@Options(useGeneratedKeys = true, keyProperty = "orders.oId",keyColumn="OID")
	public int addOrders(@Param("orders")Orders orders);

	/**
	 * 生成订单详细信息
	 * @param orderdts
	 * @return
	 */
	@Insert("insert into orderdts (OID,MealID,MealPrice,MealCount) values (#{orderdts.oId},#{orderdts.mealId},#{orderdts.mealPrice},#{orderdts.mealCount})")
	public void addOrderdts(@Param("orderdts")Orderdts orderdts);

	/**
	 * 获取用户订单
	 * @param userId
	 * @return
	 */
	@Select("select * from orders where UserId=#{userId}")
	public List<Orders> getUserOrders(@Param("userId")int userId);

	/**
	 * 删除用户订单
	 * @param oId
	 * @return
	 */
	@Delete("delete from orders where OID=#{oId}")
	public int deleteOrders(@Param("oId")int oId);

	/**
	 * 获取订单详情
	 * @param oId
	 * @return
	 */
	@Select("select * from orderdts where OID=#{oId}")
	@Results({
		@Result(column="mealId",property="meal",
				one=@One(
						select="com.dao.MealDao.getMealInfo",
						fetchType=FetchType.EAGER
						))
	})
	public List<Orderdts> getOrderdts(@Param("oId")int oId);
	
	/**
	 * 删除用户订单
	 * @param oId
	 */
	@Delete("delete from orderdts where OID=#{oId}")
	public void deleteOrderts(@Param("oId")int oId);
}
