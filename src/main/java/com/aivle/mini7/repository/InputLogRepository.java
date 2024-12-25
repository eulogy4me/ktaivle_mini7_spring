package com.aivle.mini7.repository;

import com.aivle.mini7.model.InputLog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InputLogRepository extends JpaRepository<InputLog, String> {


}