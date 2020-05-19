package com.bishe.system.dao;

import com.bishe.framework.domain.book.Book;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@Mapper
public interface IBookDao {

    //根据用户id查询履历本列表
    @Select("select * from bishe_book where createBy=#{uid}")
    List<Book> getBookById(String uid);

    //添加履历本
    @Insert("insert into bishe_book value(#{bid}, #{bookName}, #{createBy}, #{createTime}, #{updateTime})")
    int addBook(Book book);

    //删除履历本
    @Delete("delete from bishe_book where bid=#{bid}")
    int delBook(String bid);

    //更新履历本名
    @Update("update bishe_book set bookName=#{name}, updateTime=#{updateTime} where bid=#{bid}")
    int updateBook(String bid, String name, Date updateTime);
}
