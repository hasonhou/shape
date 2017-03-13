package com.pro.shape.main.bussiness;

import com.pro.shape.main.model.LoginUsers;

/**
 * User management
 * @author Dylan
 * @date 2017-3-10
 */
public interface IUserManagerService {
	
	/**
	 * Register
	 * @param user
	 * @return 0-Failed, 1-Success
	 */
	public int registerUser(LoginUsers user);

}
