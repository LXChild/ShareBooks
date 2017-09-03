package me.chong.sharebookscommon.domain;

import me.chong.sharebookscommon.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String name);

}
