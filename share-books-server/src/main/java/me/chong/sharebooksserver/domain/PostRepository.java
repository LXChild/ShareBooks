package me.chong.sharebooksserver.domain;

import me.chong.sharebooksserver.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
