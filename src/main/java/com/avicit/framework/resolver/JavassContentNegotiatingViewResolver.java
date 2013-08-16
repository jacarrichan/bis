/*
 * Copyright (c) 2009 Kongzhong.com
 * Last Modified By $ @ $
 * Current CVS Version Number: $
 */
package com.avicit.framework.resolver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;

import java.util.*;


public class JavassContentNegotiatingViewResolver extends ContentNegotiatingViewResolver {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(JavassContentNegotiatingViewResolver.class);
    private List<View> defaultViews;
    private List<ViewResolver> viewResolvers;

    @Override
    public void setDefaultViews(List<View> defaultViews) {
        super.setDefaultViews(defaultViews);
        this.defaultViews = defaultViews;
    }

    @Override
    public void setViewResolvers(List<ViewResolver> viewResolvers) {
        super.setViewResolvers(viewResolvers);
        this.viewResolvers = viewResolvers;
    }

    @Override
    public View resolveViewName(String viewName, Locale locale) throws Exception {
        RequestAttributes attrs = RequestContextHolder.getRequestAttributes();
        Assert.isInstanceOf(ServletRequestAttributes.class, attrs);
        ServletRequestAttributes servletAttrs = (ServletRequestAttributes) attrs;
        List<MediaType> requestedMediaTypes = getMediaTypes(servletAttrs.getRequest());
        Collections.sort(requestedMediaTypes);

        SortedMap<MediaType, View> views = new TreeMap<MediaType, View>();
        List<View> candidateViews = new ArrayList<View>();
        for (ViewResolver viewResolver : viewResolvers) {
            View view = viewResolver.resolveViewName(viewName, locale);
            if (view != null) {
                candidateViews.add(view);
            }
        }
        if (!CollectionUtils.isEmpty(defaultViews)) {
            candidateViews.addAll(defaultViews);
        }
        for (View candidateView : candidateViews) {
            MediaType viewMediaType = MediaType.parseMediaType(candidateView.getContentType());
            for (MediaType requestedMediaType : requestedMediaTypes) {
                if (includes(requestedMediaType, viewMediaType)) {
                    if (!views.containsKey(requestedMediaType)) {
                        views.put(requestedMediaType, candidateView);
                        break;
                    }
                }
            }
        }
        if (!views.isEmpty()) {
            MediaType mediaType = views.firstKey();
            View view = views.get(mediaType);
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Returning [" + view + "] based on requested media type '" + mediaType + "'");
            }
            return view;
        } else {
            return null;
        }
    }

    private boolean includes(MediaType first, MediaType other) {
        if (first == other) {
            return true;
        }
        if (first.getType().equals(other.getType())) {
            if (first.getSubtype().equals(other.getSubtype())) {
                return true;
            }
        }
        return false;
    }
}
