package com.sis.service;

import com.sis.entities.Major;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MajorService extends BaseServiceImp<Major> {

    @Override
    public JpaRepository<Major, Long> Repository() {
        return null;
    }
}
