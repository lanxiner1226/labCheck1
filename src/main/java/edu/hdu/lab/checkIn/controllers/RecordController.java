package edu.hdu.lab.checkIn.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import edu.hdu.lab.checkIn.services.RecordService;
import edu.hdu.lab.checkIn.services.UserRelationService;
import edu.hdu.lab.checkIn.services.UserService;
import edu.hdu.lab.checkIn.utils.JsonUtils;
import edu.hdu.lab.checkIn.utils.WebUtils;

@Controller
public class RecordController {

	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	private RecordService recordService;
	@Autowired
	private UserService userService;
	@Autowired
	private UserRelationService userRelationService;

	@ResponseBody
	@RequestMapping(value = "/record/getRecord", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String getRecord(@RequestParam("user_id") Integer user_id,
			HttpServletRequest request) {
		List<Record> list = recordService.getRecords(user_id);
		int resultCode = 0;
		Map<String, Object> resultMap = new HashMap<String, Object>(2);
		if (list != null && list.size() >= 1) {
			resultCode = 1;
			resultMap.put("record", list);
		} else if (list != null && list.size() == 0) {
			resultCode = 2;
		}

		resultMap.put("resultCode", resultCode);

		return JsonUtils.createGson().toJson(resultMap);
	}

	@ResponseBody
	@RequestMapping(value = "/record/getStudentDayRecord", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String getStudentRecord(@RequestParam("user_id") Integer user_id,
			HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>(2);
		int resultCode = 0;
		List<UserRelation> user = userRelationService.getUser(user_id);
		List<StudentDayRecord> dayRecords =new ArrayList<StudentDayRecord>();
		if(user!=null&&user.size()>0){
			System.out.println(user.size());
			for (int i = 0; i < user.size(); i++) {
				StudentDayRecord dayRecord= new StudentDayRecord();
				Integer studentId = user.get(i).getStudentId();
				List<User> user2 = userService.getUser(studentId);
				dayRecord.setName(user2.get(0).getName());
				List<Record> records = recordService.getRecord(studentId);
				if(records!=null&&records.size()>=2){
					records.subList(1, records.size()-1).clear();
				}
				dayRecord.setRecords(records);
				dayRecords.add(dayRecord);
			}
			resultMap.put("dayRecords", dayRecords);
			resultCode=1;
		}
		resultMap.put("resultCode", resultCode);
		
		return JsonUtils.createGson().toJson(resultMap);
	}

	@ResponseBody
	@RequestMapping(value = "/record/addRecord", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String addRecord(@RequestParam("user_id") Integer user_id,
			@RequestParam("phonecode") String phonecode,
			HttpServletRequest request) {
		int resultCode = 0;
		int verifyUser = userService.verifyUser(user_id, phonecode);
		if (verifyUser == 1) {
			Record record = new Record();
			record.setUserId(user_id);
			resultCode = recordService.addRecord(record);
		}
		return WebUtils.generateResult(resultCode);
	}

	// @ResponseBody
	// @RequestMapping(value = "/record/addRecord", method = RequestMethod.POST,
	// produces = "text/html;charset=UTF-8")
	// public String addRecord(@RequestParam("user_id") Integer user_id,
	// HttpServletRequest request){
	// Record record = new Record();
	// record.setUserId(user_id);
	// record.setRecordTime(Calendar.getInstance().getTime());
	// int resultCode = recordService.addRecord(record);
	//
	// return WebUtils.generateResult(resultCode);
	// }

	@ResponseBody
	@RequestMapping(value = "/record/getAllStudentDayRecord", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String getAllStudentRecord(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>(2);
		int resultCode = 0;
		User user1 = new User();
		user1.setRole(1);
		List<User> user = userService.getUsersbyrole(user1);
		List<StudentDayRecord> dayRecords =new ArrayList<StudentDayRecord>();
		
		if(user!=null&&user.size()>0){
			System.out.println(user.size());
			for (int i = 0; i < user.size(); i++) {
				StudentDayRecord dayRecord= new StudentDayRecord();
				Integer studentId = user.get(i).getId();
				List<User> user2 = userService.getUser(studentId);
				dayRecord.setName(user2.get(0).getName());
				List<Record> records = recordService.getRecord(studentId);
				if(records!=null&&records.size()>=2){
					records.subList(1, records.size()-1).clear();
				}
				dayRecord.setRecords(records);
				dayRecords.add(dayRecord);
			}
			resultMap.put("dayRecords", dayRecords);
			resultCode=1;
		}
		resultMap.put("resultCode", resultCode);
		
		return JsonUtils.createGson().toJson(resultMap);
	}
	
	@ResponseBody
	@RequestMapping(value = "/record/getAllTeacherDayRecord", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String getAllTeacherRecord(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>(2);
		int resultCode = 0;
		User user1 = new User();
		user1.setRole(2);
		List<User> user = userService.getUsersbyrole(user1);
		List<StudentDayRecord> dayRecords =new ArrayList<StudentDayRecord>();
		
		if(user!=null&&user.size()>0){
			System.out.println(user.size());
			for (int i = 0; i < user.size(); i++) {
				StudentDayRecord dayRecord= new StudentDayRecord();
				Integer studentId = user.get(i).getId();
				List<User> user2 = userService.getUser(studentId);
				dayRecord.setName(user2.get(0).getName());
				List<Record> records = recordService.getRecord(studentId);
				if(records!=null&&records.size()>=2){
					records.subList(1, records.size()-1).clear();
				}
				dayRecord.setRecords(records);
				dayRecords.add(dayRecord);
			}
			resultMap.put("dayRecords", dayRecords);
			resultCode=1;
		}
		resultMap.put("resultCode", resultCode);
		
		return JsonUtils.createGson().toJson(resultMap);
	}
}
