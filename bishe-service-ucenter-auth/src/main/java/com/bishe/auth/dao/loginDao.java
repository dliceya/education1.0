package com.bishe.auth.dao;

import com.bishe.framework.domain.system.Online;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface loginDao {
    @Update("update bishe_user set loginIp =#{ip} where username=#{username}")
    void setLoginIp(String username, String ip);

    //插入登录日志
    @Insert("insert into online value(#{tokenId}, #{userName}, #{deptName}, #{ipaddr}," +
            " #{loginLocation}, #{browser}, #{os}, #{loginTime}, #{status})")
    void writeLog(Online user);

}
