package com.sis.service;

import com.sis.entities.LectureType;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LectureTypeService extends BaseServiceImp<LectureType> {

    @Override
    public JpaRepository<LectureType, Long> Repository() {
        return null;
    }
}
