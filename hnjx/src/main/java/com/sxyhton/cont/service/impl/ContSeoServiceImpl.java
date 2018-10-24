package com.sxyhton.cont.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.sxyhton.cont.dao.ContSeoDao;
import com.sxyhton.cont.domain.ContSeoDO;
import com.sxyhton.cont.service.ContSeoService;



@Service
public class ContSeoServiceImpl implements ContSeoService {
	@Autowired
	private ContSeoDao contSeoDao;
	
	@Override
	public ContSeoDO get(Integer contSeoId){
		return contSeoDao.get(contSeoId);
	}
	
	@Override
	public List<ContSeoDO> list(Map<String, Object> map){
		return contSeoDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return contSeoDao.count(map);
	}
	
	@Override
	public int save(ContSeoDO contSeo){
		return contSeoDao.save(contSeo);
	}
	
	@Override
	public int update(ContSeoDO contSeo){
		return contSeoDao.update(contSeo);
	}
	
	@Override
	public int remove(Integer contSeoId){
		return contSeoDao.remove(contSeoId);
	}
	
	@Override
	public int batchRemove(Integer[] contSeoIds){
		return contSeoDao.batchRemove(contSeoIds);
	}
	
}
