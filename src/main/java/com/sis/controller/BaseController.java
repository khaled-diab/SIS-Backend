package com.sis.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.sis.dto.BaseDTO;
import com.sis.entities.BaseEntity;
import com.sis.entities.mapper.Mapper;
import com.sis.service.BaseServiceImp;
import com.sis.util.MessageResponse;
import com.sis.util.PageQueryUtil;
import com.sis.util.PageResult;


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
		System.out.println("ho");
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
