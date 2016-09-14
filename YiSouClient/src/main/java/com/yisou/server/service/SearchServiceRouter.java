package com.yisou.server.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yisou.server.model.SearchContent;
import com.yisou.server.model.SearchContentSum;

/***
 * 不同的搜索可能有不同的实现方法 这里用作业务分流使用
 * @author yes
 */
public class SearchServiceRouter {
	private Logger logger = LoggerFactory.getLogger(SearchServiceRouter.class);
	private Map<String, SearchService> searchServiceMap;
	
	public SearchContentSum searchContent(String searchContent,int page,String searchType){
		if(searchServiceMap.containsKey(searchType)){
			return searchServiceMap.get(searchType).searchContent(searchContent, page);
		}
		logger.info("未找到搜索类型:{}",searchType);
		return null;
	}

	public Map<String, SearchService> getSearchServiceMap() {
		return searchServiceMap;
	}

	public void setSearchServiceMap(Map<String, SearchService> searchServiceMap) {
		this.searchServiceMap = searchServiceMap;
	}
	
	
}
