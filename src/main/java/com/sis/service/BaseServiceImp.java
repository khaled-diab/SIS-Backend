package com.sis.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.graalvm.compiler.graph.Node;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;
import com.sis.entities.BaseEntity;
import com.sis.exception.ItemNotFoundException;
import com.sis.util.PageQueryUtil;
import com.sis.util.PageResult;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public abstract class BaseServiceImp<E extends BaseEntity> implements BaseService<E> {
	@Override
	public List<E> findAll() {
		List<E> entities = Repository().findAll();
		return entities;
	}

	@Override
	public E findById(Long id) {		
		return Repository().findById(id).orElseThrow(() -> new ItemNotFoundException(id));	
	}

	@Override
	@Transactional(readOnly = false)
	public E save(E entity) {
		return Repository().save(entity);
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(E entity) {
		Repository().delete(entity);
	}

	@Override
	public void deleteById(Long id) {
		Repository().deleteById(id);
	}

	@Override
	public PageResult<E> getDataPage(PageQueryUtil pageUtil) {
		PageResult<E> pageResult = getDataPage(pageUtil);
		return pageResult;
	}

	@Override
	public PageResult<E> getDataPage(PageQueryUtil pageUtil, @Nullable String sortField,@Nullable Direction sortDirection) {
		Sort sort = sortDirection.equals(Sort.Direction.ASC) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();
		Pageable pageable = PageRequest.of(pageUtil.getPage() - 1, pageUtil.getLimit(), sort);
		Page<E> page = this.Repository().findAll(pageable);
		PageResult<E> pageResult = new PageResult<E>(page.getContent(), (int) page.getTotalElements(),
				pageUtil.getLimit(), pageUtil.getPage());
		return pageResult;
	}

	@Override
	public List<E> find(String key){
		key = key.toLowerCase();
		String finalKey = key;
		List<E> list = Repository().findAll().stream().filter(e -> {
			Field[] declaredFields = e.getClass().getDeclaredFields();
			boolean found = e.getId().toString().toLowerCase().contains(finalKey);
			for (Field declaredField : declaredFields) {
				try {
					declaredField.setAccessible(true);
					Object value = declaredField.get(e);
					if (value == null) continue;
					found |= value.toString().toLowerCase().contains(finalKey.toLowerCase());
				} catch (IllegalAccessException | NullPointerException exception) {
					exception.printStackTrace();
				}
			}
			return found;
		}).collect(Collectors.toList());
		return list;
	}
}
