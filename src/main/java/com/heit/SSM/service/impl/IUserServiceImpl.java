package com.heit.SSM.service.impl;

import com.heit.SSM.mapper.tbadminMapper;
import com.heit.SSM.model.tbadmin;
import com.heit.SSM.resp.UserInfo;
import com.heit.SSM.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
@Service
public class IUserServiceImpl implements IUserService {

    @Autowired
    private tbadminMapper tbadminMapper1;
    @Override
    public List<UserInfo> queryUserInfo() {
        List<tbadmin> userlist=tbadminMapper1.queryUsers();
        List<UserInfo> userInfoList=new ArrayList<UserInfo>();
        if(!CollectionUtils.isEmpty(userlist)){
            for (tbadmin u:userlist){
                UserInfo userInfo=new UserInfo();
                userInfo.seteMail(u.geteMail());
                userInfo.setNickname(u.getUsername());
                userInfo.setPhone(u.getPhone());
                userInfoList.add(userInfo);


            }
        }
        return userInfoList;
    }

    @Override
    public List<UserInfo> queryAllUserInfo() {
        List<tbadmin> userlist=tbadminMapper1.queryAllUsers();
        List<UserInfo> userInfoList=new ArrayList<UserInfo>();
        if(!CollectionUtils.isEmpty(userlist)){
            for (tbadmin u:userlist){
                UserInfo userInfo=new UserInfo();
                userInfo.seteMail(u.geteMail());
                userInfo.setNickname(u.getUsername());
                userInfo.setPhone(u.getPhone());
                userInfoList.add(userInfo);


            }
        }
        return userInfoList;
    }
}
