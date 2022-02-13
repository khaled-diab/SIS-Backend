package com.sis.service;

import com.sis.entities.StudyType;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StudyTypeService extends BaseServiceImp<StudyType> {

    @Override
    public JpaRepository<StudyType, Long> Repository() {
        return null;
    }
}
