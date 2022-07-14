package com.sis.dto.attendanceDetails;

import com.sis.dto.BaseDTO;

import lombok.*;



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
