package edu.hdu.lab.checkIn.serviceImpls;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.hdu.lab.checkIn.mapper.UserMapper;
import edu.hdu.lab.checkIn.model.Address;
import edu.hdu.lab.checkIn.model.User;
import edu.hdu.lab.checkIn.model.UserExample;
import edu.hdu.lab.checkIn.services.UserService;

@Service("UserService")
public class UserServiceImpl implements UserService {

	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	private UserMapper userMapper;

	@Override
	public List<User> getUser(String user, String pwd) {
		// String encryptedPwd = DigestUtils.sha256Hex(pwd);
		UserExample example = new UserExample();

		example.createCriteria().andUsernameEqualTo(user)
		.andPwdEqualTo(pwd);
		// .andPwdEqualTo(encryptedPwd);
		List<User> list = userMapper.selectByExample(example);

		return list;
	}

	@Override
	public List<User> getUser(int id) {
		// String encryptedPwd = DigestUtils.sha256Hex(pwd);
		UserExample example = new UserExample();
		
		example.createCriteria().andIdEqualTo(id);
		// .andPwdEqualTo(encryptedPwd);
		List<User> list = userMapper.selectByExample(example);

		return list;
	}

	
	@Override
	public int addUser(User user) {
//		if (user.getPwd() != null) {
//			user.setPwd(DigestUtils.sha256Hex(user.getPwd()));
//		}
		return userMapper.insert(user);
	}

	@Override
	public int updateUser(User user) {
//		if (user.getPwd() != null) {
//			user.setPwd(DigestUtils.sha256Hex(user.getPwd()));
//		}
		return userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public int verifyUser(int id,String phonecode) {
		int resultcode = 0;
		UserExample example = new UserExample();
		example.createCriteria().andPhonecodeEqualTo(phonecode)
		.andIdEqualTo(id);
		List<User> list = userMapper.selectByExample(example);
		if(list!=null&&list.size()>=1)
			resultcode=1;
		return resultcode;
	}

	
	@Override
	public int deleteUser(int id) {

		return userMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<User> getUsers(User user) {
		UserExample example = new UserExample();
		example.createCriteria().andUsernameEqualTo(user.getUsername());
		return userMapper.selectByExample(example);
	}

	@Override
	public Address getAddress(int id) {
		return userMapper.getAddressById(id);
	}

	@Override
	public List<User> getUsersbyrole(User user) {
		UserExample example = new UserExample();
		example.createCriteria().andRoleEqualTo(user.getRole());
		return userMapper.selectByExample(example);
	}
//user define
	@Override
	public List<User> getUserByName(User user) {
		UserExample example = new UserExample();
		example.createCriteria().andNameEqualTo(user.getName());
		return userMapper.selectByExample(example);
	}


}
