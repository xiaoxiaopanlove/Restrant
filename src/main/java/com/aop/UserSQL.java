package com.aop;

import org.apache.ibatis.jdbc.SQL;

import com.entity.User;

public class UserSQL {
	
	/**
	 * 注册用户
	 * @param user
	 * @return
	 */
	public String addUser(final User user){
		return new SQL(){
			{
				INSERT_INTO("users");
				if(user.getLoginName()!=null){
					VALUES("LoginName", "#{loginName}");
				}
				if(user.getLoginPwd()!=null){
					VALUES("LoginPwd", "#{loginPwd}");
				}
				if(user.getTrueName()!=null){
					VALUES("TrueName", "#{trueName}");
				}
				if(user.getEmail()!=null){
					VALUES("Email", "#{email}");
				}
				if(user.getPhone()!=null){
					VALUES("Phone", "#{phone}");
				}
				if(user.getAddress()!=null){
					VALUES("Address", "#{address}");
				}
			}
		}.toString();
	}
	
	/**
	 * 修改用户信息
	 * @param user
	 * @return
	 */
	public String modifyUser(final User user){
		return new SQL(){
			{
				UPDATE("users");
				if(user.getLoginName()!=null){
					SET("LoginName = #{loginName}");
				}
				if(user.getLoginPwd()!=null){
					SET("LoginPwd = #{loginPwd}");
				}
				if(user.getTrueName()!=null){
					SET("TrueName = #{trueName}");
				}
				if(user.getEmail()!=null){
					SET("Email = #{email}");
				}
				if(user.getPhone()!=null){
					SET("Phone = #{phone}");
				}
				if(user.getAddress()!=null){
					SET("Address = #{address}");
				}
				WHERE(" ID = #{id}");
			}
		}.toString();
		
	}
}
