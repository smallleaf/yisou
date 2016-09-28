package com.yisou.server.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.yisou.http.HttpUtils;

/***
 * 通过数据库抓取数据来
 * @author yes
 */
public class JarSearchByDBService {
	
	private Logger logger = LoggerFactory.getLogger(JarSearchByDBService.class);
	/***
	 * 通过数据库抓取得到的数据
	 * @param searchContent
	 * @return
	 */
	public ModelAndView jarSearch(String searchContent){
		ModelAndView mav =new ModelAndView();
		String url ="http://localhost:8083/jar.do";
		Map<String, String> params = new HashMap<String, String>();
		params.put("searchContent", searchContent);
		String returnContent =HttpUtils.post(url, params);
		logger.info("从数据库抓取Jar获得数据库为:{}",returnContent);
		JSONObject jarSearch =JSONObject.parseObject(returnContent);

		if(jarSearch.getString("code").equals("0")){
			mav.addObject("message", "未找到名为"+searchContent+"的jar包。");
			mav.addObject("isFind", 0);
			mav.addObject("returnSearch",null);
		}else{
			Map<String, String> returnSearch = (Map<String, String>) jarSearch.get("jarSearch");
			StringBuffer msgBuffer = new StringBuffer();
			msgBuffer.append("找到了");
			msgBuffer.append(returnSearch.size());
			msgBuffer.append("关于");
			msgBuffer.append(searchContent);
			msgBuffer.append("的jar包。");
			mav.addObject("message",msgBuffer.toString());
			mav.addObject("tableRow",Math.ceil(returnSearch.size()/3.0));
			mav.addObject("size",returnSearch.size());
			mav.addObject("isFind", 1);
			mav.addObject("returnSearch",returnSearch );
		}
		mav.addObject("search",searchContent);
		mav.setViewName("searchJar");;
		return mav;
	}
}
