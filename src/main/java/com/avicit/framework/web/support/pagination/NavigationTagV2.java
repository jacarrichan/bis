package com.avicit.framework.web.support.pagination;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.tag.common.core.UrlSupport;

/**
 * 显示格式 上一页 1 2 3 4 5 下一页
 */
public class NavigationTagV2 extends TagSupport {
    static final long serialVersionUID = 2372405317744358833L;
    
    /**
     * request 中用于保存Page<E> 对象的变量名,默认为“page”
     */
    private String bean = "page";
    
    /**
     * 分页跳转的url地址,此属性必须
     */
    private String url = null;
    
    /**
     * 显示页码数量
     */
    private int number = 5;
    
    /**
     * 是否优化分页,仅对主键为数值型的有效
     */
    private boolean optimize;
    
    @Override
    public int doStartTag() throws JspException {
        JspWriter writer = pageContext.getOut();
        Page<?> onePage = (Page<?>)pageContext.getRequest().getAttribute(bean); 
        if (onePage == null) 
            return SKIP_BODY;
        url = resolveUrl(url, pageContext);
        Object firstModel = null;
        Object lastModel = null;

        if(onePage.getItems() != null && onePage.getItems().size() > 0) {
            firstModel = onePage.getItems().get(0);
            lastModel = onePage.getItems().get(onePage.getItems().size() - 1);
        }
        try {
            if (onePage.isHasPre()) {
                String preUrl = append(url, "pn", onePage.getIndex() - 1);
                if(optimize && firstModel != null) {
                    preUrl = append(preUrl, PageUtil.getIdName(firstModel), PageUtil.getIdValue(firstModel));
                    preUrl = append(preUrl, "pre", "true");
                }
                writer.print("<a href=\"" + preUrl + "\">上一页</a>&nbsp;");
                if(onePage.getIndex() > 2 && onePage.isHasNext()) {
                    writer.print("<a href=\""+append(url, "pn", 1)+"\">1</a>&nbsp;");
                }
            }
            //显示当前页码的前2页码和后两页码 
            //若1 则 1 2 3 4 5, 若2 则 1 2 3 4 5, 若3 则1 2 3 4 5,
            //若4 则 2 3 4 5 6 ,若10  则 8 9 10 11 12
            int currIndex = onePage.getIndex();
            int startIndex = (currIndex - 2 > 0)? currIndex - 2 : 1;  
            for(int i=1; i <= number && startIndex <= onePage.getContext().getPageCount(); startIndex++, i++) {
                if(startIndex == currIndex) {
                    writer.print(startIndex + "&nbsp;");
                    continue;
                }
                String pageUrl  = append(url, "pn", startIndex);
                int offset = 0;
                long id = 0;
                if(optimize && firstModel != null && lastModel != null) {
                    if(startIndex < currIndex) {
                        //pre
                        pageUrl = append(pageUrl, "pre", "true");
                        offset = (startIndex - currIndex + 1) * onePage.getContext().getPageSize();
                        id = Long.valueOf(PageUtil.getIdValue(firstModel)) + offset;
                    }
                    else {
                        //next
                        offset = (startIndex - currIndex - 1) * onePage.getContext().getPageSize();
                        id = (Long.valueOf(PageUtil.getIdValue(lastModel)) + offset);
                    }
                    pageUrl = append(pageUrl, PageUtil.getIdName(lastModel), "" + id);
                }
                writer.print("<a href=\"" + pageUrl + "\">"+ startIndex +"</a>&nbsp;");
            }
            if (onePage.isHasNext()) {
                String nextUrl  = append(url, "pn", onePage.getIndex() + 1);
                if(optimize && lastModel != null) {
                    nextUrl = append(nextUrl, PageUtil.getIdName(lastModel), PageUtil.getIdValue(lastModel));
                }
                if(onePage.getContext().getPageCount() - onePage.getIndex() > 2) {
                    writer.print("<a href=\""+append(url, "pn", onePage.getContext().getPageCount())+"\">"+onePage.getContext().getPageCount()+"</a>&nbsp;");
                }
                writer.print("<a href=\"" + nextUrl + "\">下一页</a>");
            }
            writer.print("&nbsp;(共"+onePage.getContext().getTotal()+"条记录)<br/>");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }
    
    /**
     * 为url 添加上下文环境.如果是登陆用户则还要添加uid参数
     * 
     * @param url
     * @param pageContext
     * @return
     * @throws javax.servlet.jsp.JspException
     */
    private String resolveUrl(String url, javax.servlet.jsp.PageContext pageContext) throws JspException{
        url = UrlSupport.resolveUrl(url, null, pageContext);

        url = url.replaceAll("&pn=\\d*", "").replaceAll("pn=\\d*", "").replaceAll("&pre=true", "").replaceAll("&id=\\d*", "").replaceAll("id=\\d*", "");

        return url;
    }
    
    
    private String append(String url, String key, int value) {

        return append(url, key, String.valueOf(value));
    }
    
    /**
     * 为url 参加参数对儿
     * 
     * @param url
     * @param key
     * @param value
     * @return
     */
    private String append(String url, String key, String value) {
        if (url == null || url.trim().length() == 0) {
            return "";
        }

        if (url.indexOf("?") == -1) {
            url = url + "?" + key + "=" + value;
        } else {
            if(url.endsWith("?")) {
                url = url + key + "=" + value;
            } else {
                url = url + "&amp;" + key + "=" + value;
            }
        }
        
        return url;
    }
    
    

    /**
     * @return the bean
     */
    public String getBean() {
        return bean;
    }

    /**
     * @param bean the bean to set
     */
    public void setBean(String bean) {
        this.bean = bean;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setOptimize(boolean optimize) {
        this.optimize = optimize;
    }
    
}
