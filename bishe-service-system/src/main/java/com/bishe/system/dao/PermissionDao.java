package com.bishe.system.dao;

import com.bishe.framework.domain.system.Permission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PermissionDao {

    //根据父权限id查询权限
    @Select("select * from bishe_permission where parent = #{pid}")
    List<Permission> getPermissionListById(String pid);

    //插入授权信息
    @Insert("insert into bishe_role_permission value(#{pid}, #{rid})")
    boolean auth(String rid, String pid);

    //清除角色的授权信息
    @Delete("delete from bishe_role_permission where rid = #{rid}")
    void delAuth(String rid);

    //检查该权限是不是菜单级权限
    @Select("select count(*) from bishe_permission where pid=#{pid} && parent=0")
    int isMenu(String pid);

    //查询菜单级权限的所有子级权限
    @Select("select pid from bishe_permission where parent = #{pid}")
    List<String> getMenuChild(String pid);

    //查询当前角色的授权信息
    @Select("select pid from bishe_role_permission where rid=#{rid}")
    List<String> getCurrentPermission(String rid);

}
