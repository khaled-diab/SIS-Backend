package com.sis.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.repository.JpaRepository;
import com.sis.entities.BaseEntity;
import com.sis.util.PageQueryUtil;
import com.sis.util.PageResult;
import org.springframework.data.util.Pair;

public interface BaseService<E extends BaseEntity> {

    public List<E> findAll();

    public E findById(Long id);

    public List<E> filterBy(Map<String, String> whereClause);

    public E save(E entity);

    public void delete(E entity);

    public void deleteById(Long id);

    public PageResult<E> getDataPage(PageQueryUtil pageUtil);

    public PageResult<E> getDataPage(PageQueryUtil pageUtil, String sortField, Direction sortDirection);

    public JpaRepository<E, Long> Repository();
}
