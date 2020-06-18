package com.heit.SSM.mapper;

import com.heit.SSM.model.tbmenus;
import com.heit.SSM.resp.MenuResp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface tbmenusMapper {
    int deleteByPrimaryKey(Long menuId);

    int insert(tbmenus record);

    int insertSelective(tbmenus record);

    tbmenus selectByPrimaryKey(Long menuId);

    int updateByPrimaryKeySelective(tbmenus record);

    int updateByPrimaryKey(tbmenus record);
   //一级菜单
    List<tbmenus> queryByParentId();
    //二级菜单
    List<tbmenus> queryChildMenus(@Param("parentId") Long parentId);
}