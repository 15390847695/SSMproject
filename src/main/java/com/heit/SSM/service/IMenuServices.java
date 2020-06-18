package com.heit.SSM.service;

import com.heit.SSM.model.tbadmin;
import com.heit.SSM.resp.MenuResp;

import java.util.List;

public interface IMenuServices {
    public MenuResp getMenus();

    public List<tbadmin> queryAll();
}
