package com.heit.SSM.mapper;

import com.heit.SSM.model.tbroles;

public interface tbrolesMapper {
    int deleteByPrimaryKey(Long roleId);

    int insert(tbroles record);

    int insertSelective(tbroles record);

    tbroles selectByPrimaryKey(Long roleId);

    int updateByPrimaryKeySelective(tbroles record);

    int updateByPrimaryKey(tbroles record);
}