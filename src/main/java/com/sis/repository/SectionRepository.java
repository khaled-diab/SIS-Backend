package com.sis.repository;

import com.sis.entity.Section;

public interface SectionRepository extends Baserepository<Section> {

    Section findSectionBySectionNumberAndCollegeIdAndDepartmentId(
            String sectionNumber, long college, long department);
}
