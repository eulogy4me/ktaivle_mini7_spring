package com.aivle.mini7.service;

import org.springframework.data.jpa.repository.JpaRepository;
import com.aivle.mini7.entity.RequestEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends JpaRepository<RequestEntity, Long> {}
