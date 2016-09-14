package com.yisou.server.model;

import java.util.List;

/***
 * 所有的搜索内容集合
 * @author yes
 */
public class SearchContentSum {
	
	/**总共的搜索数**/
	private int searchSum;
	
	/**多少页**/
	private int pageSum;
	
	/**搜索的详细内容**/
	private List<SearchInfo> searchInfos;

	public int getSearchSum() {
		return searchSum;
	}

	public void setSearchSum(int searchSum) {
		this.searchSum = searchSum;
	}

	public int getPageSum() {
		return pageSum;
	}

	public void setPageSum(int pageSum) {
		this.pageSum = pageSum;
	}

	public List<SearchInfo> getSearchInfos() {
		return searchInfos;
	}

	public void setSearchInfos(List<SearchInfo> searchInfos) {
		this.searchInfos = searchInfos;
	}
	
	
}
