package me.chong.sharebookscommon.domain;

import me.chong.sharebookscommon.entity.BookClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookClassRepository extends JpaRepository<BookClass, Long> {
}
