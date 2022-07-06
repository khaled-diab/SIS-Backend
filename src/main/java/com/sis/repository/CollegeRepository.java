package com.sis.repository;

import com.sis.dto.college.CollegeProjection;
import com.sis.entity.College;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;

import java.util.List;

public interface CollegeRepository extends BaseRepository<College> {
    Page<College> findAllByCodeContainingOrNameArContainingOrNameEnContaining(@Nullable String code, @Nullable String nameAR, @Nullable String nameEN, Pageable pageable);

    Boolean existsByCode(String code);

    @Query(value = " select  code,id from college", nativeQuery = true)
    List<CollegeProjection> findAllIdsAndCodes();

    College findByCode(String code);

}
