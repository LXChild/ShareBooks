package me.chong.sharebooksserver.domain;

import me.chong.sharebooksserver.entity.BookBorrowInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface BookBorrowInfoRepository extends JpaRepository<BookBorrowInfo, Long> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update BookBorrowInfo set status =:status, updateTime=:updateTime where userId =:userId and bookId =:bookId")
    void update(@Param("userId") Long userId, @Param("bookId") Long bookId,
                @Param("status") String status, @Param("updateTime") Date updateTime);

    List<BookBorrowInfo> findByStatus(String status);

    List<BookBorrowInfo> findByUserId(Long userId);
}
