package com.avicit.bis.hr.user.model;


/**
 * 用于Ajax提交用
 * User: Zhang Kaitao
 * Date: 11-7-8
 * Time: 下午8:40
 */
public class AjaxInfoModel {

    public static final String STATUS_YES = "y";
    public static final String STATUS_NO = "n";

    private String info = "成功";
    private String status = AjaxInfoModel.STATUS_YES;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
