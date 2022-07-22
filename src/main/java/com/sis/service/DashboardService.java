package com.sis.service;

import com.sis.dto.dashboard.AdminDashBoardModel;
import com.sis.repository.FacultyMemberRepository;
import com.sis.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final StudentRepository studentRepository;
    private final FacultyMemberRepository facultyMemberRepository;

    public AdminDashBoardModel collectAdminDashboardData() {
        CompletableFuture<Long> activeStudents = CompletableFuture.supplyAsync(studentRepository::countStudentsByUser_IdNotNull);
        CompletableFuture<Long> inActiveStudents = CompletableFuture.supplyAsync(studentRepository::countStudentsByUser_IdIsNull);
        CompletableFuture<Long> activeStaff = CompletableFuture.supplyAsync(facultyMemberRepository::countFacultyMembersByUser_IdNotNull);
        CompletableFuture<Long> inActiveStaff = CompletableFuture.supplyAsync(facultyMemberRepository::countFacultyMembersByUser_IdIsNull);
        CompletableFuture.allOf(activeStudents, inActiveStudents, activeStaff, inActiveStaff);
        return AdminDashBoardModel.builder()
                .activeStudents(activeStudents.join())
                .inActiveStudents(inActiveStudents.join())
                .activeStaff(activeStaff.join())
                .inActiveStaff(inActiveStaff.join())
                .build();
    }
}
