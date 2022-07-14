package com.sis.dto.attendanceDetails;

import com.sis.dto.BaseDTO;
import com.sis.dto.course.CourseDTO;
import com.sis.dto.lecture.LectureDTO;
import com.sis.dto.section.SectionDTO;
import com.sis.dto.student.StudentDTO;
import com.sis.dto.student.StudentRecordDTO;
import com.sis.util.Constants;
import lombok.*;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalTime;
import java.util.Date;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceDetailsDTO extends BaseDTO{

    private long studentId;
    private long universityId;
    private String nameAr;
    private String nameEn;
    private long sectionId;
    private long lectureId;
    private String attendanceStatus;
//    private  String year;
    /*Relations instances*/
//    private String departmentName;
//    private String collegeName;

//    private Date attendanceDate;
//    private String lectureStartTime;
//    private String lectureEndTime;
//    private SectionDTO sectionDTO;



//    private String sectionNumber;
    //    private LectureDTO lectureDTO;

}
