package com.yisou.server.utils;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yisou.server.model.SearchContent;
import com.yisou.server.model.SearchInfo;

/**
 * 搜索cx标志常量
 * @author yes
 */
public class Searchutil {
	private static Logger logger = LoggerFactory.getLogger(Searchutil.class);
	
	/**盘URl**/
	public static final String PAN_URL="http://localhost:8081/googleSearch/base.do";
	/**盘cx**/
	public static final String PAN_BAIDU_CX="014473859485346487236:xev-hu5qkoc";
	public static final String PAN_YUNBAIDU_CX="014473859485346487236:mnz0rrh-s3e";
	public static final String PAN_360_CX="014473859485346487236:nbct6mkmchg";
	
	/**文档*/
	public static final String FILE_URL="http://localhost:8081/googleSearch/base.do";
	public static final String FILE_CX="014473859485346487236:czpbrfdvbw0";
	
	
	
	/***
	 * 解析盘数据 获得自己想要的数据
	 * @param returnContent
	 * @return
	 */
	public static SearchContent parsePanSearch(String returnContent){
		SearchContent searchContent = new SearchContent();
		try {
			JSONObject contentJson = JSONObject.parseObject(returnContent);
			//获得了搜索内容的json
			 String searchStr= contentJson.getString("search");
			JSONObject search =JSONObject.parseObject(searchStr);
			//解析有用的数据
			//获得数据统计信息
			JSONObject cursor = search.getJSONObject("cursor");
			//估计搜索的数据
			String estimatedResultCount = cursor.getString("estimatedResultCount");
			//备用更多的搜索地址
			String moreResultsUrl = cursor.getString("moreResultsUrl");
			//数据统计
			String resultCount = cursor.getString("resultCount");
			if(resultCount.contains(",")){
				resultCount=resultCount.replace(",", "");
			}
			//搜索时间
			String searchResultTime = cursor.getString("searchResultTime");
			
			searchContent.setEstimatedResultCount(Integer.valueOf(estimatedResultCount));
			searchContent.setMoreResultsUrl(moreResultsUrl);
			searchContent.setResultCount(Integer.valueOf(resultCount));
			searchContent.setSearchResultTime(searchResultTime);
			List<SearchInfo> searchInfos = new LinkedList<SearchInfo>();
			
			JSONArray results =search.getJSONArray("results");
			for(int i=0;i<results.size();i++){
				JSONObject result = results.getJSONObject(i);
				SearchInfo searchInfo = new SearchInfo();
				//访问链接
				String cacheUrl = result.getString("cacheUrl");
				String clicktrackUrl = result.getString("clicktrackUrl");
				String content = result.getString("content");
				String contentNoFormatting = result.getString("contentNoFormatting");
				String formattedUrl =result.getString("formattedUrl");
				String title = result.getString("title");
				String titleNoFormatting =result.getString("titleNoFormatting");
				String unescapedUrl =result.getString("unescapedUrl");
				String url=result.getString("url");
				String visibleUrl =result.getString("visibleUrl");
				searchInfo.setCacheUrl(cacheUrl);
				searchInfo.setTitle(title);
				searchInfo.setClicktrackUrl(clicktrackUrl);
				searchInfo.setContent(contentNoFormatting);
				searchInfo.setContent(content);
				searchInfo.setFormattedUrl(formattedUrl);
				searchInfo.setTitle(titleNoFormatting);
				searchInfo.setTitleNoFormatting(titleNoFormatting);
				searchInfo.setUnescapedUrl(unescapedUrl);
				
				if(url.length()>150){
					url=url.substring(0, 150)+"...";
				}
				searchInfo.setUrl(url);
				searchInfo.setVisibleUrl(visibleUrl);
				
				//如果有图片获得图片
				JSONObject richSnippet = result.getJSONObject("richSnippet");
				if(richSnippet!=null&&richSnippet.containsKey("cseImage")){
					String imgUrl = richSnippet.getJSONObject("cseImage").getString("src");
					searchInfo.setImgUrl(imgUrl);
				}
				
				searchInfos.add(searchInfo);
			}
			searchContent.setSearchInfos(searchInfos);
			return searchContent;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("解析盘数据出现异常:{},异常数据为:{}",e.toString(),returnContent);
			return null;
		}
		
	}
}
