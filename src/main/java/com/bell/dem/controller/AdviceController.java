package com.bell.dem.controller;

import com.bell.dem.exception.ErrorView;
import com.bell.dem.view.DataView;
import com.bell.dem.view.ResultView;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * Контроллер для оборачивания успешных ответов от контроллеров
 */
@RestControllerAdvice
public class AdviceController implements ResponseBodyAdvice<Object> {

    /**
     * Всегда true
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    /**
     * Оборачивание ответа в {"data":[]}, {"error":""} или {"result":"success"}
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest request,
                                  ServerHttpResponse response) {
        if (body instanceof ErrorView) {
            return body;
        } else if (body == null) {
            return new ResultView("success");
        } else {
            return new DataView(body);
        }
    }
}
