package com.avicit.framework.util;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.avicit.framework.web.support.pagination.PaginationUtils;

public class JsonUtil {

	public static String build(String flag, String text) {
		return "{'" + flag + "' : true , '" + ResponseUtils.RESPONSE_TEXT_KEY
				+ "' : '" + text + "'}";
	}

	public static String buildFailure(String text) {
		return build(ResponseUtils.RESPONSE_FAILURE_KEY, text);
	}

	public static String buildSuccess(String text) {
		return build(ResponseUtils.RESPONSE_SUCCESS_KEY, text);
	}

	public static String buildPagination(List<?> list) {
		JSONObject json = new JSONObject();
		json.element(ResponseUtils.RESPONSE_SUCCESS_KEY, true);
		json.element(ResponseUtils.PAGINATION_TOTAL_PROPERTY_KEY,
				PaginationUtils.getPagination().getTotal());
		json.element(ResponseUtils.PAGINATION_ROOT_PROPERTY_KEY,
				JSONArray.fromObject(list));
		return json.toString();
	}

	public static String buildArrayList(List<?> list) {
		return JSONArray.fromObject(list).toString();
	}
}
