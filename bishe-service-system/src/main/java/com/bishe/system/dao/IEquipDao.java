package com.bishe.system.dao;

import com.bishe.framework.domain.equipment.Equipment;
import com.bishe.framework.domain.system.request.EquipListRequest;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface IEquipDao {
    //多条件组合分页查询装备
    @Select(value = {" <script>" +
            " SELECT * FROM bishe_equip" +
            " <where> 1=1 " +
            " <if test=\"name != null\"> AND name like '%${name}%' </if> " +
            " <if test=\"eNum != null\" > AND eNum like '%${eNum}%' </if> " +
            " <if test=\"type != null\" > AND type=#{type}</if> " +
            " <if test=\"beginTime != null &amp;&amp; endTime != null\"> AND createtime between #{beginTime} and #{endTime}</if>" +
            " </where>" +
            " <if test=\"pageNum != null\"> LIMIT #{pageNum}, #{pageSize}</if> " +
            " </script>"})
    List<Equipment> getList(EquipListRequest request);

    //获取总记录数
    @Select(value = {" <script>" +
            " SELECT count(eid) FROM bishe_equip" +
            " <where> 1=1 " +
            " <if test=\"name != null\"> AND name like '%${name}%' </if> " +
            " <if test=\"eNum != null\" > AND eNum like '%${eNum}%' </if> " +
            " <if test=\"type != null\" > AND type=#{type}</if> " +
            " <if test=\"beginTime != null &amp;&amp; endTime != null\"> AND createtime between #{beginTime} and #{endTime}</if>" +
            " </where>" +
            " </script>"})
    int getTotal();

    //根据id查询装备
    @Select("select * from bishe_equip where eid = #{eid}")
    Equipment getEquipByid(String eid);

    //根据部门id查询部门名称
    @Select("select deptName from bishe_dept where pid=#{byId}")
    String getDeptName(String byId);


    //新增装备
    @Insert("insert into bishe_equip value(" +
            " #{eid}, #{eNum}, #{name}, #{type}, #{byId}, #{deliverTime}," +
            " #{createTime}, #{life})")
    int addEquip(Equipment equipment);

    //更新装备
    @Update("update bishe_equip SET" +
            " eid = #{eid}, eNum=#{eNum}, name=#{name}, type=#{type}, " +
            " byId=#{byId}, deliverTime=#{deliverTime}, createTime=#{createTime}, life=#{life}" +
            " where eid = #{eid}")
    int updateEquip(Equipment equipment);

    //删除装备
    @Delete("delete from bishe_equip where eid = #{eid}")
    int delEquip(String eid);

    @Select("select count(eid) from bishe_equip where eNum=#{num}")
    int check(String num);
}
