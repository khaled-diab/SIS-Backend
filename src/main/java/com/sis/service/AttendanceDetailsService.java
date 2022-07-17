package com.sis.service;

import com.sis.dto.attendanceDetails.StudentLecture;
import com.sis.entity.mapper.LectureMapper;
import com.sis.exception.StudentFieldNotUniqueException;
import com.sis.repository.AttendanceDetailsRepository;
import com.sis.dto.attendanceDetails.AttendanceBySection;
import com.sis.dto.attendanceDetails.AttendanceBySectionAndStudentDTO;
import com.sis.dto.attendanceDetails.AttendanceDetailsDTO;
import com.sis.dto.lecture.LectureDTO;
import com.sis.dto.attendanceReport.AttendanceReportDTO;
import com.sis.dto.student.StudentRecordDTO;
import com.sis.entity.AttendanceDetails;
import com.sis.entity.Lecture;
import com.sis.entity.mapper.AttendanceDetailsMapper;
import com.sis.entity.mapper.StudentRecordMapper;
import com.sis.repository.AttendanceDetailsRepository;
import com.sis.repository.LectureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toCollection;

@Service
public class AttendanceDetailsService extends BaseServiceImp<AttendanceDetails>{


    @Autowired
    private AttendanceDetailsRepository attendanceDetailsRepository;

    @Autowired
    private AttendanceDetailsMapper attendanceDetailsMapper;

    @Autowired
    private  StudentService studentService;

    @Autowired
    private StudentRecordMapper studentRecordMapper;

    @Autowired
    private LectureMapper lectureMapper;
    @Autowired
    private LectureRepository lectureRepository;


    @Override
    public JpaRepository<AttendanceDetails, Long> Repository() {
        return this.attendanceDetailsRepository;
    }

    public ArrayList<AttendanceDetailsDTO> findStudentAttendances(long studentId, long courseId){
        ArrayList<AttendanceDetails> attendanceDetails= this.attendanceDetailsRepository.findStudentAttendances(studentId, courseId);
        if(attendanceDetails!=null){
            return this.attendanceDetailsMapper.toDTOs(attendanceDetails);
        }
        return null;
    }
    // this function is written by Abdo Ramadan
    public AttendanceReportDTO findAttendanceReportDTOByLecture(Long lectureId){
        ArrayList<AttendanceDetails> attendanceDetails = this.attendanceDetailsRepository.
                findAttendanceDetailsByLectureId(lectureId);
        AttendanceReportDTO attendanceReportDTO = new AttendanceReportDTO();
        if(attendanceDetails!= null){
            attendanceReportDTO.setAttendanceDetailsDTOs(this.attendanceDetailsMapper.toDTOs(attendanceDetails));
        }
        return  attendanceReportDTO;
    }
    public AttendanceDetails getAttendanceDetailsByLectureAndStudent(long lectureId, long studentId){
        AttendanceDetails attendanceDetails = this.attendanceDetailsRepository.findAttendanceDetailsByLectureIdAndStudentId(lectureId,
                studentId);
        return  attendanceDetails;
    }
    public ArrayList<AttendanceDetailsDTO> getAttendanceDetailsByLecture(long lectureId){
        List<AttendanceDetails> attendanceDetails = this.attendanceDetailsRepository.findAttendanceDetailsByLectureId(lectureId);
        ArrayList<AttendanceDetailsDTO> attendanceDetailsDTOS = new ArrayList<>();
        if(attendanceDetails!= null){
            attendanceDetailsDTOS = this.attendanceDetailsMapper.toDTOs(attendanceDetails);
        }
        return  attendanceDetailsDTOS;
    }
    public void saveAttendances(LectureDTO lectureDTO){
        ArrayList<StudentRecordDTO> studentRecordDTOS ;
        ArrayList<AttendanceDetailsDTO> attendanceDetailsDTOs=new ArrayList<>();

        studentRecordDTOS=this.studentRecordMapper.dtosToDTOs(studentService.findStudentsBySection(lectureDTO.getAcademicYearDTO().getId(), lectureDTO.getAcademicTermDTO().getId(), lectureDTO.getSectionDTO().getId()));

        for(StudentRecordDTO studentDTO:studentRecordDTOS){
            AttendanceDetailsDTO attendanceDetailsDTO = AttendanceDetailsDTO.builder()
                    .studentId(studentDTO.getId())
                    .universityId(studentDTO.getUniversityId())
//                    .collegeName(studentDTO.getCollegeName())
//                    .departmentName(studentDTO.getDepartmentName())
                    .lectureId(lectureDTO.getId())
                    .attendanceStatus("Absent")
//                    .attendanceDate(lectureDTO.getLectureDate())
//                    .lectureStartTime(lectureDTO.getLectureStartTime())
//                    .lectureEndTime(lectureDTO.getLectureEndTime())
//                    .sectionNumber(lectureDTO.getSectionDTO().getSectionNumber())
                    .sectionId(lectureDTO.getSectionDTO().getId())
                    .build();
            attendanceDetailsDTOs.add(attendanceDetailsDTO);
        }
        this.attendanceDetailsRepository.saveAll(this.attendanceDetailsMapper.toEntities(attendanceDetailsDTOs));
    }
    //Abdo Ramadan
    public ArrayList<AttendanceBySectionAndStudentDTO> getAttendanceDetailsBySectoinAndStudentId(Long sectionId,
                                                                                                 Long studentId){
        ArrayList<AttendanceDetails> attendanceDetailsList =
                this.attendanceDetailsRepository.findAttendanceDetailsBySectionIdAndStudentId(sectionId ,studentId);
        ArrayList<AttendanceBySectionAndStudentDTO> attendanceBySectionAndStudentDTOS = new ArrayList<>();
        int n = attendanceDetailsList.size();
        for(int i = 0 ;i < n ;i++){
            AttendanceDetails attendanceDetails = attendanceDetailsList.get(i);
            AttendanceBySectionAndStudentDTO attendanceBySectionAndStudentDTO = new AttendanceBySectionAndStudentDTO();
            attendanceBySectionAndStudentDTO.setAttendanceDate(attendanceDetails.getAttendanceDate());
            attendanceBySectionAndStudentDTO.setAttendanceStatus(attendanceDetails.getAttendanceStatus());
            attendanceBySectionAndStudentDTO.setLectureStartTime(attendanceDetails.getLectureStartTime());
            attendanceBySectionAndStudentDTO.setLectureEndTime(attendanceDetails.getLectureEndTime());
            attendanceBySectionAndStudentDTO.setId(attendanceDetails.getId());
            attendanceBySectionAndStudentDTOS.add(attendanceBySectionAndStudentDTO);
        }
        return  attendanceBySectionAndStudentDTOS;
    }

