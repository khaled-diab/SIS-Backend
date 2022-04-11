package com.sis.dao;

import com.sis.entities.Section;

public interface SectionRepository extends BaseDao<Section> {

    Section findSectionBySectionNumberAndCollegeIdAndDepartmentId(
            String sectionNumber, long college, long department);
}
