package com.bishe.system.controller;

import com.bishe.api.system.BookControllerApi;
import com.bishe.framework.domain.system.response.QueryBookResponse;
import com.bishe.framework.model.response.ResponseResult;
import com.bishe.system.service.BookService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookController implements BookControllerApi {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping("/bookList")
    @PreAuthorize("hasAuthority('system:book:list')")
    public QueryBookResponse getBook(@RequestParam String uid){
        return bookService.getBookById(uid);
    }

    @PreAuthorize("hasAuthority('system:book:add')")
    @RequestMapping("/addBook")
    public ResponseResult addBook(@RequestParam("name")String name, @RequestParam("createBy")String createBy){
        return bookService.addBook(name, createBy);
    }

    @PreAuthorize("hasAuthority('system:book:del')")
    @RequestMapping("/delBook")
    public ResponseResult delBook(@RequestParam String bid){
        return bookService.delBook(bid);
    }

    @RequestMapping("/updateBook")
    @PreAuthorize("hasAuthority('system:book:update')")
    public ResponseResult updateBook(@RequestParam("bid")String bid, @RequestParam("name")String name){
        return bookService.updateBook(bid, name);
    }
}
