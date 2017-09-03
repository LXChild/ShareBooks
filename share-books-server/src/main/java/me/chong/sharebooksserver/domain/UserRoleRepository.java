package me.chong.sharebooksserver.domain;

import me.chong.sharebooksserver.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    UserRole findByUserId(Long userId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update UserRole set roleId =:roleId, updateTime=:updateTime where userId =:userId")
    void update(@Param("userId") Long userId, @Param("roleId") Integer roleId, @Param("updateTime") Date updateTime);
}
