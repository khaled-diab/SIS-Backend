package com.sis.repository;

import com.sis.entities.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BaseDao <T extends BaseEntity> extends JpaRepository<T, Long>, JpaSpecificationExecutor<T> {}
