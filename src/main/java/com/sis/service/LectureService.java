package com.sis.service;

import com.sis.dao.LectureRepository;
import com.sis.entities.Lecture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class LectureService  extends BaseServiceImp<Lecture>{


    @Autowired
    private LectureRepository lectureRepository;


    @Override
    public JpaRepository<Lecture, Long> Repository() {
        return this.lectureRepository;
    }
}
