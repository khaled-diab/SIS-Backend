package com.sis.service;

import com.sis.entity.Degree;
import com.sis.repository.DegreeRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DegreeService extends BaseServiceImp<Degree> {

    private final DegreeRepository degreeRepository;

    @Override
    public JpaRepository<Degree, Long> Repository() {
        return degreeRepository;
    }

}
