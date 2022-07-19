package com.sis.service;

import com.sis.dto.section.SectionDTO;
import com.sis.dto.section.SectionRequestDTO;
import com.sis.dto.section.SectionTableRecordsDTO;
import com.sis.dto.section.Section_Course;
import com.sis.entity.*;
import com.sis.entity.mapper.SectionMapper;
import com.sis.entity.mapper.SectionTableRecordsMapper;
import com.sis.repository.SectionRepository;
import com.sis.repository.StudentEnrollmentRepository;
import com.sis.repository.specification.SectionSpecification;
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
import java.util.List;

import static java.util.stream.Collectors.toCollection;

@Service
@AllArgsConstructor
public class SectionService extends BaseServiceImp<Section> {

    private final SectionRepository sectionRepository;
    private final SectionMapper sectionMapper;
    private final SectionTableRecordsMapper sectionTableRecordsMapper;
    private final TimetableService timetableService;
    private final StudentEnrollmentService studentEnrollmentService;
    private final StudentEnrollmentRepository studentEnrollmentRepository;


    private final CourseService courseService;

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

        PageResult<SectionDTO> sectionDTOsDtoPageResult = sectionMapper.toDataPage(pageResult);
        for (SectionDTO section : sectionDTOsDtoPageResult.getData()) {
            int students = this.countBySection(section.getId());
            section.setNumberOfStudents(students);
        }
        return sectionDTOsDtoPageResult;
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
                if(section.getLectures() == null || section.getLectures().size() ==0){
                    section_course.setLecturesNumber(0);
                    section_course.setPresentAverage(0);
                    section_courses.add(section_course);
                }else {
                    section_course.setLecturesNumber(section.getLectures().size());
//                section_course.setStudentsNumber(section.getStudentEnrollments().size());
                    double presentsNumber = 0;
                    double studentNumber = section.getStudentEnrollments().size() * section.getLectures().size();
                    for (Lecture lecture : section.getLectures()) {
                        ArrayList<AttendanceDetails> attendanceDetails = lecture.getAttendanceDetails().stream().filter(attendanceDetails1 ->
                                attendanceDetails1.getAttendanceStatus().equalsIgnoreCase("Present")).collect(toCollection(ArrayList<AttendanceDetails>::new));
                        presentsNumber += attendanceDetails.size();
                    }
                    section_course.setPresentAverage((presentsNumber / studentNumber) * 100);
                    section_courses.add(section_course);
                }
            }
            return section_courses;
        }

        return null;
    }
    //Abdo.Amr
    public ArrayList<Section_Course> findStudentSections_courses(AcademicYear academicYear, AcademicTerm academicTerm, long studentId) {
        Collection<Section> sections = this.studentEnrollmentService.findStudentSections(academicYear,academicTerm,studentId);

        ArrayList<Section_Course> section_courses = new ArrayList<>();

        if (sections != null && sections.size() > 0) {
            for (Section section : sections) {
                Section_Course section_course = new Section_Course();

                section_course.setId(section.getId());
                section_course.setSectionNumber(section.getSectionNumber());
                section_course.setCourseName(section.getCourse().getNameEn());
                if(section.getLectures() == null || section.getLectures().size() ==0){
                    section_course.setLecturesNumber(0);
                    section_course.setPresentAverage(0);
                    section_courses.add(section_course);
                }else {
                    section_course.setLecturesNumber(section.getLectures().size());
//                section_course.setStudentsNumber(section.getStudentEnrollments().size());
                    double presentsNumber = 0;
                    double lecturesNumber =  section.getLectures().size();
                    for (Lecture lecture : section.getLectures()) {
                        ArrayList<AttendanceDetails> attendanceDetails = lecture.getAttendanceDetails().stream().filter(attendanceDetails1 ->
                                attendanceDetails1.getAttendanceStatus().equalsIgnoreCase("Present") && attendanceDetails1.getStudent().getId() == studentId).collect(toCollection(ArrayList<AttendanceDetails>::new));
                        presentsNumber += attendanceDetails.size();
                    }
                    section_course.setPresentAverage((presentsNumber / lecturesNumber) * 100);
                    section_courses.add(section_course);
                }
            }
            return section_courses;
        }

        return null;
    }


    public PageResult<SectionTableRecordsDTO> filter(PageQueryUtil pageUtil, SectionRequestDTO sectionRequestDTO) {
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

        PageResult<SectionTableRecordsDTO> sectionDTOsDtoPageResult = sectionTableRecordsMapper.toDataPage(pageResult);
        for (SectionTableRecordsDTO section : sectionDTOsDtoPageResult.getData()) {
            int students = this.countBySection(section.getId());
            section.setNumberOfStudents(students);
        }
        return sectionDTOsDtoPageResult;
    }

    public List<SectionDTO> getSectionsByCourseId(Long courseId) {
        Course course = this.courseService.findById(courseId);
        return this.sectionMapper.toDTOs(this.sectionRepository.getSectionsByCourseId(course.getId()));
    }

}
