package edu.hdu.lab.checkIn.serviceImpls;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.hdu.lab.checkIn.mapper.UserRelationMapper;
import edu.hdu.lab.checkIn.model.UserRelation;
import edu.hdu.lab.checkIn.model.UserRelationExample;
import edu.hdu.lab.checkIn.services.UserRelationService;

@Service("UserRelationService")
public class UserRelationServiceImpl implements UserRelationService {

	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	private UserRelationMapper userRelationMapper;

	@Override
	public int addUserRelation(UserRelation user) {
		return userRelationMapper.insert(user);
	}

	@Override
	public int updateUserRelation(UserRelation user) {
		return 0;
	}

	@Override
	public int deleteUserRelation(UserRelation user) {
		UserRelationExample example=new UserRelationExample();
		example.createCriteria().andStudentIdEqualTo(user.getStudentId()).andTeacherIdEqualTo(user.getTeacherId());
		return userRelationMapper.deleteByExample(example);
	}

	@Override
	public List<UserRelation> getUsers(UserRelation user) {
		UserRelationExample example=new UserRelationExample();
		example.createCriteria().andTeacherIdEqualTo(user.getTeacherId())
		.andStudentIdEqualTo(user.getStudentId());
		return userRelationMapper.selectByExample(example);
	}

	@Override
	public List<UserRelation> getUser(int id) {
		UserRelationExample example=new UserRelationExample();
		example.createCriteria().andTeacherIdEqualTo(id);
		return userRelationMapper.selectByExample(example);
	}

}
