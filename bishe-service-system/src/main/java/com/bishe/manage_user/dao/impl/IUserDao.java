package com.bishe.manage_user.dao.impl;

import com.bishe.framework.domain.system.Online;
import com.bishe.framework.domain.system.request.OnlineRequest;
import com.bishe.framework.domain.ucenter.BsUser;
import com.bishe.framework.domain.ucenter.ext.BsUserExt;
import com.bishe.framework.domain.ucenter.request.QueryPageRequest;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface IUserDao{

    //获取用户详细信息
    @Select("select * from bishe_user where username = #{username}")
    public BsUser getBsUserByUsername(String username);

    //查询用户权限信息
    @Select("SELECT " +
            "   a.permissionName " +
            "FROM " +
            "   bishe_permission a " +
            "LEFT JOIN " +
            "   bishe_role_permission b " +
            "ON a.pid = b.pid " +
            "WHERE b.rid = #{rid}")
    public List<String> getPermissionsByRid(String rid);

    //添加用户
    @Insert("insert into bishe_user " +
            " values(#{uid}, #{username}, #{password}, #{name}, #{qq}, #{rid}, #{pid}, " +
            " #{createTime}, #{updateTime}, #{email}, #{status}, #{loginIp}, #{loginDate}, #{phone}, #{remark})")
    public int addUser(BsUser user);

    //删除用户
    @Delete("delete from bishe_user where uid = #{uid}")
    public int delUser(String uid);

    //多条件组合分页查询
    @Select(value = {" <script>" +
            " SELECT a.* ,b.deptName FROM bishe_user a left join bishe_dept b ON a.pid=b.pid" +
            " <where> 1=1 " +
            " <if test=\"username != null\"> AND a.username like '%${username}%' </if> " +
            " <if test=\"phone != null\" > AND a.phone like '%${phone}%' </if> " +
            " <if test=\"status != null\" > AND a.status=#{status}</if> " +
            " <if test=\"pid != null\" >  AND a.pid=#{pid}</if>" +
            " <if test=\"beginTime != null &amp;&amp; endTime != null\"> AND createtime between #{beginTime} and #{endTime}</if>" +
            " </where>" +
            " <if test=\"pageNum != null\"> LIMIT #{pageNum}, #{pageSize}</if> " +
            " </script>"})
    public List<BsUserExt> getUserList(QueryPageRequest pageRequest);

    //多条件组合分页查询
    @Select(value = {" <script>" +
            " SELECT count(uid) " +
            " <if test=\"pid != null\"> ,b.deptName</if> " +
            " FROM bishe_user a " +
            " <if test=\"pid != null\"> left join bishe_dept b ON a.pid=b.pid</if> " +
            " <where> 1=1 " +
            " <if test=\"username != null\"> AND a.username like '%${username}%'</if> " +
            " <if test=\"phone != null\" > AND a.phone like '%${phone}%'</if> " +
            " <if test=\"status != null\" > AND a.status=#{status}</if> " +
            " <if test=\"pid != null\" >  AND a.pid=#{pid}</if>" +
            " <if test=\"beginTime != null &amp;&amp; endTime != null\"> AND createtime between #{beginTime} and #{endTime}</if>" +
            " </where>" +
            " </script>"})
    public int getTotal(QueryPageRequest pageRequest);

    @Select("select * from bishe_user where uid = #{uid}")
    public BsUser findUserById(String uid);

    //修改用户信息
    @Update("update bishe_user SET " +
            " name=#{name}, pid=#{pid}, phone=#{phone}, email=#{email}, username=#{username}," +
            " status=#{status}, rid=#{rid}, remark=#{remark}, updateTime=#{updateTime}" +
            " where uid=#{uid}")
    public int updateUser(BsUser user);

    //根据角色id获取角色名称
    @Select("select roleName from bishe_role where rid=#{rid}")
    String getRoleName(String rid);

    //改密码
    @Update("update bishe_user set password=#{pass} where uid=#{uid}")
    int reset(String uid, String pass);

    //获取在线用户列表
    //多条件组合分页查询
    @Select(value = {" <script>" +
            " SELECT * FROM online " +
            " <where> 1=1 " +
            " <if test=\"userName != null\"> AND userName like '%${userName}%'</if> " +
            " <if test=\"status != null\"> AND status = #{status}</if> " +
            " <if test=\"loginLocation != null\" > AND loginLocation like '%${loginLocation}%'</if> " +
            " </where>" +
            " <if test=\"pageNum != null\"> LIMIT #{pageNum}, #{pageSize}</if> " +
            " </script>"})
    List<Online> onlineList(OnlineRequest request);

    //获取用户总数
    @Select(value = {" <script>" +
            " SELECT count(*) FROM online " +
            " <where> 1=1 " +
            " <if test=\"userName != null\"> AND userName like '%${userName}%'</if> " +
            " <if test=\"status != null\"> AND status = #{status}</if> " +
            " <if test=\"loginLocation != null\" > AND loginLocation like '%${loginLocation}%'</if> " +
            " </where>" +
            " </script>"})
    int getOnlineTotal(OnlineRequest request);
}
