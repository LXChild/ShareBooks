package me.chong.sharebooksserver.service;

import me.chong.sharebooksserver.domain.BookRepository;
import me.chong.sharebooksserver.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BookService {

    private final BookRepository repository;

    @Autowired
    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public Book save(Book book) {
        // TODO 验证书籍信息是否为空
        return repository.save(book);
    }

    public List<Book> findAll() {
        return repository.findAll();
    }

    public List<Book> findAllByClassId(Long classId) {
        return repository.findAllByClassId(classId);
    }

    public List<Book> findByName(String name) {
        return repository.findByName(name);
    }

    public void update(Book book) {
        repository.update(book.getId(), book.getQuantity(), new Date());
    }

    public Book findOne(Long id) {
        return repository.findOne(id);
    }
}

