package com.sis.repository;

import com.sis.entity.College;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;

public interface CollegeRepository extends BaseRepository<College> {
    Page<College> findAllByCodeContainingOrNameArContainingOrNameEnContaining(@Nullable String code, @Nullable String nameAR,
                                                                              @Nullable String nameEN, Pageable pageable);

    Boolean existsByCode(String code);

}
