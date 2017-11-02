/**
 *  UserServiceImpl TODO()
 */
package cn.smbms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.smbms.mapper.UserMapper;
import cn.smbms.pojo.Role;
import cn.smbms.pojo.User;
import cn.smbms.service.UserService;

/**
 * @author ggy
 *
 */
@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
	public User findByName(String name) {
		return userMapper.findByName(name);
	}
	
	public List<Role> findByRoleAll() {
		return userMapper.findByRoleAll();
	}
	
	public Integer saveUser(User user) {
		return userMapper.saveUser(user);
	}

}
