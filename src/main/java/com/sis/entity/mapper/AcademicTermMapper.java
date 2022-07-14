package com.sis.entity.mapper;

import com.sis.dto.AcademicTermDTO;
import com.sis.entity.AcademicTerm;
import com.sis.util.PageResult;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

import static java.util.stream.Collectors.toCollection;

@Component
@AllArgsConstructor
public class AcademicTermMapper implements Mapper<AcademicTerm, AcademicTermDTO> {

    private AcademicYearMapper academicYearMapper;

    @Override
    public AcademicTermDTO toDTO(AcademicTerm entity) {
        AcademicTermDTO academicTermDTO = new AcademicTermDTO();
        academicTermDTO.setId(entity.getId());
        academicTermDTO.setCode(entity.getCode());
        academicTermDTO.setName(entity.getName());
        academicTermDTO.setEnd_date(entity.getEndDate());
        academicTermDTO.setStart_date(entity.getStartDate());
        academicTermDTO.setStatus(entity.isStatus());
        if (entity.getAcademicYear() != null) {
            academicTermDTO.setAcademicYearDTO(academicYearMapper.toDTO(entity.getAcademicYear()));
        }
        return academicTermDTO;
    }

    @Override
    public AcademicTerm toEntity(AcademicTermDTO dto) {
        AcademicTerm academicTerm = new AcademicTerm();
        academicTerm.setCode(dto.getCode());
        academicTerm.setName(dto.getName());
        academicTerm.setId(dto.getId());
        academicTerm.setStartDate(dto.getStart_date());
        academicTerm.setEndDate(dto.getEnd_date());
        academicTerm.setStatus(dto.isStatus());
        if(dto.getStart_date().after(dto.getEnd_date())){
            throw new IllegalArgumentException("date not valid");
        }
        if (dto.getAcademicYearDTO() != null) {
            academicTerm.setAcademicYear(academicYearMapper.toEntity(dto.getAcademicYearDTO()));
        }
        return academicTerm;
    }

    @Override
    public ArrayList<AcademicTermDTO> toDTOs(Collection<AcademicTerm> academicTerms) {
        return academicTerms.stream().map(entity -> toDTO(entity)).collect(toCollection(ArrayList<AcademicTermDTO>::new));
    }

    @Override
    public ArrayList<AcademicTerm> toEntities(Collection<AcademicTermDTO> academicTermDTOS) {
        return academicTermDTOS.stream().map(dto -> toEntity(dto)).collect(toCollection(ArrayList<AcademicTerm>::new));
    }

    @Override
    public PageResult<AcademicTermDTO> toDataPage(PageResult<AcademicTerm> entity) {
        return new PageResult<>(entity.getData().stream().map(academicTerm ->
                toDTO(academicTerm)).collect(toCollection(ArrayList<AcademicTermDTO>::new))
                , entity.getTotalCount(), entity.getPageSize(), entity.getCurrPage());
    }
}
