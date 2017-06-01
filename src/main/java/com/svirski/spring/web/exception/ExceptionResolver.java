package com.svirski.spring.web.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Vasili_Svirski on 6/1/2017.
 */
@ControllerAdvice
public class ExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            Object o,
            Exception e) {
        ModelAndView model = new ModelAndView("errorPage");
        model.addObject("exceptionInfo", e);
        return model;
    }
}
