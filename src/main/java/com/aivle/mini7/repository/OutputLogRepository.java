package com.aivle.mini7.repository;

import com.aivle.mini7.model.OutputLog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutputLogRepository extends JpaRepository<OutputLog, String> {


}
