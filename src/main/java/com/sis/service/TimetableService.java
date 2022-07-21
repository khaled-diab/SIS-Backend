package com.sis.service;

import com.sis.dto.timetable.TimetableDTO;
import com.sis.dto.timetable.TimetableRequestDTO;
import com.sis.dto.timetable.TimetableTableRecordsDTO;
import com.sis.entity.AcademicTerm;
import com.sis.entity.Section;
import com.sis.entity.Timetable;
import com.sis.entity.mapper.TimetableMapper;
import com.sis.entity.mapper.TimetableTableRecordsMapper;
import com.sis.repository.TimetableRepository;
import com.sis.repository.specification.TimetableSpecification;
import com.sis.util.Constants;
import com.sis.util.PageQueryUtil;
import com.sis.util.PageResult;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@AllArgsConstructor
public class TimetableService extends BaseServiceImp<Timetable> {

    private final TimetableRepository timetableRepository;
    private final TimetableMapper timetableMapper;
    private final TimetableTableRecordsMapper timetableTableRecordsMapper;
    private final StudentEnrollmentService studentEnrollmentService;

    private AcademicTermService academicTermService;

    @Override
    public JpaRepository<Timetable, Long> Repository() {
        return timetableRepository;
    }

    public PageResult<TimetableDTO> search(PageQueryUtil pageUtil, TimetableRequestDTO timetableRequestDTO) {
        Page<Timetable> timetablePage;

        Long filterCollege = timetableRequestDTO.getFilterCollege();

        Long filterDepartment = timetableRequestDTO.getFilterDepartment();

        Long filterAcademicYear = timetableRequestDTO.getFilterAcademicYear();

        Long filterAcademicTerm = timetableRequestDTO.getFilterAcademicTerm();

        Long filterFacultyMember = timetableRequestDTO.getFilterFacultyMember();

        Long filterCourse = timetableRequestDTO.getFilterCourse();

        Long filterSection = timetableRequestDTO.getFilterSection();

        String filterDay = timetableRequestDTO.getFilterDay();

        Pageable pageable = PageRequest.of(pageUtil.getPage() - 1, pageUtil.getLimit(), constructSortObject(timetableRequestDTO));
        if (filterCollege != null || filterDepartment != null ||
                filterAcademicYear != null || filterAcademicTerm != null || filterFacultyMember != null ||
                filterCourse != null || filterSection != null || (filterDay != null && !filterDay.trim().isEmpty())) {
            TimetableSpecification timetableSpecification = new TimetableSpecification(filterCollege, filterDepartment,
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
            return Sort.by(Sort.Direction.ASC, "startTime");
        }
        return Sort.by(Sort.Direction.valueOf(timetableRequestDTO.getSortDirection()), timetableRequestDTO.getSortBy());
    }

    public void saveAllTimetable(List<Timetable> timetableDTOList) {
        this.timetableRepository.saveAll(timetableDTOList);
    }

    //Abdo.Amr
    public Collection<TimetableDTO> findFacultyMemberTimeTables(long academicYearId, long academicTermId, long facultyMemberId, long courseId) {
        Collection<Timetable> timetables = this.timetableRepository.findFacultyMemberTimeTables(academicYearId, academicTermId, facultyMemberId, courseId);
        Collection<TimetableDTO> timetableDTOs = null;
        if (timetables != null) {
            timetableDTOs = this.timetableMapper.toDTOs(timetables);
        }
        for (Timetable timetable : timetables) {
            System.out.println(timetable.getStartTime() + " start");
            System.out.println(timetable.getEndTime() + " end");
        }
        return timetableDTOs;
    }

    //Abdo.Amr
    public ArrayList<Long> findFacultyMemberSections(long academicYearId, long academicTermId, long facultyMemberId) {
        return this.timetableRepository.findFacultyMemberSections(academicYearId, academicTermId, facultyMemberId);

    }

    //Abdo.Amr
    public ArrayList<Long> findFacultyMemberCourses(long academicYearId, long academicTermId, long facultyMemberId) {
        return this.timetableRepository.findFacultyMemberCourses(academicYearId, academicTermId, facultyMemberId);

    }

    public Collection<TimetableDTO> getSectionTimeTables(long academicYearId, long academicTermId, long sectionId) {
        ArrayList<Timetable> timetables = this.timetableRepository.findTimetableBySection(academicYearId, academicTermId, sectionId);
        ArrayList<TimetableDTO> timetableDTOS = new ArrayList<>();

        if (timetables != null) {
            timetableDTOS = this.timetableMapper.toDTOs(timetables);
        }
        return timetableDTOS;
    }

    public Collection<TimetableTableRecordsDTO> getSectionTimeTables1(long academicYearId, long academicTermId, long sectionId) {
        ArrayList<Timetable> timetables = this.timetableRepository.findTimetableBySection(academicYearId, academicTermId, sectionId);
        ArrayList<TimetableTableRecordsDTO> timetableTableRecordsDTOS = new ArrayList<>();

        if (timetables != null) {
            timetableTableRecordsDTOS = this.timetableTableRecordsMapper.toDTOs(timetables);
        }
        return timetableTableRecordsDTOS;
    }

    public ArrayList<TimetableTableRecordsDTO> getStudentTimetables(long studentId) {
        AcademicTerm academicTerm = this.academicTermService.getCurrentAcademicTerm();
        Collection<Section> sections = this.studentEnrollmentService.
                findStudentSections(academicTerm.getAcademicYear(), academicTerm, studentId);
        ArrayList<TimetableTableRecordsDTO> timetableDTOs = new ArrayList<>();
        for (Section section : sections) {
            timetableDTOs.addAll(this.getSectionTimeTables1(
                    section.getAcademicYear().getId(), section.getAcademicTerm().getId(), section.getId()));
        }
        timetableDTOs.sort((timetableDTO, t1) -> {
            if (Integer.parseInt(Constants.from12To24System(t1.getStartTime()).substring(0, 2)) > (Integer.parseInt(Constants.from12To24System(timetableDTO.getStartTime()).substring(0, 2))))
                return -1;
            else if (Integer.parseInt(Constants.from12To24System(t1.getStartTime()).substring(0, 2)) < (Integer.parseInt(Constants.from12To24System(timetableDTO.getStartTime()).substring(0, 2))))
                return 1;
            return 0;
        });
        return timetableDTOs;
    }

    public PageResult<TimetableTableRecordsDTO> filter(PageQueryUtil pageUtil, TimetableRequestDTO timetableRequestDTO) {
        Page<Timetable> timetablePage;

        Long filterCollege = timetableRequestDTO.getFilterCollege();

        Long filterDepartment = timetableRequestDTO.getFilterDepartment();

        Long filterAcademicYear = timetableRequestDTO.getFilterAcademicYear();

        Long filterAcademicTerm = timetableRequestDTO.getFilterAcademicTerm();

        Long filterFacultyMember = timetableRequestDTO.getFilterFacultyMember();

        Long filterCourse = timetableRequestDTO.getFilterCourse();

        Long filterSection = timetableRequestDTO.getFilterSection();

        String filterDay = timetableRequestDTO.getFilterDay();

        Pageable pageable = PageRequest.of(pageUtil.getPage() - 1, pageUtil.getLimit(), constructSortObject(timetableRequestDTO));
        if (filterCollege != null || filterDepartment != null ||
                filterAcademicYear != null || filterAcademicTerm != null || filterFacultyMember != null ||
                filterCourse != null || filterSection != null || (filterDay != null && !filterDay.trim().isEmpty())) {
            TimetableSpecification timetableSpecification = new TimetableSpecification(filterCollege, filterDepartment,
                    filterAcademicYear, filterAcademicTerm, filterFacultyMember, filterCourse, filterSection, filterDay);

            timetablePage = timetableRepository.findAll(timetableSpecification, pageable);
        } else {
            timetablePage = timetableRepository.findAll(pageable);
        }
        PageResult<Timetable> pageResult = new PageResult<>(timetablePage.getContent(), (int) timetablePage.getTotalElements(),
                pageUtil.getLimit(), pageUtil.getPage());

        return timetableTableRecordsMapper.toDataPage(pageResult);
    }

}
