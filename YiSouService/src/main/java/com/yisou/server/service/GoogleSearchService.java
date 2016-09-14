package com.yisou.server.service;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yisou.response.ResponseUtil;
import com.yisou.server.search.GoogleSearch;

/**
 * google搜索引擎服务接口
 * @author yes
 *
 */
@Service
public class GoogleSearchService {
	private Logger logger = LoggerFactory.getLogger(GoogleSearchService.class);
	
	/**
	 * 基本的查询
	 * @param searchContent
	 * @param start
	 * @param cx
	 * @return
	 */
	public Object baseSearchResult(String searchContent,String start,String cx,int num){
		
		String returnContent =GoogleSearch.baseSearch(searchContent,start,cx,num);
		if(StringUtils.isBlank(returnContent)){
			return ResponseUtil.getFailResult("请求接口返回数据异常");
		}
		return  ResponseUtil.getSuccessResult("search",returnContent);
	}
	
	
	
}
