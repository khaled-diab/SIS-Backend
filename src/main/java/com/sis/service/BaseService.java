package com.sis.service;

import com.sis.entity.BaseEntity;
import com.sis.util.PageQueryUtil;
import com.sis.util.PageResult;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BaseService<E extends BaseEntity > {

    List<E> findAll();

    E findById(Long id);

    E save(E entity);

    List<E> saveAll(List<E> entity);

    void delete(E entity);

    void deleteById(Long id);

    PageResult<E> getDataPage(PageQueryUtil pageUtil);

    PageResult<E> getDataPage(PageQueryUtil pageUtil, String sortField, Direction sortDirection);

    JpaRepository<E, Long> Repository();


}
