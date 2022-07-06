package com.sis.entity.mapper;


import com.sis.dto.BaseDTO;
import com.sis.entity.BaseEntity;
import com.sis.util.PageResult;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public interface Mapper<E extends BaseEntity, D extends BaseDTO> {
    D toDTO(final E e);

    E toEntity(final D d);

    ArrayList<D> toDTOs(final Collection<E> e);

    ArrayList<E> toEntities(final Collection<D> ds);

    PageResult<D> toDataPage(final PageResult<E> entity);
}
