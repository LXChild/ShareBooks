package me.chong.sharebooksserver.service;

import me.chong.sharebooksserver.domain.BookClassRepository;
import me.chong.sharebooksserver.entity.BookClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookClassService {

    @Autowired
    private BookClassRepository repository;

    public BookClass findOne(Long id) {
        return repository.findOne(id);
    }

    public List<BookClass> findAll() {
        return repository.findAll();
    }
}
