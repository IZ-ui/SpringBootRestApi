package com.bell.dem.controller;

import com.bell.dem.view.DataView;
import com.bell.dem.view.Result;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * Контроллер для оборачивания успешных ответов от контроллеров
 */
@ControllerAdvice
public class AdviceController implements ResponseBodyAdvice<Object> {

    /**
     * Проверка, на случай, если custom exception
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return !methodParameter.getParameterType().getSimpleName().equals("ResponseEntity");
    }

    /**
     * Оборачивание ответа в {"data":[]} или {"result":"success"}
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest request,
                                  ServerHttpResponse response) {
        if (body == null) {
            return new Result("success");
        } else {
            return new DataView(body);
        }
    }
}
