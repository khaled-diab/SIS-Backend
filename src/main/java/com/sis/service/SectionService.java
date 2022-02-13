package com.sis.service;

import com.sis.entities.Section;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SectionService extends BaseServiceImp<Section> {

    @Override
    public JpaRepository<Section, Long> Repository() {
        return null;
    }
}
