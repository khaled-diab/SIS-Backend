package com.sis.service;

import com.sis.dto.FacultyMemberDTO;
import com.sis.entities.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import com.sis.dao.FacultyMemberRepository;
import com.sis.entities.FacultyMember;

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

	public void update(long key, FacultyMemberDTO dto){
		Optional<FacultyMember> object = facultyMemberRepository.findById(key);
		if(object.isPresent()){
			FacultyMember member = mapper.toEntity(dto);
			facultyMemberRepository.save(member);
		}
		else{
			throw new RuntimeException("error");
		}
	}

}
