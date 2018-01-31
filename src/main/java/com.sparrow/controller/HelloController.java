package com.sparrow.controller;

import com.sparrow.exception.BusinessException;
import com.sparrow.vo.HelloVO;
import com.sparrow.web.support.ViewWithModel;

/**
 * @author harry
 */
public class HelloController {
    public ViewWithModel hello() throws BusinessException {
        return ViewWithModel.forward("hello", new HelloVO("我来自遥远的sparrow 星球,累死我了..."));
    }

    public ViewWithModel fly() throws BusinessException {

        return ViewWithModel.redirect("fly-here", new HelloVO("我是从fly.jsp飞过来，你是不是没感觉？"));
    }

    public ViewWithModel transit() throws BusinessException {
        return ViewWithModel.transit("transit-here", new HelloVO("我从你这歇一会，最终我要到transit-here"));
    }
}
