package com.yisou.server.service;

import com.yisou.server.model.SearchContent;
import com.yisou.server.model.SearchContentSum;

/***
 * 搜索接口
 * @author yes
 */
public interface SearchService {
	
	public SearchContentSum searchContent(String searchContent,int page);
}
