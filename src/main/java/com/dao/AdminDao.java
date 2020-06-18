package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.entity.Admin;

public interface AdminDao {
	/**
	 * 管理员登录
	 * @param admin
	 * @return
	 */
	@Select("select * from admin where LoginName=#{admin.loginName} and LoginPwd=#{admin.loginPwd}")
	public List<Admin> checkLogin(@Param("admin")Admin admin);

}
