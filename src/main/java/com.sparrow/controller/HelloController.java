package com.sparrow.controller;

import com.sparrow.exception.BusinessException;
import com.sparrow.vo.HelloVO;
import com.sparrow.web.support.ViewWithModel;

/**
 * @author harry
 * @date 2018/1/31
 */
public class HelloController {
    public ViewWithModel hello() throws BusinessException {
        return ViewWithModel.forward(new HelloVO("我来自遥远的sparrow 星球,累死我了..."));
    }

    public ViewWithModel fly() throws BusinessException {
        return ViewWithModel.redirect("hello.jsp", new HelloVO("我是从fly.jsp飞过来，你是不是没感觉？"));
    }
}
