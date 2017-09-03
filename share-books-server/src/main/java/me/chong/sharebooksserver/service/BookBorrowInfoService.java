package me.chong.sharebooksserver.service;

import me.chong.sharebooksserver.domain.BookBorrowInfoRepository;
import me.chong.sharebooksserver.entity.BookBorrowInfo;
import me.chong.sharebooksserver.enums.BookBorrowStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BookBorrowInfoService {

    private final BookBorrowInfoRepository repository;

    @Autowired
    public BookBorrowInfoService(BookBorrowInfoRepository repository) {
        this.repository = repository;
    }

    public BookBorrowInfo save(BookBorrowInfo bookBorrowInfo) {
        return repository.save(bookBorrowInfo);
    }

    public void update (BookBorrowInfo bookBorrowInfo) {
        repository.update(bookBorrowInfo.getUserId(), bookBorrowInfo.getBookId(),
                bookBorrowInfo.getStatus(), bookBorrowInfo.getUpdateTime());
    }

    public List<BookBorrowInfo> findBorrowingInfo() {
        return repository.findByStatus(BookBorrowStatusEnum.BORROWING.getTag());
    }

    public List<BookBorrowInfo> findByStatus(String status) {
        return repository.findByStatus(status);
    }

    public List<BookBorrowInfo> findByUserId(Long userId) {
        List<BookBorrowInfo> borrowInfos = repository.findByUserId(userId);
        Stream<BookBorrowInfo> result = borrowInfos.stream().filter(info -> !BookBorrowStatusEnum.BACKED.getTag().equals(info.getStatus()));
        return result.collect(Collectors.toList());
    }
}
