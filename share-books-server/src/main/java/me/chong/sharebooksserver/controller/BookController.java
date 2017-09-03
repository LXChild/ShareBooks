package me.chong.sharebooksserver.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import me.chong.sharebooksserver.dataobject.Result;
import me.chong.sharebooksserver.entity.Book;
import me.chong.sharebooksserver.entity.BookOwner;
import me.chong.sharebooksserver.entity.User;
import me.chong.sharebooksserver.enums.ResultCodeEnum;
import me.chong.sharebooksserver.service.BookClassService;
import me.chong.sharebooksserver.service.BookOwnerService;
import me.chong.sharebooksserver.service.BookService;
import me.chong.sharebooksserver.service.UserService;
import me.chong.sharebooksserver.utils.ExcelUtils;
import me.chong.sharebooksserver.utils.ResultUtils;
import me.chong.sharebooksserver.validator.BaseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/v1/books")
@Api(value = "书籍管理接口", protocols = "JSON")
public class BookController {

    private final BookService bookService;

    private final BookOwnerService bookOwnerService;

    private final UserService userService;

    private final BookClassService bookClassService;

    @Autowired
    public BookController(BookService bookService, BookOwnerService bookOwnerService, UserService userService, BookClassService bookClassService) {
        this.bookService = bookService;
        this.bookOwnerService = bookOwnerService;
        this.userService = userService;
        this.bookClassService = bookClassService;
    }


    @PostMapping
    @ApiParam(required = true, name = "book", value = "书籍")
    @ApiOperation(value = "添加书籍", httpMethod = "POST", response = Result.class, notes = "添加书籍")
    public Result save(HttpServletRequest request, @RequestBody @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtils.error(ResultCodeEnum.FAILED, bindingResult.getFieldError().getDefaultMessage());
        }

        List<Book> bookInDB = bookService.findByName(book.getName());
        Stream<Book> stream = bookInDB.stream().filter(e -> e.getAuthor().equals(book.getAuthor()));
        List<Book> books = stream.collect(Collectors.toList());
        Book result;
        if (!books.isEmpty()) {
            result = books.get(0);
            result.setQuantity(result.getQuantity() + 1);
            bookService.update(result);
        } else {
            book.setCreateTime(new Date());
            book.setClassName(bookClassService.findOne(book.getClassId()).getName());
            result = bookService.save(book);
        }

        String username = request.getRemoteUser();
        BaseValidator.notEmptyString(username);

        User user = userService.findByName(username);
        BaseValidator.notEmpty(user);
        BaseValidator.notEmpty(user.getId());

        BookOwner bookOwner = new BookOwner();
        bookOwner.setBookId(result.getId());
        bookOwner.setUserId(user.getId());
        bookOwner.setCreateTime(new Date());

        bookOwnerService.save(bookOwner);
        return ResultUtils.success(result);
    }

    @GetMapping("/name")
    @ApiParam(required = true, name = "name", value = "书名")
    @ApiOperation(value = "根据书名获取书籍", httpMethod = "GET", response = Result.class, notes = "根据书名获取书籍")
    public Result findByName(String name) {
        return ResultUtils.success(bookService.findByName(name));
    }

    @GetMapping
    @ApiOperation(value = "获取所有书籍", httpMethod = "GET", response = Result.class, notes = "获取所有书籍")
    public Result findAll() {
        return ResultUtils.success(bookService.findAll());
    }

    @GetMapping("/classId/{classId}")
    @ApiParam(required = true, name = "classId", value = "类别Id")
    @ApiOperation(value = "根据类别ID获取书籍", httpMethod = "GET", response = Result.class, notes = "根据类别ID获取书籍")
    public Result findAllByClassId(@PathVariable("classId") Long classId) {
        return ResultUtils.success(bookService.findAllByClassId(classId));
    }

    @GetMapping("/id/{id}")
    @ApiParam(required = true, name = "id", value = "id")
    @ApiOperation(value = "根据图书ID获取书籍", httpMethod = "GET", response = Result.class, notes = "根据图书ID获取书籍")
    public Result findOne(@PathVariable("id") Long id) {
        return ResultUtils.success(bookService.findOne(id));
    }

    @GetMapping("/xxx")
    public Result saveAll() {
        List<Book> books = ExcelUtils.getExcel("/Users/LXChild/Downloads/书籍清单2.xls");
        for (Book book : books) {
            bookService.save(book);
            BookOwner bookOwner = new BookOwner();
            bookOwner.setBookId(book.getId());
            bookOwner.setUserId(1L);
            bookOwner.setCreateTime(new Date());
            bookOwnerService.save(bookOwner);
        }
        return ResultUtils.success(books);
    }
}
