package me.chong.sharebooksserver.domain;

import me.chong.sharebooksserver.entity.BookClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookClassRepository extends JpaRepository<BookClass, Long> {
}
