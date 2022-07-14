package com.sis.entity.mapper;

import com.sis.dto.timetable.TimetableTableRecordsDTO;
import com.sis.entity.Timetable;
import com.sis.util.PageResult;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

import static java.util.stream.Collectors.toCollection;

@Component
@AllArgsConstructor
public class TimetableTableRecordsMapper implements Mapper<Timetable, TimetableTableRecordsDTO> {

    @Override
    public ArrayList<TimetableTableRecordsDTO> toDTOs(Collection<Timetable> entity) {
        return entity.stream().map(this::toDTO).collect(toCollection(ArrayList<TimetableTableRecordsDTO>::new));
    }

    @Override
    public PageResult<TimetableTableRecordsDTO> toDataPage(PageResult<Timetable> entity) {
        return new PageResult<>(entity.getData().stream().map(this::toDTO).collect(toCollection(ArrayList<TimetableTableRecordsDTO>::new)), entity.getTotalCount(), entity.getPageSize(), entity.getCurrPage());
    }

    @Override
    public ArrayList<Timetable> toEntities(Collection<TimetableTableRecordsDTO> dtos) {
        return dtos.stream().map(this::toEntity).collect(toCollection(ArrayList<Timetable>::new));
    }

    @Override
    public TimetableTableRecordsDTO toDTO(Timetable entity) {
        TimetableTableRecordsDTO dto = new TimetableTableRecordsDTO();
        dto.setId(entity.getId());
        dto.setDay(entity.getDay());
        dto.setStartTime(entity.getStartTime());
        dto.setEndTime(entity.getEndTime());
        if (entity.getLectureType() != null) {
            dto.setLectureTypeName(entity.getLectureType().getNameEn());
        }
        if (entity.getFacultyMember() != null) {
            dto.setFacultyMemberName(entity.getFacultyMember().getNameEn());
        }
        if (entity.getCourse() != null) {
            dto.setCourseName(entity.getCourse().getNameEn());
        }
        if (entity.getSection() != null) {
            dto.setSectionName(entity.getSection().getSectionNumber());
        }
        if (entity.getBuilding() != null) {
            dto.setBuildingName(entity.getBuilding().getNameEn());
        }
        if (entity.getClassroom() != null) {
            dto.setClassroomName(entity.getClassroom().getName_en());
        }
        return dto;
    }

    @Override
    public Timetable toEntity(TimetableTableRecordsDTO timetableTableRecordsDTO) {
        return null;
    }

}
