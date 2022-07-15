package com.sis.repository;

import com.sis.entity.Section;

import java.util.List;

public interface SectionRepository extends BaseRepository<Section> {

    Section findSectionBySectionNumberAndCollegeIdAndDepartmentId(
            String sectionNumber, long college, long department);

    List<Section> getSectionsByCourseId(Long courseId);
}
