package me.chong.sharebookscommon.domain;

import me.chong.sharebookscommon.entity.BookOwner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookOwnerRepository extends JpaRepository<BookOwner, Long> {
}
