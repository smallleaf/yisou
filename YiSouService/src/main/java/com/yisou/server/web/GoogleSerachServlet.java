package com.yisou.server.web;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yisou.response.ResponseUtil;
import com.yisou.server.service.GoogleSearchService;

/**
 * google接口请求
 * @author yes
 *
 */
@Controller
public class GoogleSerachServlet {
	
	@Autowired
	private GoogleSearchService googleSearchService;
	
	@RequestMapping("googleSearch/base")
	@ResponseBody
	public Object googleSearchResult(String searchContent,String start,String cx,Integer num){
		
		if(StringUtils.isBlank(searchContent)||StringUtils.isBlank(start)||StringUtils.isBlank(cx)||num==null){
			return ResponseUtil.getFailResult("缺少参数:searchContent,start,cx,num");
		}
		return googleSearchService.baseSearchResult(searchContent,start,cx,num);
	}
	
}
