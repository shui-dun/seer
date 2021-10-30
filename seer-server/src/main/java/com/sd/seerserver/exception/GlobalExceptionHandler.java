package com.sd.seerserver.exception;

import com.sd.seerserver.entity.Response;
import com.sd.seerserver.enumeration.StatusCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Response<?> exceptionHandler(Exception e) {
        log.error("e=\"{}\". Stack is :\n", e.getMessage(), e);
        return new Response<>(StatusCodeEnum.SERVER_ERROR);
    }

    @ExceptionHandler(UnauthenticatedException.class)
    @ResponseBody
    public Response<?> unauthenticatedExceptionHandler(Exception e) {
        log.info(e.getMessage());
        return new Response<>(StatusCodeEnum.UNAUTHENTICATED);
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseBody
    public Response<?> unauthorizedExceptionHandler(Exception e) {
        log.info(e.getMessage());
        return new Response<>(StatusCodeEnum.UNAUTHORIZED);
    }
}