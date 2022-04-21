package com.sis.util;

import com.sis.dto.BaseDTO;
import lombok.Builder;

@Builder
public class MessageResponse extends BaseDTO {
    private String message;
    private String field;


    public MessageResponse(String message, String field) {
        this.message = message;
        this.field = field;
    }

    public MessageResponse(String message) {
        this.message = message;

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
