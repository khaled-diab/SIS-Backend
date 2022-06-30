package com.sis.util;

import lombok.Builder;

@Builder
public class MessageResponse {

    private Integer status;
    private String message;
    private String field;

    public MessageResponse() {
    }

    public MessageResponse(Integer status, String message, String field) {
        this.status = status;
        this.message = message;
        this.field = field;
    }

    public MessageResponse(String message, String field) {
        this.message = message;
        this.field = field;
    }

    public MessageResponse(String message) {
        this.message = message;

    }

    public MessageResponse(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
