package com.yisou.http;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * http封装
 * @author yes
 *
 */
public class HttpUtils {
	
	private static Logger logger = LoggerFactory.getLogger(HttpUtils.class);

	
	/**
	 * 
	 * @param url
	 * @param method
	 * @param properties
	 * @param params
	 * @param headers
	 * @param encoding
	 * @param proxyHost
	 * @return
	 * @param kind 代表对应的实例化方法
	 */
	private static String request(String url,String method,Map<String, String> properties,Map<String, String> params,Map<String, String> headers,String encoding,String proxyHost,Integer proxyPort,int kind,Integer socketTimeout, Integer connectTimeout){
		HttpClientUtil httpClientUtil ;
		
		
		if(kind==1){//有代理实例化
			httpClientUtil = new HttpClientUtil(proxyHost,proxyPort);
		}else if(kind==2){//超时实例化
			httpClientUtil = new HttpClientUtil(socketTimeout, connectTimeout);
		}else{//默认
			httpClientUtil = new HttpClientUtil();
		}
		Request request = new Request();
		request.setHeaders(headers);
		request.setParams(params);
		request.setProperties(properties);
		request.setProperty("url", url);
		request.setProperty("method", method);
		
		Response response = null;
		
		try {
			response = httpClientUtil.sendRequest(request);
			if(response==null||response.getStatusCode()!=200){
				return null;
			}
			String content = encoding==null?response.getContentString():response.getContentString(encoding);
			return content;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info("请求异常：{}",e.toString());
		}
		return null;
	}
	
	public static String get(String url){
		return request(url, "get", null, null, null, null,null,null,0,null,null);
	}
	/***
	 * 
	 * @param url
	 * @param socketTimeout
	 * @param connectTimeout
	 * @return
	 */
	public static String get(String url,int socketTimeout, int connectTimeout){
		return  request(url, "get", null, null, null, null,null,null,2,socketTimeout,connectTimeout);
	}
	
	public static String getByProxy(String url,String proxyHost,Integer proxyPort){
		return request(url, "get", null, null, null, null,proxyHost,proxyPort,1,null,null);
	}
	
	public static String post(String url,Map<String, String> params){
		return request(url, "post", null, params, null, null,null,null,0,null,null);
	}
	
}
