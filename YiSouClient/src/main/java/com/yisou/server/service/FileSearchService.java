package com.yisou.server.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.yisou.http.HttpUtils;
import com.yisou.server.model.SearchContent;
import com.yisou.server.model.SearchContentSum;
import com.yisou.server.utils.Searchutil;

public class FileSearchService  implements SearchService{
	
	@Override
	public SearchContentSum searchContent(String searchContent, int page) {
		// TODO Auto-generated method stub
		Map<String, String> params = new HashMap<String, String>();
		params.put("start", String.valueOf(10*(page-1)));
		params.put("searchContent", searchContent);
		params.put("num", "10");
		params.put("cx", Searchutil.FILE_CX);
		
		String resultContent = HttpUtils.post(Searchutil.FILE_URL, params);
		
		if(StringUtils.isBlank(resultContent)){
			return null;
		}
		SearchContent parseSearchContent = Searchutil.parsePanSearch(resultContent);
		if(parseSearchContent==null){
			 return null;
		}
		SearchContentSum searchContentSum = new SearchContentSum();
		searchContentSum.setSearchSum(parseSearchContent.getResultCount());
		searchContentSum.setPageSum(parseSearchContent.getResultCount()/10);
		searchContentSum.setSearchInfos(parseSearchContent.getSearchInfos());
		return searchContentSum;
	}

}
