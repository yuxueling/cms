package com.sxyhton.cont.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.sxyhton.cont.dao.ContProductInfoDao;
import com.sxyhton.cont.domain.ContProductInfoDO;
import com.sxyhton.cont.service.ContProductInfoService;



@Service
public class ContProductInfoServiceImpl implements ContProductInfoService {
	@Autowired
	private ContProductInfoDao contProductInfoDao;
	
	@Override
	public ContProductInfoDO get(Integer contProductInfoId){
		return contProductInfoDao.get(contProductInfoId);
	}
	
	@Override
	public List<ContProductInfoDO> list(Map<String, Object> map){
		return contProductInfoDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return contProductInfoDao.count(map);
	}
	
	@Override
	public int save(ContProductInfoDO contProductInfo){
		return contProductInfoDao.save(contProductInfo);
	}
	
	@Override
	public int update(ContProductInfoDO contProductInfo){
		return contProductInfoDao.update(contProductInfo);
	}
	
	@Override
	public int remove(Integer contProductInfoId){
		return contProductInfoDao.remove(contProductInfoId);
	}
	
	@Override
	public int batchRemove(Integer[] contProductInfoIds){
		return contProductInfoDao.batchRemove(contProductInfoIds);
	}

	@Override
	public List<ContProductInfoDO> listByDict(Map<String, Object> map) {
		return contProductInfoDao.listByDict(map);
	}

}
