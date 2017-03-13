package com.pro.shape.main.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pro.shape.main.bussiness.IUserManagerService;
import com.pro.shape.main.bussiness.impl.UserManagerServiceImpl;
import com.pro.shape.main.dao.CommonDao;
import com.pro.shape.main.model.LoginUsers;

/**
 * Register controller
 * @author Dylan
 * @date 2017-3-10
 */
@Controller
public class UserManagerController {
	
	IUserManagerService userManagerServiceImpl = new UserManagerServiceImpl();

	/**
	 * Register request method
	 * @param user
	 * @return result
	 */
	@RequestMapping("register")
	@ResponseBody
	public Map<String,Object> register(LoginUsers user){
		Map<String,Object> result = new HashMap<String,Object>();
		if(null!=user.getUsername() && null!=user.getPwd() && null!=user.getRealname()){
			int flag = userManagerServiceImpl.registerUser(user);
			result.put("flag", flag);
			result.put("msg", 1==flag?"注册成功":"注册失败");
		}else{
			result.put("flag", -1);
			result.put("msg", "请输入完整信息");
		}
		return result;
	}
	
	/**
	 * Query request method
	 * @return result
	 */
	@RequestMapping("query")
	@ResponseBody
	public List<LoginUsers> getAllUser(){
		List<LoginUsers> list = null;
		try {
			list = CommonDao.getAllUser();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
