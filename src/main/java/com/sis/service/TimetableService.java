package com.sis.service;

import com.sis.dao.TimetableRepository;
import com.sis.dao.specification.TimetableSpecification;
import com.sis.dto.timetable.TimetableDTO;
import com.sis.dto.timetable.TimetableRequestDTO;
import com.sis.entities.Timetable;
import com.sis.entities.mapper.TimetableMapper;
import com.sis.util.PageQueryUtil;
import com.sis.util.PageResult;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class TimetableService extends BaseServiceImp<Timetable> {

    private final TimetableRepository timetableRepository;
    private final TimetableMapper timetableMapper;

    @Override
    public JpaRepository<Timetable, Long> Repository() {
        return timetableRepository;
    }

    public PageResult<TimetableDTO> filter(PageQueryUtil pageUtil, TimetableRequestDTO timetableRequestDTO) {
        Page<Timetable> timetablePage;
        String searchValue = timetableRequestDTO.getSearchValue();

        Long filterCollege = timetableRequestDTO.getFilterCollege();

        Long filterDepartment = timetableRequestDTO.getFilterDepartment();

        Long filterAcademicYear = timetableRequestDTO.getFilterAcademicYear();

        Long filterAcademicTerm = timetableRequestDTO.getFilterAcademicTerm();

        Long filterFacultyMember = timetableRequestDTO.getFilterFacultyMember();

        Long filterCourse = timetableRequestDTO.getFilterCourse();

        Long filterSection = timetableRequestDTO.getFilterSection();

        String filterDay = timetableRequestDTO.getFilterDay();

        Pageable pageable = PageRequest.of(pageUtil.getPage() - 1, pageUtil.getLimit(), constructSortObject(timetableRequestDTO));
        if ((searchValue != null && !searchValue.trim().isEmpty()) || filterCollege != null || filterDepartment != null ||
                filterAcademicYear != null || filterAcademicTerm != null) {
            TimetableSpecification timetableSpecification = new TimetableSpecification(searchValue, filterCollege, filterDepartment,
                    filterAcademicYear, filterAcademicTerm, filterFacultyMember, filterCourse, filterSection, filterDay);

            timetablePage = timetableRepository.findAll(timetableSpecification, pageable);
        } else {
            timetablePage = timetableRepository.findAll(pageable);
        }
        PageResult<Timetable> pageResult = new PageResult<>(timetablePage.getContent(), (int) timetablePage.getTotalElements(),
                pageUtil.getLimit(), pageUtil.getPage());

        return timetableMapper.toDataPage(pageResult);
    }

    private Sort constructSortObject(TimetableRequestDTO timetableRequestDTO) {
        if (timetableRequestDTO.getSortDirection() == null) {
            return Sort.by(Sort.Direction.ASC, "day");
        }
        return Sort.by(Sort.Direction.valueOf(timetableRequestDTO.getSortDirection()), timetableRequestDTO.getSortBy());
    }

    public Collection<Timetable> findTimeTables(long academicYearId, long academicTermId, long facultyMemberId, long courseId){
        return this.timetableRepository.findTimeTables( academicYearId,  academicTermId,  facultyMemberId,  courseId);
    }

}
