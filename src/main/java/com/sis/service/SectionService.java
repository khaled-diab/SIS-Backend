package com.sis.service;

import com.sis.dao.SectionRepository;
import com.sis.dao.specification.SectionSpecification;
import com.sis.dto.section.SectionDTO;
import com.sis.dto.section.SectionRequestDTO;
import com.sis.dto.section.Section_Course;
import com.sis.entities.*;
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
import java.util.Iterator;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toCollection;

@Service
@AllArgsConstructor
public class SectionService extends BaseServiceImp<Section> {

    private final SectionRepository sectionRepository;
    private final SectionMapper sectionMapper;
    private final TimetableService timetableService;
    private final StudentEnrollmentService studentEnrollmentService;

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

        Long filterMajor = sectionRequestDTO.getFilterMajor();

        Pageable pageable = PageRequest.of(pageUtil.getPage() - 1, pageUtil.getLimit(), constructSortObject(sectionRequestDTO));
        if ((searchValue != null && !searchValue.trim().isEmpty()) || filterCollege != null ||
                filterDepartment != null || filterAcademicYear != null || filterAcademicTerm != null ||
                filterCourse != null || filterStudyType != null || filterMajor != null) {
            SectionSpecification sectionSpecification = new SectionSpecification(searchValue, filterCollege, filterDepartment,
                    filterAcademicYear, filterAcademicTerm, filterCourse, filterStudyType, filterMajor);

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
            return Sort.by(Sort.Direction.ASC, "college");
        }
        return Sort.by(Sort.Direction.valueOf(sectionRequestDTO.getSortDirection()), sectionRequestDTO.getSortBy());
    }

    public Section findSection(String sectionNumber, long collegeId, long departmentId) {
        return this.sectionRepository.findSectionBySectionNumberAndCollegeIdAndDepartmentId(
                sectionNumber, collegeId, departmentId);
    }

    public int countBySection(long sectionId) {
        return this.studentEnrollmentService.countBySection(sectionId);
    }

//    public ArrayList<Section> getStudentSections(long studentId) {
//        ArrayList<Section> sections = this.studentEnrollmentService.getStudentSections(studentId);
//        return sections;
//    }

    //Abdo.Amr
    public Collection<Section> findStudentSections(AcademicYear academicYear, AcademicTerm academicTerm, long studentId) {
        Collection<Section> sections = this.studentEnrollmentService.findStudentSections(academicYear, academicTerm, studentId);
        return sections;
    }

    //Abdo.Amr
    public Section findStudentSection(long academicYearId, long academicTermId, long studentId, long courseId) {
        Section section = this.studentEnrollmentService.findStudentSection(academicYearId, academicTermId, studentId, courseId);
        return section;
    }

    //Abdo.Amr
    public ArrayList<SectionDTO> findFacultyMemberSections(long academicYearId, long academicTermId, long facultyMemberId) {
        ArrayList<Long> sectionIds = this.timetableService.findFacultyMemberSections(academicYearId, academicTermId, facultyMemberId);
        ArrayList<Section> sections = new ArrayList<>();
        ArrayList<SectionDTO> sectionDTOs = new ArrayList<>();

        if (sectionIds != null && sectionIds.size() > 0) {
            for (long id : sectionIds) {
                Section section = this.findById(id);
                sections.add(section);
            }
            sectionDTOs = this.sectionMapper.toDTOs(sections);
            return sectionDTOs;
        }
        return null;
    }

    //Abdo.Amr
    public ArrayList<Section_Course> findFacultyMemberSections_courses(long academicYearId, long academicTermId, long facultyMemberId) {
        ArrayList<Long> sectionIds = this.timetableService.findFacultyMemberSections(academicYearId, academicTermId, facultyMemberId);
        ArrayList<Section_Course> section_courses = new ArrayList<>();

        if (sectionIds != null && sectionIds.size() > 0) {
            for (long id : sectionIds) {
                Section_Course section_course = new Section_Course();
                Section section = this.findById(id);

                section_course.setId(section.getId());
                section_course.setSectionNumber(section.getSectionNumber());
                section_course.setCourseName(section.getCourse().getNameEn());
                section_course.setLecturesNumber(section.getLectures().size());
                section_course.setStudentsNumber(section.getStudentEnrollments().size());
                for (Lecture lecture : section.getLectures()) {
                    ArrayList<AttendanceDetails> attendanceDetails = lecture.getAttendanceDetails().stream().filter(attendanceDetails1 ->
                            attendanceDetails1.getAttendanceStatus().equalsIgnoreCase("Present")).collect(toCollection(ArrayList<AttendanceDetails>::new));
                    section_course.setPresentsNumber(attendanceDetails.size());
                }
                section_courses.add(section_course);



            }
            return section_courses;
        }

            return null;
        }


}
