package com.cloudht.cont.dao;

import com.cloudht.cont.domain.ContSitemapDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author yuxueling
 * @email 980899486@qq.com
 * @date 2018-10-12 15:59:14
 */
@Mapper
public interface ContSitemapDao {

	ContSitemapDO get(Integer contSitemapId);
	
	List<ContSitemapDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ContSitemapDO contSitemap);
	
	int update(ContSitemapDO contSitemap);
	
	int remove(Integer cont_sitemap_id);
	
	int batchRemove(Integer[] contSitemapIds);
}
