package me.chong.sharebooksserver.domain;

import me.chong.sharebooksserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String name);

}
