package com.zhou.service;

import com.zhou.pojo.Result;
import com.zhou.pojo.User;

public interface UserService {

	Result login(User user);

	Result delete(Integer id);

}
