package com.sis.service;

import com.sis.dao.LectureRepository;
import com.sis.dao.LectureTypeRepository;
import com.sis.entities.LectureType;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LectureTypeService extends BaseServiceImp<LectureType> {

    private LectureTypeRepository lectureTypeRepository;

    @Override
    public JpaRepository<LectureType, Long> Repository() {
        return lectureTypeRepository;
    }
}
