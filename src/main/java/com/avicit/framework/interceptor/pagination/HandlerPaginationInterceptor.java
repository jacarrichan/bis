package com.avicit.framework.interceptor.pagination;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.avicit.framework.interceptor.AbstractHandlerPreparInterceptor;
import com.avicit.framework.web.support.pagination.Pagination;
import com.avicit.framework.web.support.pagination.PaginationUtils;

public class HandlerPaginationInterceptor extends
		AbstractHandlerPreparInterceptor {

	public static final String PAGINATION_BEAN_ATTRIBUTE = "pagination_bean";

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) {
		String startParam = request.getParameter("start");
		String limitParam = request.getParameter("limit");

		if (startParam != null) {
			int start = 0;
			int limit = 0;
			boolean isPagination = true;
			try {
				start = Integer.valueOf(startParam);
				limit = Integer.valueOf(limitParam);
			} catch (Exception e) {
				isPagination = false;
			}
			if (isPagination) {
				Pagination p = null;
				try {
					JSONObject sort = JSONArray.fromObject(request
							.getParameter("sort")).getJSONObject(0);
					if (sort != null) {
						p = new Pagination(start, limit,
								sort.getString("property"),
								sort.getString("direction"));
					} else {
						p = new Pagination(start, limit);
					}
				} catch (Exception e) {
					p = new Pagination(start, limit);
				}
				PaginationUtils.setPagination(p);
			}
		}
		return true;
	}
}
