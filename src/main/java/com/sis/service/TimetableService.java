package com.sis.service;

import com.sis.entities.Timetable;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TimetableService extends BaseServiceImp<Timetable> {

    @Override
    public JpaRepository<Timetable, Long> Repository() {
        return null;
    }
}
