package com.bishe.system.service;

import com.bishe.framework.domain.book.Book;
import com.bishe.framework.domain.system.response.QueryBookResponse;
import com.bishe.framework.model.response.CommonCode;
import com.bishe.framework.model.response.ResponseResult;
import com.bishe.framework.utils.IdUtils;
import com.bishe.system.dao.IBookDao;
import com.bishe.system.service.impl.IBookService;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Service
public class BookService implements IBookService {

    private final IBookDao iBookDao;
    private Calendar timeUtils = Calendar.getInstance(TimeZone.getTimeZone("GMT+8:00"));

    public BookService(IBookDao iBookDao) {
        this.iBookDao = iBookDao;
    }

    @Override
    public QueryBookResponse getBookById(String uid) {
        QueryBookResponse response;
        List<Book> bookList = iBookDao.getBookById(uid);
        if(bookList.size() > 0){
            response = new QueryBookResponse(CommonCode.SUCCESS, bookList);
        }else response = new QueryBookResponse(CommonCode.FAIL,null);

        return response;
    }

    @Override
    public ResponseResult addBook(String name, String createBy) {
        ResponseResult result;
        Book book = new Book();
        book.setBookName(name);
        book.setBid(IdUtils.simpleUUID());
        book.setCreateBy(createBy);
        book.setCreateTime(timeUtils.getTime());
        book.setUpdateTime(timeUtils.getTime());
        if(iBookDao.addBook(book) > 0){
            result = new ResponseResult(CommonCode.SUCCESS);
        }else result = new ResponseResult(CommonCode.FAIL);

        return result;
    }

    @Override
    public ResponseResult delBook(String bid) {
        ResponseResult result;
        if(iBookDao.delBook(bid) > 0){
            result = new ResponseResult(CommonCode.SUCCESS);
        }else result = new ResponseResult(CommonCode.FAIL);

        return result;
    }

    @Override
    public ResponseResult updateBook(String bid, String name) {
        ResponseResult result;
        Date updateTime = timeUtils.getTime();
        if(iBookDao.updateBook(bid, name, updateTime) > 0){
            result = new ResponseResult(CommonCode.SUCCESS);
        }else result = new ResponseResult(CommonCode.FAIL);
        return result;
    }
}
