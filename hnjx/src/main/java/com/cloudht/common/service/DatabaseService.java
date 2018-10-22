/**
 * 
 */
package com.cloudht.common.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * @author 980899486@qq.com
 * @Time 2017年9月6日
 * @description
 * 
 */
@Service
public interface DatabaseService {
	List<Map<String, Object>> list();

	byte[] generatorCode(String[] tableNames);
	/**
	 * 根据url地址
	 * @param tableUrl
	 * @return
	 * @throws Exception 
	 */
	Boolean tableToSqlByTableUrl(String tableUrl) throws Exception;
}
