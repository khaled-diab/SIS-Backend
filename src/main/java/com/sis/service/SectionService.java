package com.sis.service;

import com.sis.dao.SectionRepository;
import com.sis.dao.specification.SectionSpecification;
import com.sis.dto.section.SectionDTO;
import com.sis.dto.section.SectionRequestDTO;
import com.sis.entities.Section;
import com.sis.entities.mapper.SectionMapper;
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
import java.util.Collection;

@Service
@AllArgsConstructor
public class SectionService extends BaseServiceImp<Section> {

    private final SectionRepository sectionRepository;
    private final SectionMapper sectionMapper;
    private StudentEnrollmentService studentEnrollmentService;

    @Override
    public JpaRepository<Section, Long> Repository() {
        return sectionRepository;
    }

    public PageResult<SectionDTO> search(PageQueryUtil pageUtil, SectionRequestDTO sectionRequestDTO) {
        Page<Section> sectionPage;
        String searchValue = sectionRequestDTO.getSearchValue();

        Long filterCollege = sectionRequestDTO.getFilterCollege();

        Long filterDepartment = sectionRequestDTO.getFilterDepartment();

        Long filterAcademicYear = sectionRequestDTO.getFilterAcademicYear();

        Long filterAcademicTerm = sectionRequestDTO.getFilterAcademicTerm();

        Long filterCourse = sectionRequestDTO.getFilterCourse();

        Long filterStudyType = sectionRequestDTO.getFilterStudyType();

        String filterSectionNumber = sectionRequestDTO.getFilterSectionNumber();

        Pageable pageable = PageRequest.of(pageUtil.getPage() - 1, pageUtil.getLimit(), constructSortObject(sectionRequestDTO));
        if (( searchValue != null && !searchValue.trim().isEmpty() ) || filterCollege != null || filterDepartment != null ||
                filterAcademicYear != null || filterAcademicTerm != null || filterCourse != null ||
                filterStudyType != null || filterSectionNumber != null) {
            SectionSpecification sectionSpecification = new SectionSpecification(searchValue, filterCollege, filterDepartment,
                    filterAcademicYear, filterAcademicTerm, filterCourse, filterStudyType, filterSectionNumber);

            sectionPage = sectionRepository.findAll(sectionSpecification, pageable);
        } else {
            sectionPage = sectionRepository.findAll(pageable);
        }
        PageResult<Section> pageResult = new PageResult<>(sectionPage.getContent(), (int) sectionPage.getTotalElements(),
                pageUtil.getLimit(), pageUtil.getPage());

        return sectionMapper.toDataPage(pageResult);
    }

    private Sort constructSortObject(SectionRequestDTO sectionRequestDTO) {
        if (sectionRequestDTO.getSortDirection() == null) {
            return Sort.by(Sort.Direction.ASC, "sectionNumber");
        }
        return Sort.by(Sort.Direction.valueOf(sectionRequestDTO.getSortDirection()), sectionRequestDTO.getSortBy());
    }

    //UC011
    public Collection<Section> findStudentSections(long academicYearId, long academicTermId, long studentId){
        Collection<Section> sections = this.studentEnrollmentService.findStudentSections(academicYearId, academicTermId,studentId);
        return sections;
    }
    //UC011
    public Section findStudentSection(long academicYearId, long academicTermId,long studentId,long courseId){
        Section section = this.studentEnrollmentService.findStudentSection(academicYearId, academicTermId,studentId, courseId);
        return section;
    }


}
