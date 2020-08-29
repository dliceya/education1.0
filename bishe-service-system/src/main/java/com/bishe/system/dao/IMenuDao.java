package com.bishe.system.dao;

import com.bishe.framework.domain.system.Menu;
import com.bishe.framework.domain.system.request.MenuListRequest;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface IMenuDao {
    //根据菜单id查询菜单
    @Select("select * from bishe_menu where parent = #{mid} && status = 1")
    List<Menu> getMenuListByMid(String mid);

    //多条件组合分页查询菜单
    @Select(value = {" <script>" +
            " SELECT * FROM bishe_menu" +
            " <where> 1=1 " +
            " <if test=\"name != null\"> AND name like '%${name}%' </if> " +
            " <if test=\"component != null\" > AND component like '%${component}%' </if> " +
            " <if test=\"status != null\" > AND status=#{status}</if> " +
            " <if test=\"beginTime != null &amp;&amp; endTime != null\"> AND createtime between #{beginTime} and #{endTime}</if>" +
            " </where>" +
            " <if test=\"pageNum != null\"> LIMIT #{pageNum}, #{pageSize}</if> " +
            " </script>"})
    List<Menu> getList(MenuListRequest request);

    //获取总记录数
    @Select(value = {" <script>" +
            " SELECT count(mid) FROM bishe_menu" +
            " <where> 1=1 " +
            " <if test=\"name != null\"> AND name like '%${name}%' </if> " +
            " <if test=\"component != null\" > AND component like '%${component}%' </if> " +
            " <if test=\"status != null\" > AND status=#{status}</if> " +
            " <if test=\"beginTime != null &amp;&amp; endTime != null\"> AND createtime between #{beginTime} and #{endTime}</if>" +
            " </where>" +
            " </script>"})
    int getTotal();

    //根据id查询菜单名
    @Select("select name from bishe_menu where mid = #{mid}")
    String getNameByMid(String mid);

    //新增菜单
    @Insert("insert into bishe_menu value(" +
            " #{mid}, #{name}, #{parent}, #{orderNum}, #{component}, #{icon}," +
            " #{status}, #{createTime}, #{updateTime}, #{createBy}, #{remark})")
    int addMenu(Menu menu);

    //删除菜单
    @Delete("delete from bishe_menu where mid = #{mid}")
    int delMenu(String mid);

    //更新菜单
    @Update("update bishe_menu SET" +
            " name = #{name}, parent=#{parent}, component=#{component}, icon=#{icon}, " +
            " status=#{status}, updateTime=#{updateTime}, remark=#{remark}" +
            " where mid = #{mid}")
    int updateMenu(Menu menu);

    //根据id查询菜单
    @Select("select * from bishe_menu where mid = #{mid}")
    Menu getMenuByid(String mid);

    //修改角色状态
    @Update("update bishe_menu set status=#{status} where mid=#{mid}")
    boolean changeStatus(String mid, String status);

    @Select("select count(*) from bishe_menu")
    int getMenuListTotal();
}
