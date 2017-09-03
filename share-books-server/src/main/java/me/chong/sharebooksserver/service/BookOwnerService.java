package me.chong.sharebooksserver.service;

import me.chong.sharebooksserver.domain.BookOwnerRepository;
import me.chong.sharebooksserver.entity.BookOwner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookOwnerService {

    private final BookOwnerRepository repository;

    @Autowired
    public BookOwnerService(BookOwnerRepository repository) {
        this.repository = repository;
    }

    public BookOwner save(BookOwner bookOwner) {
        // TODO 验证租借信息是否为空
        return repository.save(bookOwner);
    }

    public List<BookOwner> findByUserId(Long userId) {
        return repository.findByUserId(userId);
    }

}
