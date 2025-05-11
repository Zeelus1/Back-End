package com.zeelus.zeelus.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExceptionDefaultHandlerDTO {
    private String message;
    private String field;
}