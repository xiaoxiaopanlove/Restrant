package com.aop;

import org.apache.ibatis.jdbc.SQL;

import com.entity.Page;

public class OrdersSQL {
	/**
	 * 获取订单总数
	 * @param page
	 * @return
	 */
	public String getOrderCount(final Page page){
		return new SQL(){
			{
				SELECT("count(OID)");
				FROM("orders");
				if(page.getoId()!=0){
					WHERE("OID =" + page.getoId());
				}
				if(page.getOrderState()!=null){
					WHERE("OrderState ='"+page.getOrderState()+"'");
				}
			}
		}.toString();
	}
	
	/**
	 * 获取订单分页信息
	 * @param page
	 * @return
	 */
	public String getOrders(final Page page){
		String sql= new SQL(){
			{
				SELECT("*");
				FROM("orders");
				if(page.getoId()!=0){
					WHERE("OID =" + page.getoId());
				}
				if(page.getOrderState()!=null){
					WHERE("OrderState ='"+page.getOrderState()+"'");
				}
			}
		}.toString();
		return sql+" limit "+(page.getPageNum()-1)*page.getPageSize()+","+page.getPageSize();
	}
}
