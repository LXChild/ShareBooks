package me.chong.sharebooksserver.domain;


import me.chong.sharebooksserver.entity.BookOwner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookOwnerRepository extends JpaRepository<BookOwner, Long> {

    List<BookOwner> findByUserId(Long userId);
}
