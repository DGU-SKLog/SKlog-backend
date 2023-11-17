package com.combaksa.sklog.repository;

import com.combaksa.sklog.domain.RequestHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RequestHistoryRepository extends JpaRepository<RequestHistory, Long> {
    List<RequestHistory> findAllByRequest(String request);
}
