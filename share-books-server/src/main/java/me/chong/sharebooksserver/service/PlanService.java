package me.chong.sharebooksserver.service;

import me.chong.sharebooksserver.domain.PlanRepository;
import me.chong.sharebooksserver.entity.Plan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PlanService {

    @Autowired
    private PlanRepository repository;

    public Plan save(Plan plan) {
        return repository.save(plan);
    }

    public void update(Plan plan) {
        repository.update(plan.getId(), plan.getReadCount(), plan.getComplete(), new Date());
    }

    public List<Plan> findByUserId(Long userId) {
        return repository.findByUserId(userId);
    }
}
