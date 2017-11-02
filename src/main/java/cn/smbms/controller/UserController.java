/**
 *  UserController TODO()
 */
package cn.smbms.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.codehaus.jackson.annotate.JsonAnyGetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.smbms.pojo.Role;
import cn.smbms.pojo.User;
import cn.smbms.service.UserService;

/**
 * @author ggy
 *
 */
@Controller
public class UserController {
		@Autowired
		private UserService userService;
		public void setUserService(UserService userService) {
			this.userService = userService;
		}
		/**
		 * login get TODO(get方法提交只是用来跳转login界面的)
		 * @return
		 */
		@RequestMapping(value="login.html"
					,method=RequestMethod.GET)
		public String login(){
			return "../login";
		}
		/**
		 * islogin TODO(post提交用来做用户登录方法实现的)
		 * @param model
		 * @param userCode
		 * @param userPassword
		 * @param session
		 * @return
		 */
		@RequestMapping(value="login.html"
				,method=RequestMethod.POST)
		public String islogin(Model model,
				@RequestParam("userCode")String userCode,
				@RequestParam("userPassword")String userPassword,
				HttpSession session){
			User user=userService.findByName(userCode);
			if(user!=null){
				if(user.getUserPassword().equals(userPassword)){
					if(session.getAttribute("USER_CODE")!=null){
						session.removeAttribute("USER_CODE");
					}
					session.setAttribute("USER_CODE", user);
					session.setMaxInactiveInterval(60000);
					return "frame";
				}else {
					model.addAttribute("error", "抱歉密码不正确");
					return "../login";	
				}
			}else{
				model.addAttribute("error", "抱歉账户名不正确");
				return "../login";	
			}
			
		}
		/**
		 * userShow TODO(点击用户模块显示首页面信息)
		 * @param model
		 * @return
		 */
		@RequestMapping(value="user.html"
					,method=RequestMethod.GET)
		public String userShow(Model model){
			//这里应该有一个首页面的展示 Page分页对象 显示首页信息
			//用来实现分页展示
			return "userlist";
		}
		/**
		 * userAdd TODO(进去用户新增界面)
		 * @return
		 */
		@RequestMapping(value="userAdd.html"
				,method=RequestMethod.GET)
		public String userAdd(){
			//userAdd.html
			return "useradd";
		}
		/**
		 * roleList TODO(跳转添加页面的时候实现角色的展示)
		 * @return
		 */
	@RequestMapping("role.json")
	@ResponseBody
		public Object roleList(){
		List<Role> list=userService.findByRoleAll();
		System.out.println("--"+JSON.toJSONString(list));
		return JSON.toJSONString(list);
	}	
		/**
		 * 	getUserCode TODO(判断该用户编号是否存在)
		 * @param userCode
		 * @return
		 */
		@RequestMapping("getUserCode.json")
		@ResponseBody
		public Object getUserCode(@RequestParam("userCode")
			String userCode){
			User user=userService.findByName(userCode);
			JSONObject json=new JSONObject();
			if(user!=null){
				 json.put("userCode", "exist");
			}else{
				json.put("userCode", "success");
			}
			return json;
		}		
		@RequestMapping(value="userAdd.html",method=RequestMethod.POST)
		public String userAdd(Model model,User user,HttpSession session){
			System.out.println("------");
			if(session.getAttribute("USER_CODE")!=null){
				User u=(User)session.getAttribute("USER_CODE");
				user.setCreatedBy(u.getId());	
			}else {
				user.setCreatedBy(1);
			}
			user.setCreationDate(new Date());
			Integer id=userService.saveUser(user);
			if (id>0) {
				return "redirect:user.html";
			}else {
				model.addAttribute("message", "抱歉因为网络原因添加失败！");
				return "useradd";
			}
			
		}
}

