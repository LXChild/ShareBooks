package me.chong.sharebooksserver.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import me.chong.sharebooksserver.dataobject.Result;
import me.chong.sharebooksserver.entity.Book;
import me.chong.sharebooksserver.entity.BookBorrowInfo;
import me.chong.sharebooksserver.entity.User;
import me.chong.sharebooksserver.enums.BookBorrowStatusEnum;
import me.chong.sharebooksserver.enums.ResultCodeEnum;
import me.chong.sharebooksserver.service.BookBorrowInfoService;
import me.chong.sharebooksserver.service.BookService;
import me.chong.sharebooksserver.service.UserService;
import me.chong.sharebooksserver.utils.ResultUtils;
import me.chong.sharebooksserver.validator.BaseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/bookBorrow")
@Api(value = "书籍借入管理接口", protocols = "JSON")
public class BookBorrowInfoController {

    private final BookBorrowInfoService bookBorrowInfoService;

    private final UserService userService;

    private final BookService bookService;


    @Autowired
    public BookBorrowInfoController(BookBorrowInfoService bookBorrowInfoService, UserService userService, BookService bookService) {
        this.bookBorrowInfoService = bookBorrowInfoService;
        this.userService = userService;
        this.bookService = bookService;
    }

    @PostMapping("/bookId/{bookId}")
    @ApiParam(required = true, name = "bookId", value = "借入书籍Id")
    @ApiOperation(value = "添加书籍借入信息", httpMethod = "POST", response = Result.class, notes = "添加书籍借入信息")
    public Result save(HttpServletRequest request, @PathVariable("bookId") Long bookId) {
        String username = request.getRemoteUser();
        BaseValidator.notEmptyString(username);

        User user = userService.findByName(username);
        BaseValidator.notEmpty(user);
        BaseValidator.notEmpty(user.getId());

        BookBorrowInfo bookBorrowInfo = new BookBorrowInfo();
        bookBorrowInfo.setBookId(bookId);
        bookBorrowInfo.setBookName(bookService.findOne(bookId).getName());
        bookBorrowInfo.setUserId(user.getId());
        bookBorrowInfo.setUserName(user.getName());
        bookBorrowInfo.setBookNo(bookService.findOne(bookId).getName());
        bookBorrowInfo.setStatus(BookBorrowStatusEnum.BORROWING);
        bookBorrowInfo.setCreateTime(new Date());

        return ResultUtils.success(bookBorrowInfoService.save(bookBorrowInfo));
    }

    @PutMapping
    @ApiParam(required = true, name = "bookId", value = "借入书籍Id")
    @ApiOperation(value = "更新书籍借入信息", httpMethod = "PUT", response = Result.class, notes = "更新书籍借入信息")
    public Result update(HttpServletRequest request, Long bookId, String status) {
        String username = request.getRemoteUser();
        BaseValidator.notEmptyString(username);

        User user = userService.findByName(username);
        BaseValidator.notEmpty(user);
        BaseValidator.notEmpty(user.getId());

        BookBorrowInfo bookBorrowInfo = new BookBorrowInfo();
        bookBorrowInfo.setBookId(bookId);
        bookBorrowInfo.setUserId(user.getId());
        if (Objects.equals(status, BookBorrowStatusEnum.BORROWING.getTag())) {
            bookBorrowInfo.setStatus(BookBorrowStatusEnum.BORROWING);
        } else if (Objects.equals(status, BookBorrowStatusEnum.BORROW_APPLYING.getTag())) {
            bookBorrowInfo.setStatus(BookBorrowStatusEnum.BORROW_APPLYING);
        } else {
            return ResultUtils.error(ResultCodeEnum.NOT_MATCH, "租借状态错误");
        }
        bookBorrowInfo.setCreateTime(new Date());
        bookBorrowInfoService.update(bookBorrowInfo);
        return ResultUtils.success(null);
    }

    @GetMapping
    @ApiOperation(value = "查询所有书籍租借信息", httpMethod = "GET", response = Result.class, notes = "查询所有书籍租借信息")
    public Result borrowingInfo() {
        return ResultUtils.success(bookBorrowInfoService.findBorrowingInfo());
    }

    @GetMapping("/status/{status}")
    @ApiOperation(value = "根据状态查询书籍租借信息", httpMethod = "GET", response = Result.class, notes = "根据状态查询书籍租借信息")
    public Result findByStatus(@PathVariable("status") String status) {
        return ResultUtils.success(bookBorrowInfoService.findByStatus(status));
    }

    @GetMapping("/mine")
    @ApiOperation(value = "获取个人借入的书籍", httpMethod = "GET", response = Result.class, notes = "获取个人借入的书籍")
    public Result findBorrowingBooks(HttpServletRequest request) {
        String userName = request.getRemoteUser();
        User user = userService.findByName(userName);
        List<BookBorrowInfo> borrowInfos = bookBorrowInfoService.findByUserId(user.getId());
        List<Book> books = new ArrayList<>();
        for (BookBorrowInfo info : borrowInfos) {
            books.add(bookService.findOne(info.getBookId()));
        }
        return ResultUtils.success(books);
    }
}
