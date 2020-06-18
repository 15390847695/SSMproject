package com.heit.SSM.mapper;

import com.heit.SSM.model.tbrolesmenusKey;

public interface tbrolesmenusMapper {
    int deleteByPrimaryKey(tbrolesmenusKey key);

    int insert(tbrolesmenusKey record);

    int insertSelective(tbrolesmenusKey record);
}