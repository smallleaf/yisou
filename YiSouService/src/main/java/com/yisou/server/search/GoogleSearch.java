package com.yisou.server.search;

import org.apache.commons.lang3.StringUtils;

import com.yisou.http.HttpUtils;
import com.yisou.response.ResponseUtil;

/**
 * 自定搜索类，算法
 * 
 * @author yes
 *
 */
public class GoogleSearch {

	/**
	 * 最基本的搜索返回json数据
	 * 
	 * @param searchContent
	 *            搜索内容
	 * @param start
	 *            从哪个页面开始
	 * @param cx 是google搜索中代表那个搜索的标志
	 * @return
	 */
	public static String baseSearch(String searchContent, String start,String cx,int num) {
		StringBuilder searchUrl = new StringBuilder();
		searchUrl
				.append("https://www.googleapis.com/customsearch/v1element?key=AIzaSyCVAXiUzRYsML1Pv6RwSG1gunmMikTzQqY");
		searchUrl
				.append("&rsz=filtered_cse&hl=en&prettyPrint=false&source=gcsc&gss=.com&sig=8bdfc79787aa2b2b1ac464140255872c&sort=&googlehost=www.google.com");
		searchUrl.append("&start=" + start);
		searchUrl.append("&q=" + searchContent);
		searchUrl.append("&cx=" + cx);
		searchUrl.append("&num=" + num);

		String returnContent = HttpUtils.getByProxy(searchUrl.toString(),
				"localhost", 8087);

		if (StringUtils.isBlank(returnContent)) {
			return null;
		}
		return returnContent;
	}
}
