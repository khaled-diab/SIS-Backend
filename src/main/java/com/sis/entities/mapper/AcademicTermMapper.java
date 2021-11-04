package com.sis.entities.mapper;

import com.sis.dao.AcademicYearDao;
import com.sis.dto.AcademicTermDTO;
import com.sis.entities.AcademicTerm;
import com.sis.entities.AcademicYear;
import com.sis.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import static java.util.stream.Collectors.toCollection;


public class AcademicTermMapper implements  Mapper<AcademicTerm , AcademicTermDTO>{
    @Autowired
    private AcademicYearDao academicYearDao ;
    @Override
    public AcademicTermDTO toDTO(AcademicTerm entity) {
        AcademicTermDTO academicTermDTO = new AcademicTermDTO();
        academicTermDTO.setId(entity.getId());
        academicTermDTO.setCode(entity.getCode());
        academicTermDTO.setName(entity.getName());
        academicTermDTO.setEnd_date(entity.getEndDate());
        academicTermDTO.setStart_date(entity.getStartDate());
        academicTermDTO.setYear_id(entity.getAcademicYear().get().getId());
        academicTermDTO.setYear_name(entity.getAcademicYear().get().getName());
        return academicTermDTO;
    }

    @Override
    public AcademicTerm toEntity(AcademicTermDTO dto) {
        AcademicTerm academicTerm =new AcademicTerm();
        academicTerm.setCode(dto.getCode());
        academicTerm.setName(dto.getName());
        academicTerm.setId(dto.getId());
        academicTerm.setEndDate(dto.getEnd_date());
        academicTerm.setStartDate(dto.getStart_date());
        academicTerm.setAcademicYear(academicYearDao.findById(dto.getYear_id()));
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
    public PageResult<AcademicTermDTO> toDataPage(PageResult<AcademicTerm> entities) {
        return new PageResult<>(entities.getData().stream().map(entity ->
                toDTO(entity)).collect(toCollection(ArrayList<AcademicTermDTO>::new))
                , entities.getTotalCount(), entities.getPageSize(), entities.getCurrPage());
    }
}
