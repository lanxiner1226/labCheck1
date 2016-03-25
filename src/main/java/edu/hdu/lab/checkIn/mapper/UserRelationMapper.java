package edu.hdu.lab.checkIn.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import edu.hdu.lab.checkIn.model.UserRelation;
import edu.hdu.lab.checkIn.model.UserRelationExample;

public interface UserRelationMapper {
    int countByExample(UserRelationExample example);

    int deleteByExample(UserRelationExample example);

    int insert(UserRelation record);

    int insertSelective(UserRelation record);

    List<UserRelation> selectByExample(UserRelationExample example);

    int updateByExampleSelective(@Param("record") UserRelation record, @Param("example") UserRelationExample example);

    int updateByExample(@Param("record") UserRelation record, @Param("example") UserRelationExample example);
}