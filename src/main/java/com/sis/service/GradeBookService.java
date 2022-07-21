package com.sis.service;

import com.sis.dto.gradeBook.GradeBookDTO;
import com.sis.dto.gradeBook.GradeBookRequestDTO;
import com.sis.dto.section.SectionDTO;
import com.sis.entity.*;
import com.sis.entity.mapper.*;
import com.sis.repository.GradeBookRepository;
import com.sis.repository.TimetableRepository;
import com.sis.repository.specification.GradeBookSpecification;
import com.sis.util.PageQueryUtil;
import com.sis.util.PageResult;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class GradeBookService extends BaseServiceImp<GradeBook> {

    private final GradeBookRepository gradeBookRepository;
    private final GradeBookMapper gradeBookMapper;

    private final TimetableRepository timetableRepository;

    private final SectionService sectionService;

    private final SectionMapper sectionMapper;

    @Override
    public JpaRepository<GradeBook, Long> Repository() {
        return gradeBookRepository;
    }

    public PageResult<GradeBookDTO> filter(PageQueryUtil pageUtil, GradeBookRequestDTO gradeBookRequestDTO) {
        Page<GradeBook> gradeBookPage;

        Long filterAcademicTerm = gradeBookRequestDTO.getFilterAcademicTerm();

        Long filterSection = gradeBookRequestDTO.getFilterSection();

        Long filterStudent = gradeBookRequestDTO.getFilterStudent();

        Pageable pageable = PageRequest.of(pageUtil.getPage() - 1, pageUtil.getLimit(), constructSortObject(gradeBookRequestDTO));
        if (filterAcademicTerm != null || filterSection != null || filterStudent != null) {
            GradeBookSpecification gradeBookSpecification =
                    new GradeBookSpecification(filterAcademicTerm, filterSection, filterStudent);

            gradeBookPage = gradeBookRepository.findAll(gradeBookSpecification, pageable);
        } else {
            gradeBookPage = gradeBookRepository.findAll(pageable);
        }
        PageResult<GradeBook> pageResult = new PageResult<>(gradeBookPage.getContent(), (int) gradeBookPage.getTotalElements(),
                pageUtil.getLimit(), pageUtil.getPage());

        return gradeBookMapper.toDataPage(pageResult);
    }

    private Sort constructSortObject(GradeBookRequestDTO gradeBookRequestDTO) {
        if (gradeBookRequestDTO.getSortDirection() == null) {
            return Sort.by(Sort.Direction.ASC, "student");
        }
        return Sort.by(Sort.Direction.valueOf(gradeBookRequestDTO.getSortDirection()), gradeBookRequestDTO.getSortBy());
    }

    public ArrayList<SectionDTO> getFacultyMemberSections(Long termId, Long facultyMemberId) {
        ArrayList<Long> sectionIds = this.timetableRepository.getFacultyMemberSections(termId, facultyMemberId);
        ArrayList<Section> sections = new ArrayList<>();
        ArrayList<SectionDTO> sectionDTOs = new ArrayList<>();

        if (sectionIds != null && sectionIds.size() > 0) {
            for (long id : sectionIds) {
                Section section = this.sectionService.findById(id);
                sections.add(section);
            }
            sectionDTOs = this.sectionMapper.toDTOs(sections);
            return sectionDTOs;
        }
        return null;
    }

}
