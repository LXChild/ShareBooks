package me.chong.sharebooksserver.domain;

import me.chong.sharebooksserver.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface PlanRepository extends JpaRepository<Plan, Long> {


    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Plan set readCount =:readCount, complete =:complete, updateTime=:updateTime where id =:id")
    void update(@Param("id") Long id, @Param("readCount") Integer readCount, @Param("complete") Boolean complete,
                @Param("updateTime") Date updateTime);

    List<Plan> findByUserId(Long userId);
}
