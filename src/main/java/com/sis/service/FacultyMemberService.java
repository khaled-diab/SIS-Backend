package com.sis.service;

import com.sis.dto.FacultyMemberDTO;
import com.sis.entities.mapper.Mapper;
import com.sis.specification.FacultyMemberSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import com.sis.dao.FacultyMemberRepository;
import com.sis.entities.FacultyMember;

import java.util.List;
import java.util.Optional;

@Service
public class FacultyMemberService extends BaseServiceImp<FacultyMember>{
	
	@Autowired
	private FacultyMemberRepository facultyMemberRepository;

	@Autowired
	private Mapper<FacultyMember, FacultyMemberDTO> mapper;

	@Override
	public JpaRepository<FacultyMember, Long> Repository() {
		return facultyMemberRepository;
	}

	public void update(FacultyMemberDTO dto){
		Optional<FacultyMember> object = facultyMemberRepository.findById(dto.getId());
		if(object.isPresent()){
			FacultyMember member = mapper.toEntity(dto);
			facultyMemberRepository.save(member);
		}
		else{
			throw new RuntimeException("faculty member not found");
		}
	}

	public List<FacultyMember> search(String key){
		FacultyMemberSpecification facultyMemberSpecification = new FacultyMemberSpecification(key);
		return facultyMemberRepository.findAll(facultyMemberSpecification);
	}

}
