package com.bishe.system.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface IUploadDao {
    @Select("select a.beginTime from bishe_record a join bishe_equip b on a.eid = b.eid " +
            " where a.type = 4 && b.name = #{name}")
    List<String> getData(String name);

    @Select("select a.time from bishe_record a join bishe_equip b on a.eid = b.eid " +
            " where a.type = 4 && b.name = #{name}")
    List<String> getTime(String name);
}
