package edu.hdu.lab.checkIn.mapper;

import edu.hdu.lab.checkIn.model.Address;
import edu.hdu.lab.checkIn.model.User;
import edu.hdu.lab.checkIn.model.UserExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
	List<User> getUsersByParams(User user);
	
	Address getAddressById(Integer id);
}