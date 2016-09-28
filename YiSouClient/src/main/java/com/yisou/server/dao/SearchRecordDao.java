package com.yisou.server.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/***
 * 搜索记录插入数据库
 * @author yes
 */
@Repository
public class SearchRecordDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/***
	 * 保存搜索记录
	 * @param searchContent
	 * @param searchType
	 * @param searchIp
	 * @return
	 */
	public int saveSearchRecord(String searchContent,String searchType,String searchIp){
		String sql ="INSERT INTO search_record(search_content,search_ip,search_type,search_time) values(?,?,?,sysdate())";
		return jdbcTemplate.update(sql, searchContent,searchIp,searchType);
	}
}
