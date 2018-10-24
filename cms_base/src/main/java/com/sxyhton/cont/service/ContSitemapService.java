package com.sxyhton.cont.service;

import java.util.List;
import java.util.Map;

import com.sxyhton.cont.domain.ContSitemapDO;

/**
 * 
 * 
 * @author yuxueling
 * @email 980899486@qq.com
 * @date 2018-10-12 15:59:14
 */
public interface ContSitemapService {
	
	ContSitemapDO get(Integer contSitemapId);
	
	List<ContSitemapDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ContSitemapDO contSitemap);
	
	int update(ContSitemapDO contSitemap);
	
	int remove(Integer contSitemapId);
	
	int batchRemove(Integer[] contSitemapIds);
}
