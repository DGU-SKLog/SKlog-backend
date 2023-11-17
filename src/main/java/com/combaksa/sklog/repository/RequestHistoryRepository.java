package com.combaksa.sklog.repository;

import com.combaksa.sklog.domain.RequestHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RequestHistoryRepository extends JpaRepository<RequestHistory, Long> {
    Optional<RequestHistory> findByUserContent(String userContent);
}
