package com.sis.controller;

import com.sis.dto.BaseDTO;
import com.sis.entity.BaseEntity;
import com.sis.entity.mapper.Mapper;
import com.sis.service.BaseServiceImp;
import com.sis.util.MessageResponse;
import com.sis.util.PageQueryUtil;
import com.sis.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


public class BaseController <T extends BaseEntity, DTO extends BaseDTO>{
	@Autowired
	private BaseServiceImp<T> baseService;
	
	@Autowired
	private Mapper<T, DTO>mapper;
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public DTO get(@PathVariable(value = "id") Long id) {		
		return mapper.toDTO(baseService.findById(id));
	}
	
	@RequestMapping(value="/all", method = RequestMethod.GET)
	public List<DTO> list() {
		return mapper.toDTOs(baseService.findAll());
	}


	@RequestMapping(value="/datapage", method = RequestMethod.POST)
	public PageResult<DTO> getDataPage(@RequestBody PageQueryUtil pageUtil) {
		return mapper.toDataPage(baseService.getDataPage(pageUtil));
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public MessageResponse create(@RequestBody DTO dto) {
		 baseService.save(mapper.toEntity(dto));
		 return new MessageResponse("Item has been saved successfully");
	}
	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	public MessageResponse update(@PathVariable(value = "id") Long id, @RequestBody DTO dto) {
		 baseService.save(mapper.toEntity(dto));
		 return new MessageResponse("Item has been updated successfully");
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public MessageResponse delete(@PathVariable(value = "id") Long id) {
		baseService.deleteById(id);
		return new MessageResponse("Item has been deleted successfully");
	}
	
}
