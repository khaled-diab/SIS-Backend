package com.sis.dto;

public abstract class BaseDTO {
    private long id;

    public BaseDTO() {

    }

    public BaseDTO(long id) {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
