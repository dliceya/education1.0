package com.bishe.system.dao;

import com.bishe.framework.domain.system.Role;
import com.bishe.framework.domain.system.request.QueryRoleRequest;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface RoleDao {
    //添加系统角色
    @Insert("insert into bishe_role value(" +
            "#{rid}, #{roleName}, #{roleCode}, #{status}, #{createTime}, #{updateTime}, #{remark})")
    int addRole(Role role);

    //根据角色id删除角色
    @Delete("delete from bishe_role where rid = #{rid}")
    int delRole(String rid);

    //更新系统角色
    @Update("update bishe_role SET" +
            " roleName=#{roleName}, roleCode=#{roleCode}, status=#{status}, remark=#{remark}" +
            " where rid = #{rid}")
    int updateRole(Role role);
    //查询系统角色列表
    @Select(value = {" <script>" +
            " SELECT * from bishe_role " +
            " <where> 1=1 " +
            " <if test=\"roleName != null\"> AND roleName like '%${roleName}%'</if> " +
            " <if test=\"roleCode != null\" > AND roleCode=#{roleCode}</if> " +
            " <if test=\"status != null\" > AND a.status=#{status}</if> " +
            " <if test=\"beginTime != null &amp;&amp; endTime != null\"> AND createtime between #{beginTime} and #{endTime}</if>" +
            " </where>" +
            " <if test=\"pageNum != null\"> LIMIT #{pageNum}, #{pageSize}</if> " +
            " </script>"})
    List<Role> getRoleList(QueryRoleRequest pageRequest);

    //根据id查询系统角色
    @Select("select * from bishe_role where rid = #{rid}")
    Role getRoleById(String rid);

    //查询total
    @Select(value = {" <script>" +
            " SELECT count(rid) from bishe_role " +
            " <where> 1=1 " +
            " <if test=\"roleName != null\"> AND roleName like '%${roleName}%'</if> " +
            " <if test=\"roleCode != null\" > AND roleCode=#{roleCode}</if> " +
            " <if test=\"status != null\" > AND a.status=#{status}</if> " +
            " <if test=\"beginTime != null &amp;&amp; endTime != null\"> AND createtime between #{beginTime} and #{endTime}</if>" +
            " </where>" +
            " </script>"})
    int getTotal(QueryRoleRequest pageRequest);

    @Select("select * from bishe_role where status = 1")
    List<Role> getRoleSelect();
}
