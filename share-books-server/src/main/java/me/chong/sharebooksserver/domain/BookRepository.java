package me.chong.sharebooksserver.domain;

import me.chong.sharebooksserver.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByClassId(Long classId);

    List<Book> findByName(String name);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Book set quantity =:quantity, updateTime=:updateTime where id =:id")
    void update(@Param("id") Long id, @Param("quantity") Integer quantity, @Param("updateTime") Date updateTime);
}
