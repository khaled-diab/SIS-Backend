package com.sis.controller;

import com.sis.dto.dashboard.AdminDashBoardModel;
import com.sis.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashBoardController {

    private final DashboardService dashboardService;

    @GetMapping("/admin")
    public AdminDashBoardModel collectAdminDashboardData() {
        return dashboardService.collectAdminDashboardData();
    }
}
