package com.sis.service;

import com.sis.entity.Major;
import com.sis.repository.MajorRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MajorService extends BaseServiceImp<Major> {

    private MajorRepository majorRepository;

    @Override
    public JpaRepository<Major, Long> Repository() {
        return majorRepository;
    }
}
