package com.heit.SSM.service;

import com.heit.SSM.resp.UserInfo;

import java.util.List;

public interface IUserService {
    public List<UserInfo> queryUserInfo();

    public List<UserInfo> queryAllUserInfo();
}
