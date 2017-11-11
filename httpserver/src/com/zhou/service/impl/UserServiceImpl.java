package com.zhou.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.zhou.mapper.UserMapper;
import com.zhou.pojo.Result;
import com.zhou.pojo.User;
import com.zhou.pojo.UserExample;
import com.zhou.pojo.UserExample.Criteria;
import com.zhou.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public Result login(User user) {
		
		//校验用户名和密码不能为空
		if(StringUtils.isEmpty(user.getUsername())|| StringUtils.isEmpty(user.getPassword())){
			//用户名和密码为空
			return Result.build(400, "用户名或者密码错误");
		}
		
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(user.getUsername());
		criteria.andPasswordEqualTo(user.getPassword());
		
		//执行查询
		List<User> list = userMapper.selectByExample(example);
		if (list==null || list.size()==0) {
			return Result.build(400, "用户名或者密码错误");
		}
		return Result.build(200, "登陆成功");
	}

	@Override
	public Result delete(Integer id) {
		try {
			int i = userMapper.deleteByPrimaryKey(id);
			if (i == 1) {
				return Result.build(200, "删除成功");
			}else {
				return Result.build(400, "删除失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Result.build(500, "系统内部错误");
		}
	}
}
