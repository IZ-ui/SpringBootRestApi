package com.bell.dem.util;

import com.bell.dem.exception.CustomValidationException;
import org.springframework.validation.BindingResult;

import java.util.stream.Collectors;

public class Validator {
    public static void validate(BindingResult result) {
        if (result.hasErrors()) {
            String errorFieldsMsg = result.getFieldErrors().stream()
                    .map(fe -> String.format("[%s] %s", fe.getField(), fe.getDefaultMessage()))
                    .collect(Collectors.joining("\n"));
            throw new CustomValidationException(errorFieldsMsg);
        }
    }
}
