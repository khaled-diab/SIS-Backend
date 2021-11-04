package com.sis.service;

import com.sis.dto.FacultyMemberDTO;
import com.sis.entities.mapper.FacultyMemberMapper;
import com.sis.entities.mapper.Mapper;
import com.sis.specification.FacultyMemberSpecification;
import com.sis.util.PageQueryUtil;
import com.sis.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
	private FacultyMemberMapper facultyMemberMapper;

	@Override
	public JpaRepository<FacultyMember, Long> Repository() {
		return facultyMemberRepository;
	}

	public void update(FacultyMemberDTO dto){
		Optional<FacultyMember> object = facultyMemberRepository.findById(dto.getId());
		if(object.isPresent()){
			FacultyMember member = facultyMemberMapper.toEntity(dto);
			facultyMemberRepository.save(member);
		}
		else{
			throw new RuntimeException("faculty member not found");
		}
	}

	public PageResult<FacultyMemberDTO> search(PageQueryUtil pageUtil, String key){

		Pageable pageable = PageRequest.of(pageUtil.getPage() - 1, pageUtil.getLimit());

		FacultyMemberSpecification facultyMemberSpecification=new FacultyMemberSpecification(key);

		Page<FacultyMember> coursePage = facultyMemberRepository.findAll(facultyMemberSpecification,pageable);

		PageResult<FacultyMember> pageResult = new PageResult<>(coursePage.getContent(), (int) coursePage.getTotalElements(),
				pageUtil.getLimit(), pageUtil.getPage());

		return facultyMemberMapper.toDataPage(pageResult);
	}


}
