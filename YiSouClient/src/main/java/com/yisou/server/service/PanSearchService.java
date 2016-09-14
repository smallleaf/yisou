package com.yisou.server.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;

import org.apache.commons.lang3.StringUtils;
import org.junit.internal.runners.model.EachTestNotifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yisou.http.HttpUtils;
import com.yisou.response.ResponseUtil;
import com.yisou.server.model.SearchContent;
import com.yisou.server.model.SearchContentSum;
import com.yisou.server.model.SearchInfo;
import com.yisou.server.utils.Searchutil;

/***
 * 百度盘搜索
 * 
 * @author yes
 */
public class PanSearchService implements SearchService {
	private Logger logger = LoggerFactory.getLogger(PanSearchService.class);


	/****
	 * 要搜寻的list
	 */
	private Map<String, Map<String, String>> searchPanMap;

	/**
	 * 百度盘搜索
	 * 
	 * @param searchContent
	 * @param page
	 * @return
	 */
	public SearchContentSum searchContent(String searchContent, int page) {
		
		//所有的数目
		Map<String, SearchContent> searchContentMap = new HashMap<String, SearchContent>();
		Map<String, String> params = new HashMap<String, String>();
		params.put("start", String.valueOf(10 * (page - 1)));
		params.put("searchContent", searchContent);
		params.put("num", "5");
		for (Map.Entry<String, Map<String, String>> map : searchPanMap
				.entrySet()) {
			params.put("cx", map.getValue().get("cx"));
			params.put("url", map.getValue().get("cx"));
			String resultContent = HttpUtils.post(Searchutil.PAN_URL, params);
			if (resultContent != null) {
				searchContentMap.put(map.getKey(),
						Searchutil.parsePanSearch(resultContent));
			} else {
				searchContentMap.put(map.getKey(), null);
			}
		}
		int pageSum=0;
		int searchSum=0;
		List<SearchInfo> searchInfos = new LinkedList<SearchInfo>();
		for(Map.Entry<String, SearchContent> entry:searchContentMap.entrySet()){
			if(entry.getValue()!=null){
				searchSum+=entry.getValue().getResultCount();
				searchInfos.addAll(entry.getValue().getSearchInfos());
			}
		}
		pageSum=searchSum/10;
		SearchContentSum searchContentSum = new SearchContentSum();
		searchContentSum.setPageSum(pageSum);
		searchContentSum.setSearchSum(searchSum);
		searchContentSum.setSearchInfos(searchInfos);
		
		return searchContentSum;
	}

	public Map<String, Map<String, String>> getSearchPanMap() {
		return searchPanMap;
	}

	public void setSearchPanMap(Map<String, Map<String, String>> searchPanMap) {
		this.searchPanMap = searchPanMap;
	}
	
	
	
}
