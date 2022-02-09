package com.sis.entities.mapper;

import com.sis.dto.DegreeDTO;
import com.sis.dto.college.CollegeDTO;
import com.sis.dto.facultyMember.FacultyMemberDTO;
import com.sis.entities.Degree;
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
	public ArrayList<DegreeDTO> toDTOs(Collection<Degree> entities) {
		return entities.stream().map(this::toDTO).collect(toCollection(ArrayList<DegreeDTO>::new));
	}
	@Override
	public PageResult<DegreeDTO> toDataPage(PageResult<Degree> entities) {
		return new PageResult<>(entities.getData().stream().map(this::toDTO).collect(toCollection(ArrayList<DegreeDTO>::new)), entities.getTotalCount(), entities.getPageSize(), entities.getCurrPage());
	}
	@Override
	public ArrayList<Degree> toEntities(Collection<DegreeDTO> dtos) {
		return dtos.stream().map(this::toEntity).collect(toCollection(ArrayList<Degree>::new));
	}
	@Override
	public DegreeDTO toDTO(Degree entity) {
		DegreeDTO dto=new DegreeDTO();
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
		entity.setId(dto.getId());
		entity.setNameAr(dto.getNameAr());
		entity.setNameEn(dto.getNameEn());
//		if(dto.getFacultyMemberDTO()!=null) {
//			entity.setFacultyMemberCollection(this.facultyMemberMapper.toEntity(dto.getFacultyMemberDTO()));
//		}
		return entity;
	}

}
