package com.machineric.machinericpractice.repository;

import com.machineric.machinericpractice.entity.WorldTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorldTimeRepository extends JpaRepository<WorldTime, Long> {
//    Optional<WorldTime> find();
}
