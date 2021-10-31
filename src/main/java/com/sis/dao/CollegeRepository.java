package com.sis.dao;

import com.sis.entities.College;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;

public interface CollegeRepository extends BaseDao<College> {
    Page<College> findAllByCodeContainingOrNameArContainingOrNameEnContaining(@Nullable String code,
                                                                              @Nullable String nameAR,
                                                                              @Nullable String nameEN,
                                                                              Pageable pageable);
}
