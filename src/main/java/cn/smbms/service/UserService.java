/**
 *  UserService TODO()
 */
package cn.smbms.service;

import java.util.List;
import cn.smbms.pojo.Role;
import cn.smbms.pojo.User;

/**
 * @author ggy
 *
 */
public interface UserService {
	
	User findByName(String name);
	List<Role> findByRoleAll();
	Integer saveUser(User user);
}	
