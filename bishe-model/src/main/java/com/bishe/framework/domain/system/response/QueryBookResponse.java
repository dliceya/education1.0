package com.bishe.framework.domain.system.response;

import com.bishe.framework.domain.book.Book;
import com.bishe.framework.model.response.ResponseResult;
import com.bishe.framework.model.response.ResultCode;
import lombok.Data;

import java.util.List;

@Data
public class QueryBookResponse extends ResponseResult {
    public List<Book> bookList;

    public QueryBookResponse(ResultCode resultCode, List<Book> bookList){
        super(resultCode);
        this.bookList = bookList;
    }
}