    public ArrayList<AttendanceBySection> getAttendanceDetailsBySectoin(Long sectionId){
        ArrayList<AttendanceDetails> attendanceDetailsList =
                this.attendanceDetailsRepository.findStudentBySectionId(sectionId );
        ArrayList<AttendanceBySection> attendanceBySectionAndStudentDTOS = new ArrayList<>();
        int n = attendanceDetailsList.size();
        ArrayList<Long> lectureIds = lectureRepository.findFacultyMemberLectures(sectionId);

        for(int i = 0 ;i < n ;i++){
            int lectureNumber = lectureIds.size();
            AttendanceDetails attendanceDetails = attendanceDetailsList.get(i);
            AttendanceBySection attendanceBySection = new AttendanceBySection();
            attendanceBySection.setNameOfStudent(attendanceDetails.getStudent().getNameAr());
            attendanceBySection.setNumberOfLecture(lectureNumber);
            Long studentId = attendanceDetails.getStudent().getId();
            int absenceNumber = attendanceDetailsRepository.findStudentAbsenceLecture(studentId, sectionId).size();
            double rate = 0 ;
            try {
                rate = 100.0 - (1.0 * absenceNumber / lectureNumber) * 100;
            }catch (Exception ex){
            }
            attendanceBySection.setRate(rate);
            attendanceBySection.setIdOfStudent(studentId);
            attendanceBySection.setAbsentLecture(absenceNumber);

            attendanceBySectionAndStudentDTOS.add(attendanceBySection);
        }
        return  attendanceBySectionAndStudentDTOS;
    }
    public void addAutoAttendance(long attendanceCode,  long studentId, long lectureId  ) {

//        Optional<Lecture> lecture = this.lectureRepository.findById(lectureId);
        AttendanceDetails attendanceDetails = this.getAttendanceDetailsByLectureAndStudent(lectureId,studentId);
        if(attendanceDetails ==null){
            throw new StudentFieldNotUniqueException(null,"Attendance Registration Failed !!");
        }else {
            if (attendanceDetails.getStudent().getId() == studentId) {
                if ((attendanceDetails.getLecture().getAttendanceCode() == attendanceCode) && (attendanceDetails.getLecture().getAttendanceStatus())) {
                    attendanceDetails.setAttendanceStatus("Present");
                    this.save(attendanceDetails);
                } else {
                    throw new StudentFieldNotUniqueException(null,"Attendance Registration Failed !!");
                }
            }else{
                throw new StudentFieldNotUniqueException(null,"Attendance Registration Failed !!");

            }
        }
    }

}
