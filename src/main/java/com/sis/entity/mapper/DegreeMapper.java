package com.sis.entity.mapper;

import com.sis.dto.DegreeDTO;
import com.sis.entity.Degree;
import com.sis.util.PageResult;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

import static java.util.stream.Collectors.toCollection;

@Component
@AllArgsConstructor
public class DegreeMapper implements Mapper<Degree, DegreeDTO> {

//	private FacultyMemberMapper facultyMemberMapper;

	@Override
	public ArrayList<DegreeDTO> toDTOs(Collection<Degree> entity) {
		return entity.stream().map(this::toDTO).collect(toCollection(ArrayList<DegreeDTO>::new));
	}

	@Override
	public PageResult<DegreeDTO> toDataPage(PageResult<Degree> entity) {
		return new PageResult<>(entity.getData().stream().map(this::toDTO).collect(toCollection(ArrayList<DegreeDTO>::new)), entity.getTotalCount(), entity.getPageSize(), entity.getCurrPage());
	}

	@Override
    public ArrayList<Degree> toEntities(Collection<DegreeDTO> dtos) {
        return dtos.stream().map(this::toEntity).collect(toCollection(ArrayList<Degree>::new));
    }

	@Override
	public DegreeDTO toDTO(Degree entity) {
		DegreeDTO dto = new DegreeDTO();
		dto.setId(entity.getId());
		dto.setNameAr(entity.getNameAr());
		dto.setNameEn(entity.getNameEn());
//		if(entity.getFacultyMemberCollection()!=null) {
//			dto.setFacultyMemberDTO(this.facultyMemberMapper.toDTO(entity.getFacultyMemberCollection()));
//		}
		return dto;
	}
	@Override
	public Degree toEntity(DegreeDTO dto) {
		Degree entity=new Degree();
		if(dto != null) {
			entity.setId(dto.getId());
			entity.setNameAr(dto.getNameAr());
			entity.setNameEn(dto.getNameEn());
//		if(dto.getFacultyMemberDTO()!=null) {
//			entity.setFacultyMemberCollection(this.facultyMemberMapper.toEntity(dto.getFacultyMemberDTO()));
//		}
		}
		return entity;
	}

}
