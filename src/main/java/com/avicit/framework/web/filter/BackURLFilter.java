package com.avicit.framework.web.filter;


import org.apache.commons.lang.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * User: Zhang Kaitao
 * Date: 12-1-2 下午5:06
 * Version: 1.0
 */
public class BackURLFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String backURL = request.getParameter("BackURL");
        if(StringUtils.isEmpty(backURL)) {
            backURL = request.getHeader("Referer");
        }


        request.setAttribute("BackURL", backURL);

        request.setAttribute("mBackURL", getMBackURL(request, response));

        filterChain.doFilter(request, response);

    }

    public static String getMBackURL(HttpServletRequest request, HttpServletResponse response) {
        String basePath = request.getScheme() + "://" + request.getServerName();
        if (request.getServerPort() != 80) {
            basePath += ":" + request.getServerPort();
        }
        String requestURI = request.getRequestURI();
        String queryString = request.getQueryString();

        String backURL = basePath + requestURI + (queryString == null || queryString.length() == 0 ? "" : "?" + queryString);

        /*
        try {
            backURL = response.encodeURL(backURL);
            backURL = URLEncoder.encode(backURL, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        */
        return backURL;
    }



    @Override
    public void destroy() {

    }
}
