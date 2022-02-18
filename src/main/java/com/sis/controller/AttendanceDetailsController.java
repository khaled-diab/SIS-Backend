package com.sis.controller;


import com.sis.dto.attendanceDetails.AttendanceDetailsDTO;
import com.sis.entities.AttendanceDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/attendanceDetails")
public class AttendanceDetailsController extends BaseController<AttendanceDetails, AttendanceDetailsDTO> {

}
