package me.chong.sharebooksserver.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.chong.sharebooksserver.dataobject.Result;
import me.chong.sharebooksserver.entity.Book;
import me.chong.sharebooksserver.entity.BookOwner;
import me.chong.sharebooksserver.entity.User;
import me.chong.sharebooksserver.service.BookOwnerService;
import me.chong.sharebooksserver.service.BookService;
import me.chong.sharebooksserver.service.UserService;
import me.chong.sharebooksserver.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/bookLend")
@Api(value = "书籍借出管理接口", protocols = "JSON")
public class BookOwnerController {

    @Autowired
    private BookOwnerService bookOwnerService;

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @GetMapping("/mine")
    @ApiOperation(value = "获取个人共享的书籍", httpMethod = "GET", response = Result.class, notes = "获取个人共享的书籍")
    public Result findBorrowingBooks(HttpServletRequest request) {
        String userName = request.getRemoteUser();
        User user = userService.findByName(userName);
        List<BookOwner> owners = bookOwnerService.findByUserId(user.getId());
        List<Book> books = new ArrayList<>();
        for (BookOwner info : owners) {
            books.add(bookService.findOne(info.getBookId()));
        }
        return ResultUtils.success(books);
    }
}
