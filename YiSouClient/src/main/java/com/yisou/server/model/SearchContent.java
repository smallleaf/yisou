package com.yisou.server.model;

import java.util.List;

/***
 * 搜索内容实体类
 * @author yes
 */
public class SearchContent {
	
	/**估计搜索的数目**/
	private Integer  estimatedResultCount;
	/**备用更多的搜索地址**/
	private String moreResultsUrl;
	/**数据统计**/
	private Integer resultCount;
	/**google搜索的时间**/
	private String searchResultTime;
	/**搜索的详细内容**/
	private List<SearchInfo> searchInfos;
	public String getMoreResultsUrl() {
		return moreResultsUrl;
	}
	public void setMoreResultsUrl(String moreResultsUrl) {
		this.moreResultsUrl = moreResultsUrl;
	}
	public String getSearchResultTime() {
		return searchResultTime;
	}
	public void setSearchResultTime(String searchResultTime) {
		this.searchResultTime = searchResultTime;
	}
	public List<SearchInfo> getSearchInfos() {
		return searchInfos;
	}
	public void setSearchInfos(List<SearchInfo> searchInfos) {
		this.searchInfos = searchInfos;
	}
	public Integer getEstimatedResultCount() {
		return estimatedResultCount;
	}
	public void setEstimatedResultCount(Integer estimatedResultCount) {
		this.estimatedResultCount = estimatedResultCount;
	}
	public Integer getResultCount() {
		return resultCount;
	}
	public void setResultCount(Integer resultCount) {
		this.resultCount = resultCount;
	}
	
	
}
