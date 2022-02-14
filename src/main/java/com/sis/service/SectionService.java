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

@Service
@AllArgsConstructor
public class SectionService extends BaseServiceImp<Section> {

    @Override
    public JpaRepository<Section, Long> Repository() {
        return null;
    }

    private final SectionRepository sectionRepository;
    private final SectionMapper sectionMapper;

    public PageResult<SectionDTO> search(PageQueryUtil pageUtil, SectionRequestDTO sectionRequestDTO) {
        Page<Section> sectionPage;
        String searchValue = sectionRequestDTO.getSearchValue();

        Long filterCollege = sectionRequestDTO.getFilterCollege();

        Long filterDepartment = sectionRequestDTO.getFilterDepartment();

        Long filterAcademicYear = sectionRequestDTO.getFilterAcademicYear();

        Long filterAcademicTerm = sectionRequestDTO.getFilterAcademicTerm();

        Long filterCourse = sectionRequestDTO.getFilterCourse();

        Long filterStudyType = sectionRequestDTO.getFilterStudyType();

        Long filterSectionNumber = sectionRequestDTO.getFilterSectionNumber();

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
            return Sort.by(Sort.Direction.ASC, "nameAr");
        }
        return Sort.by(Sort.Direction.valueOf(sectionRequestDTO.getSortDirection()), sectionRequestDTO.getSortBy());
    }
}
