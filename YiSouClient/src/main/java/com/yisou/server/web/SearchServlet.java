package com.yisou.server.web;


import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yisou.server.service.SearchRecordService;
import com.yisou.server.service.SearchServiceRouter;

/**
 * 搜索接口
 * @author yes
 */
@Controller
public class SearchServlet {
	
	@Autowired
	public SearchServiceRouter searchServiceRouter;
	@Autowired
	public SearchRecordService searchRecordService;
	/**
	 * 百度盘搜索
	 * @param searchContent
	 * @param page
	 * @return
	 */
	@RequestMapping("search")
	public ModelAndView search(String searchContent,Integer page,String searchType,HttpServletRequest request){
		//保存搜索记录
		searchRecordService.saveSearchRecord(searchContent, searchType, request.getRemoteHost());
		if(StringUtils.isBlank(searchContent)||page==null||StringUtils.isBlank(searchType)){
			return null;
		}
		return searchServiceRouter.searchContent(searchContent, page,searchType);
	}
}
