package com.pro.shape.main.bussiness.impl;

import java.sql.SQLException;

import com.pro.shape.main.bussiness.IUserManagerService;
import com.pro.shape.main.dao.CommonDao;
import com.pro.shape.main.model.LoginUsers;

public class UserManagerServiceImpl implements IUserManagerService {

	public int registerUser(LoginUsers user) {
		int flag = 0;
		try {
			//Judge user exist
			boolean existFlag = CommonDao.isInfoExits(user.getUsername());
			if(existFlag){
				return flag;
			}
			CommonDao.addInfo(user);
			flag =1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 1;
	}
	
	

}
