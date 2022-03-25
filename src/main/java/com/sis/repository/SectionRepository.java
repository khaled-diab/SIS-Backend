package com.sis.repository;

import com.sis.entities.College;
import com.sis.entities.Department;
import com.sis.entities.Section;

public interface SectionRepository extends BaseDao<Section> {

    Section findSectionBySectionNumberAndCollegeAndDepartment(
            String sectionNumber, College college, Department department);
}
