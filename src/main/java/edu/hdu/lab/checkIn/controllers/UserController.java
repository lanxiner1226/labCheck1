package edu.hdu.lab.checkIn.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.hdu.lab.checkIn.model.Address;
import edu.hdu.lab.checkIn.model.User;
import edu.hdu.lab.checkIn.services.UserService;
import edu.hdu.lab.checkIn.utils.Constants;
import edu.hdu.lab.checkIn.utils.JsonUtils;
import edu.hdu.lab.checkIn.utils.WebUtils;

@Controller
public class UserController {

	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	private UserService userService;

	@ResponseBody
	@RequestMapping(value = "/user/login", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String authorize(@RequestParam("username") String username,
			@RequestParam("pwd") String pwd, HttpServletRequest request) {
		username = username.trim();
		pwd = pwd.trim();

		List<User> list = userService.getUser(username, pwd);
		int resultCode = list == null ? 0 : list.size();
		Map<String, Object> resultMap = new HashMap<String, Object>(2);
		if (resultCode > 0) {
			request.getSession().setAttribute("username", username);
			request.getSession().setAttribute("pwd", pwd);
			request.getSession().setAttribute("userId", list.get(0).getId());
			request.getSession().setAttribute("role", list.get(0).getRole());
			resultMap.put("id", list.get(0).getId());
			resultMap.put("role", list.get(0).getRole());
		}
		resultMap.put("resultCode", resultCode);

		return JsonUtils.createGson().toJson(resultMap);
	}
 /*
	@ResponseBody
	@RequestMapping(value = "/user/me", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String aboutMe(@RequestParam("id") Integer id,
			HttpServletRequest request) {
		// String username = request.getSession().getAttribute("username")
		// .toString();
		// String pwd = request.getSession().getAttribute("pwd").toString();

		List<User> list = userService.getUser(id);
		String resultMessage = "";
		int resultCode = list == null ? 0 : list.size();
		if (resultCode > 0) {
			User user = list.get(0);
			Address address = userService.getAddress(user.getAddressId());
			System.out.println(address.toString());
			user.setAddress(userService.getAddress(user.getAddressId())
					.getLocation());
			user.setPwd(null);
			resultMessage = JsonUtils.createGson().toJson(user);

		} else
			resultMessage = WebUtils.generateResult(resultCode);

		return resultMessage;
	}
*/
	
	//user define
	  @ResponseBody
	    @RequestMapping(value="/user/me", method=RequestMethod.GET, produces = "text/html;charset=UTF-8")    
	    public String aboutMe(HttpServletRequest request) {
//	        String username = request.getSession().getAttribute("username").toString();
//	        String pwd = request.getSession().getAttribute("pwd").toString();
//	        Integer role = (Integer)request.getSession().getAttribute("role");
          Integer id = (Integer)request.getSession().getAttribute("userId");
	        List<User> list = userService.getUser(id);
	        String resultMessage = "";
	        int resultCode = list == null ? 0 : list.size();
	        
	        if (resultCode > 0) 
	            resultMessage = JsonUtils.createGson().toJson(list.get(0));
	        else
	            resultMessage = WebUtils.generateResult(resultCode);
	        
	        return resultMessage;
	    }
	    
	
	
	@ResponseBody
	@RequestMapping(value = "/user/newTeacher", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String addTeachers(
			@RequestParam("username") String username,
			@RequestParam("pwd") String pwd,
			@RequestParam("name") String name,
			@RequestParam(value = "address_id", required = false) Integer address_id,
			@RequestParam(value = "phonecode", required = false) String phonecode,
			HttpServletRequest request) {

		Integer myrole = (Integer) request.getSession().getAttribute("role");
		int resultCode = 0;
		System.out.println(myrole);
		if (myrole == 3) {
			User u = new User();
			u.setUsername(username);

			List<User> users = userService.getUsers(u);
			if (users.size() > 0)
				return WebUtils
						.generateResult(Constants.RESULT_CODE_USER_ALREADY_EXISTS);
			u.setName(name);
			u.setPhonecode(phonecode);
			u.setRole(2);
			u.setPwd(pwd);
			u.setAddressId(1);

			resultCode = userService.addUser(u);
		}

		return WebUtils.generateResult(resultCode);
	}

	@ResponseBody
	@RequestMapping(value = "/user/newStudent", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String addStudent(
			@RequestParam("username") String username,
			@RequestParam("pwd") String pwd,
			@RequestParam("name") String name,
			@RequestParam(value = "address_id", required = false) Integer address_id,
			@RequestParam(value = "phonecode", required = false) String phonecode,
			HttpServletRequest request) {

		Integer myrole = (Integer) request.getSession().getAttribute("role");
		int resultCode = 0;
		if (myrole == 3) {
			User u = new User();
			u.setUsername(username);

			List<User> users = userService.getUsers(u);
			if (users.size() > 0)
				return WebUtils
						.generateResult(Constants.RESULT_CODE_USER_ALREADY_EXISTS);
			u.setName(name);
			u.setPhonecode(phonecode);
			u.setRole(1);
			u.setPwd(pwd);
			u.setAddressId(1);

			resultCode = userService.addUser(u);
		}

		return WebUtils.generateResult(resultCode);
	}

	@ResponseBody
	@RequestMapping(value = "/user/update", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String updateUser(
			@RequestParam("id") Integer id,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "pwd", required = false) String pwd,
			@RequestParam(value = "phonecode", required = false) String phonecode,
			HttpServletRequest request) {
		User user = new User();
		user.setId(id);
		user.setPwd(pwd);
		user.setName(name);
		user.setPhonecode(phonecode);

		int resultCode = userService.updateUser(user);

		return WebUtils.generateResult(resultCode);
	}

	@ResponseBody
	@RequestMapping(value = "/user/logout", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String logout(HttpServletRequest request) {
		// HttpSession session = request.getSession();
		//
		// session.setAttribute("user", null);

		return WebUtils.generateResult(1);
	}

	@ResponseBody
	@RequestMapping(value = "/user/delete/{id}", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String deleteUser(@PathVariable int id) {

		int resultCode = userService.deleteUser(id);

		return WebUtils.generateResult(resultCode);

	}

	@ResponseBody
	@RequestMapping(value = "/user/getTeachers", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String getTeachers() {
		User user = new User();
		user.setRole(2);
		List<User> users = userService.getUsersbyrole(user);
		for (User u : users) {
			u.setPwd(null);
		}
		return JsonUtils.createGson().toJson(users);
	}

	@ResponseBody
	@RequestMapping(value = "/user/getStudents", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String getStudents() {
		User user = new User();
		user.setRole(1);
		List<User> users = userService.getUsersbyrole(user);
		for (User u : users) {
			u.setPwd(null);
		}
		return JsonUtils.createGson().toJson(users);
	}


}
