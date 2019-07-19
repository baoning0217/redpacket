package com.xishanqu.redpacket.common.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * @Function:
 * @Author: BaoNing
 * @Date: 2019-07-11
 */
@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ModelAndView uploadException(MaxUploadSizeExceededException e) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("msg","上传文件大小超出限制");
        modelAndView.setViewName("error");
        return modelAndView;
    }

}
