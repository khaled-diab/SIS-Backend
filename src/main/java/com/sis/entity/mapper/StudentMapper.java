package com.sis.entity.mapper;


import com.sis.dto.student.StudentDTO;

import com.sis.entity.Student;
import com.sis.entity.security.User;
import com.sis.repository.RoleRepository;
import com.sis.repository.UserRepository;
import com.sis.util.PageResult;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import static java.util.stream.Collectors.toCollection;

@Component
@AllArgsConstructor
public class StudentMapper implements Mapper<Student,StudentDTO> {



	private DepartmentMapper departmentMapper;

	private CollegeMapper collegeMapper;

	private AcademicProgramMapper academicProgramMapper;


	private UserMapper userMapper;
	private UserRepository userRepository;
	private RoleRepository roleRepository;

	private final PasswordEncoder passwordEncoder;



	@Override
	public StudentDTO toDTO(Student entity) {

		StudentDTO dto = new StudentDTO();
		dto.setAlternativeMail(entity.getAlternativeMail());
		dto.setUniversityMail(entity.getUniversityMail());
		dto.setBirthDate(entity.getBirthDate());
		dto.setId(entity.getId());
		dto.setNameEn(entity.getNameEn());
		dto.setNationality(entity.getNationality());
		dto.setLevel(entity.getLevel());
		dto.setYear(entity.getYear());
		dto.setNameAr(entity.getNameAr());
		dto.setNationalId(entity.getNationalId());
		dto.setPhone(entity.getPhone());
		dto.setParentPhone(entity.getParentPhone());
		dto.setPhoto(entity.getPhoto());
		dto.setUniversityId(entity.getUniversityId());
		if(entity.getDepartmentId()!=null) {
			dto.setDepartmentDTO(this.departmentMapper.toDTO(entity.getDepartmentId()));

		}
		if(entity.getCollegeId()!=null) {
			dto.setCollegeDTO(this.collegeMapper.toDTO(entity.getCollegeId()));
		}
		if(entity.getProgramId()!=null) {
			dto.setAcademicProgramDTO(this.academicProgramMapper.toDTO(entity.getProgramId()));
		}
		if(entity.getUser()!=null) {
			dto.setUser(userMapper.toDTO(entity.getUser()));
		}
		return dto;
	}

	@Override
	public Student toEntity(StudentDTO dto) {

		Student entity=new Student();
		if(dto != null) {
			entity.setId(dto.getId());
			entity.setAlternativeMail(dto.getAlternativeMail());
			entity.setLevel(dto.getLevel());
			entity.setYear(dto.getYear());
			entity.setBirthDate(dto.getBirthDate());
			entity.setNationality(dto.getNationality());
			entity.setNameEn(dto.getNameEn());
			entity.setNameAr(dto.getNameAr());
			entity.setParentPhone(dto.getParentPhone());
			entity.setPhone(dto.getPhone());
			entity.setPhoto(dto.getPhoto());
			entity.setUniversityId(dto.getUniversityId());
			entity.setNationalId(dto.getNationalId());
			entity.setUniversityMail(dto.getUniversityMail());
			if (dto.getCollegeDTO() != null) {
				entity.setCollegeId(this.collegeMapper.toEntity(dto.getCollegeDTO()));
			}
			if (dto.getDepartmentDTO() != null ) {
				if(dto.getDepartmentDTO().getId()==-1){
					entity.setDepartmentId(null);
				}else {
					entity.setDepartmentId(this.departmentMapper.toEntity(dto.getDepartmentDTO()));
				}
			}
			if (dto.getAcademicProgramDTO() != null ) {
				if (dto.getAcademicProgramDTO().getId() == -1) {
					entity.setProgramId(null);
				} else {
					entity.setProgramId(this.academicProgramMapper.toEntity(dto.getAcademicProgramDTO()));
				}
			}
			User user;
			if(dto.getUser()==null){
				user = new User();
				user.setPassword(passwordEncoder.encode("changeme"));
			}else {
				Optional<User> user1 = this.userRepository.findById(dto.getUser().getId());
				user=user1.get();
			}
			user.setRole(roleRepository.getRoleStudent());
			user.setEmail(dto.getUniversityMail());
			user.setUsername(dto.getUniversityMail());
			user.setType("STUDENT");
			user.setFirstname(dto.getNameAr());
			user.setLastname(dto.getNameAr());
			user = userRepository.save(user);
			entity.setUser(user);
		}
		return entity;

	}

	@Override
	public ArrayList<StudentDTO> toDTOs(Collection<Student> students) {
		return students.stream().map(entity -> toDTO(entity)).collect(toCollection(ArrayList<StudentDTO>::new));
	}

	@Override
	public ArrayList<Student> toEntities(Collection<StudentDTO> studentDTOS) {
		return studentDTOS.stream().map(dto -> toEntity(dto)).collect(toCollection(ArrayList<Student>::new));
	}

	@Override
	public PageResult<StudentDTO> toDataPage(PageResult<Student> entities) {
		return new PageResult<>(entities.getData().stream().map(entity -> toDTO(entity)).collect(toCollection(ArrayList<StudentDTO>::new)), entities.getTotalCount(), entities.getPageSize(), entities.getCurrPage());

	}
}
