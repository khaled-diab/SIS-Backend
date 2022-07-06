package com.sis.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Service
@AllArgsConstructor
public class PojoValidationService {

    private final Validator validator;

    public Set<ConstraintViolation<Object>> validate(Object object) {
        return validator.validate(object);
    }
}
