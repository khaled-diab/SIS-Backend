package com.sis.dto.dashboard;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminDashBoardModel {

    private Long activeStudents;
    private Long inActiveStudents;
    private Long activeStaff;
    private Long inActiveStaff;

}
