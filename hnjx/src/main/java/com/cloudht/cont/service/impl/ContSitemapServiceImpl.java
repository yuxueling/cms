package com.cloudht.cont.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.cloudht.cont.dao.ContSitemapDao;
import com.cloudht.cont.domain.ContSitemapDO;
import com.cloudht.cont.service.ContSitemapService;



@Service
public class ContSitemapServiceImpl implements ContSitemapService {
	@Autowired
	private ContSitemapDao contSitemapDao;
	
	@Override
	public ContSitemapDO get(Integer contSitemapId){
		return contSitemapDao.get(contSitemapId);
	}
	
	@Override
	public List<ContSitemapDO> list(Map<String, Object> map){
		return contSitemapDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return contSitemapDao.count(map);
	}
	
	@Override
	public int save(ContSitemapDO contSitemap){
		return contSitemapDao.save(contSitemap);
	}
	
	@Override
	public int update(ContSitemapDO contSitemap){
		return contSitemapDao.update(contSitemap);
	}
	
	@Override
	public int remove(Integer contSitemapId){
		return contSitemapDao.remove(contSitemapId);
	}
	
	@Override
	public int batchRemove(Integer[] contSitemapIds){
		return contSitemapDao.batchRemove(contSitemapIds);
	}
	
}
