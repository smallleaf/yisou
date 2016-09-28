package com.yisou.server.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yisou.server.dao.SearchRecordDao;

@Service
public class SearchRecordService {
	
	private Logger logger = LoggerFactory.getLogger(SearchRecordService.class);
	@Autowired
	private SearchRecordDao searchRecordDao;
	
	public void saveSearchRecord(String searchContent,String searchType,String searchIp){
		int result = searchRecordDao.saveSearchRecord(searchContent, searchType, searchIp);
		if(result<=0){
			logger.info("插入数据不成功，搜索内容:{},搜索类型{},搜索Ip:{}",searchContent,searchType,searchIp);
		}
	}
}
