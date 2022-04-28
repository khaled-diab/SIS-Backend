package com.sis.repository;

import com.sis.entity.Section;

public interface SectionRepository extends BaseRepository<Section> {

    Section findSectionBySectionNumberAndCollegeIdAndDepartmentId(
            String sectionNumber, long college, long department);
}
