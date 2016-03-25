package edu.hdu.lab.checkIn.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.hdu.lab.checkIn.model.Record;
import edu.hdu.lab.checkIn.model.StudentDayRecord;
import edu.hdu.lab.checkIn.model.User;
import edu.hdu.lab.checkIn.model.UserRelation;
import edu.hdu.lab.checkIn.services.UserRelationService;
import edu.hdu.lab.checkIn.services.UserService;
import edu.hdu.lab.checkIn.utils.JsonUtils;
import edu.hdu.lab.checkIn.utils.WebUtils;

@Controller
public class UserRelationController {

	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	private UserRelationService userRelationService;	
	@Autowired
	private UserService userService;

	@ResponseBody
	@RequestMapping(value = "/user/newRelation", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String addRelation(@RequestParam("teacher_id") Integer teacher_id,
			@RequestParam("student_id") Integer student_id) {
		UserRelation record = new UserRelation();
		record.setStudentId(student_id);
		record.setTeacherId(teacher_id);
		List<UserRelation> users = userRelationService.getUsers(record);
		logger.debug(users);
		if(users==null||users.size()==0){
			return WebUtils.generateResult(userRelationService.addUserRelation(record));
		}else{
			return WebUtils.generateResult(0);
		}
			
	}
	
	@ResponseBody
	@RequestMapping(value = "/user/delRelation", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String delRelation(@RequestParam("teacher_id") Integer teacher_id,
			@RequestParam("student_id") Integer student_id) {
		UserRelation record = new UserRelation();
		record.setStudentId(student_id);
		record.setTeacherId(teacher_id);
		return WebUtils.generateResult(userRelationService.deleteUserRelation(record));
	}
	
	@ResponseBody
	@RequestMapping(value = "/user/getRelationStudent", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String getRelation(@RequestParam("teacher_id") Integer teacher_id) {
		Map<String, Object> resultMap = new HashMap<String, Object>(2);
		int resultCode = 0;
		List<UserRelation> user = userRelationService.getUser(teacher_id);
		List<User> users =new ArrayList<User>();
		if(user!=null&&user.size()>0){
			System.out.println(user.size());
			for (int i = 0; i < user.size(); i++) {
				Integer studentId = user.get(i).getStudentId();
				List<User> user2 = userService.getUser(studentId);
				users.add(user2.get(0));
			}
			for (User u : users) {
				u.setPwd(null);
			}
			resultMap.put("students", users);
			resultCode=1;
		}
		resultMap.put("resultCode", resultCode);
		return JsonUtils.createGson().toJson(resultMap);
	}
}
