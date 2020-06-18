package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;

import com.aop.UserSQL;
import com.entity.User;

public interface UserDao {

	@Select("select * from users where LoginName=#{user.loginName} and LoginPwd=#{user.loginPwd}")
	public List<User> checkLogin(@Param("user")User user);

	@InsertProvider(type = UserSQL.class, method = "addUser")
	public int addUser(User user);

	@Select("select count(id) from users where LoginName=#{loginName}")
	public int checkloginName(@Param("loginName") String loginName);

	@UpdateProvider(type = UserSQL.class, method = "modifyUser")
	public int modifyUser(User user);

}
