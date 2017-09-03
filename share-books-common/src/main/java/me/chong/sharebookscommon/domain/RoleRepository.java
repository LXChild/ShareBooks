package me.chong.sharebookscommon.domain;

import me.chong.sharebookscommon.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
