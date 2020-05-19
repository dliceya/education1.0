package com.bishe.system.dao;

import com.bishe.framework.domain.record.Record;
import com.bishe.framework.domain.system.request.QueryRecordRequest;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface RecordDao {
    //分页查询履历记录
    @Select(value = {" <script>" +
            " SELECT * FROM bishe_record" +
            " <where> 1=1 " +
            " <if test=\"bid != null\"> AND bid = #{bid} </if> " +
            " <if test=\"ename != null\" > AND eid = #{ename} </if> " +
            " <if test=\"type != null\" > AND type = #{type} </if> " +
            " <if test=\"beginTime != null &amp;&amp; endTime != null\"> AND createtime between #{beginTime} and #{endTime}</if>" +
            " </where>" +
            " <if test=\"pageNum != null\"> LIMIT #{pageNum}, #{pageSize}</if> " +
            " </script>"})
    List<Record> getList(QueryRecordRequest request);

    //条件查询总数
    //获取总记录数
    @Select(value = {" <script>" +
            " SELECT count(rid) FROM bishe_record" +
            " <where> 1=1 " +
            " <if test=\"bid != null\"> AND bid = #{bid} </if> " +
            " <if test=\"ename != null\" > AND eid = #{ename} </if> " +
            " <if test=\"type != null\" > AND type = #{type} </if> " +
            " <if test=\"beginTime != null &amp;&amp; endTime != null\"> AND createtime between #{beginTime} and #{endTime}</if>" +
            " </where>" +
            " </script>"})
    int getTotal();

    //添加履历记录
    @Insert("insert into bishe_record(rid, bid, createTime, eid, type, time, " +
            " user, updateTime, beginTime) " +
            "value(#{rid}, #{bid}, #{createTime}, #{eid}, #{type}, #{time}, " +
            " #{user}, #{updateTime}, #{beginTime})")
    int addRecord(Record record);

    //删除履历记录
    @Delete("delete from bishe_record where rid=#{rid}")
    int delRecord(String rid);

    @Select("select eid from bishe_equip where name like '%${value}%'")
    List<String> getEquipId(String ename);

    @Select("select name from bishe_equip where eid=#{eid}")
    String getEquipName(String eid);

    //结束履历状态
    @Update("update bishe_record set time=#{time}, endTime = #{endTime} where rid=#{rid}")
    boolean endStatus(long time, Date endTime, String rid);

    @Select("select * from bishe_record where rid = #{rid}")
    Record getRecord(String rid);
}
