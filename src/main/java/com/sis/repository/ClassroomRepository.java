package com.sis.repository;

import com.sis.entity.Classroom;

import java.util.List;

public interface ClassroomRepository extends BaseRepository<Classroom> {
    List<Classroom> getClassroomsByBuildingId(Long buildingId);

}
