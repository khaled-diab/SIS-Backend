package com.sis.repository;

import com.sis.entity.College;
import com.sis.entity.Department;
import com.sis.entity.Section;

public interface SectionRepository extends BaseDao<Section> {

    Section findSectionBySectionNumberAndCollegeAndDepartment(
            String sectionNumber, College college, Department department);
}
