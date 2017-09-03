package me.chong.sharebookscommon.domain;

import me.chong.sharebookscommon.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByClassId(Long classId);
}
