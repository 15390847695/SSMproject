package com.heit.SSM.service.impl;

import com.heit.SSM.mapper.tbadminMapper;
import com.heit.SSM.mapper.tbmenusMapper;
import com.heit.SSM.model.tbadmin;
import com.heit.SSM.model.tbmenus;
import com.heit.SSM.resp.MenuResp;
import com.heit.SSM.resp.MenusData;
import com.heit.SSM.service.IMenuServices;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
@Service
public class IMenusServiceImpl implements IMenuServices {

//    @Autowired
//    private tbmenus tbmenu;
   @Autowired
   private tbadminMapper tbadminMapper1;

   @Autowired
   private tbmenusMapper tbmenusMapper;
//    private MenuResp menuResp;
    @Override
    public MenuResp getMenus() {
        MenuResp menuResp=new MenuResp();
      List<tbmenus> tbmenusList= tbmenusMapper.queryByParentId();
      if(StringUtils.isEmpty(tbmenusList)){
          return menuResp;
      }
      List<MenusData> data =new ArrayList<MenusData>();
      for (tbmenus menus:tbmenusList){
          MenusData menusData=new MenusData();
          BeanUtils.copyProperties(menus,menusData);

          //查询子菜单

          Long menuId=menus.getMenuId();
          List<tbmenus> childMenus=tbmenusMapper.queryChildMenus(menuId);
          List<MenusData> list =new ArrayList<MenusData>();
          for(tbmenus m:childMenus){
              MenusData childMenu=new MenusData();
              BeanUtils.copyProperties(m,childMenu);
              list.add(childMenu);
          }
          menusData.setList(list);
          data.add(menusData);

      }
        menuResp.setData(data);
        menuResp.setCode(0);
        menuResp.setMsg("查询成功");

        return menuResp;
    }

    @Override
    public List<tbadmin> queryAll() {


        return tbadminMapper1.queryAllUser();
    }


}
