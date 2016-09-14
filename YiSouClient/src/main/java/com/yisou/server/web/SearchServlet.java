package com.yisou.server.web;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yisou.server.model.SearchContentSum;
import com.yisou.server.service.SearchServiceRouter;

/**
 * 搜索接口
 * @author yes
 */
@Controller
public class SearchServlet {
	
	@Autowired
	public SearchServiceRouter searchServiceRouter;
	/**
	 * 百度盘搜索
	 * @param searchContent
	 * @param page
	 * @return
	 */
	@RequestMapping("search")
	public String search(String searchContent,Integer page,Model search,String searchType){
		if(StringUtils.isBlank(searchContent)||page==null||StringUtils.isBlank(searchType)){
			return null;
		}
		SearchContentSum searchContentSum = searchServiceRouter.searchContent(searchContent, page,searchType);
		if(searchContentSum==null){
			return null;
		}
		search.addAttribute("searchContent", searchContentSum);
		search.addAttribute("search", searchContent);
		search.addAttribute("searchType", searchType);
		search.addAttribute("page", page);
		return "index";
	}
	
}
