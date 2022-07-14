package com.sis.entity.mapper;

import com.sis.dto.section.SectionTableRecordsDTO;
import com.sis.entity.Section;
import com.sis.util.PageResult;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

import static java.util.stream.Collectors.toCollection;

@Component
@AllArgsConstructor
public class SectionTableRecordsMapper implements Mapper<Section, SectionTableRecordsDTO> {

    @Override
    public ArrayList<SectionTableRecordsDTO> toDTOs(Collection<Section> entity) {
        return entity.stream().map(this::toDTO).collect(toCollection(ArrayList<SectionTableRecordsDTO>::new));
    }

    @Override
    public PageResult<SectionTableRecordsDTO> toDataPage(PageResult<Section> entity) {
        return new PageResult<>(entity.getData().stream().map(this::toDTO).collect(toCollection(ArrayList<SectionTableRecordsDTO>::new)), entity.getTotalCount(), entity.getPageSize(), entity.getCurrPage());
    }

    @Override
    public ArrayList<Section> toEntities(Collection<SectionTableRecordsDTO> dtos) {
        return dtos.stream().map(this::toEntity).collect(toCollection(ArrayList<Section>::new));
    }

    @Override
    public SectionTableRecordsDTO toDTO(Section entity) {
        SectionTableRecordsDTO dto = new SectionTableRecordsDTO();
        dto.setId(entity.getId());
        dto.setSectionNumber(entity.getSectionNumber());
        dto.setCapacity(entity.getCapacity());
        if (entity.getCourse() != null) {
            dto.setCourseName(entity.getCourse().getNameEn());
        }
        if (entity.getMajor() != null) {
            dto.setMajorName(entity.getMajor().getNameEn());
        }
        if (entity.getStudyType() != null) {
            dto.setStudyTypeName(entity.getStudyType().getNameEn());
        }
        if (entity.getCollege() != null) {
            dto.setCollegeName(entity.getCollege().getNameEn());
        }
        if (entity.getDepartment() != null) {
            dto.setDepartmentName(entity.getDepartment().getNameEn());
        }
        return dto;
    }

    @Override
    public Section toEntity(SectionTableRecordsDTO sectionTableRecordsDTO) {
        return null;
    }

}
