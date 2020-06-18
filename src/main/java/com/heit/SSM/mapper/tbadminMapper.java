package com.heit.SSM.mapper;

import com.heit.SSM.model.tbadmin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface tbadminMapper {
    int deleteByPrimaryKey(Long id);

    int insert(tbadmin record);

    int insertSelective(tbadmin record);

    tbadmin selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(tbadmin record);

    int updateByPrimaryKey(tbadmin record);

    tbadmin selectByUserName(@Param("userName") String userName);

    public List<tbadmin> queryAllUser();
    //用户权限
    List<String> queryUserPerms(@Param("userName") String userName);

    List<tbadmin> queryUsers();
    List<tbadmin> queryAllUsers();
}