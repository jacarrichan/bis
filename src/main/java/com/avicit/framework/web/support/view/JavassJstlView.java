package com.avicit.framework.web.support.view;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.view.JstlView;

/**
 * 添加对于JavassContentNegotiatingViewResolver.java 的content-type支持
 * @author Zhang Kaitao
 *
 * @version 1.0, 2010-8-18
 */
public class JavassJstlView extends JstlView {
    
    @Override
    protected void exposeHelpers(HttpServletRequest request) throws Exception {
        super.exposeHelpers(request);
    }
    
}
