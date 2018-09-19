package com.cloudht.cont.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.cloudht.cont.dao.ContProductParamDao;
import com.cloudht.cont.domain.ContProductParamDO;
import com.cloudht.cont.service.ContProductParamService;



@Service
public class ContProductParamServiceImpl implements ContProductParamService {
	@Autowired
	private ContProductParamDao contProductParamDao;
	
	@Override
	public ContProductParamDO get(String contProductParamId){
		return contProductParamDao.get(contProductParamId);
	}
	
	@Override
	public List<ContProductParamDO> list(Map<String, Object> map){
		return contProductParamDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return contProductParamDao.count(map);
	}
	
	@Override
	public int save(ContProductParamDO contProductParam){
		return contProductParamDao.save(contProductParam);
	}
	
	@Override
	public int update(ContProductParamDO contProductParam){
		return contProductParamDao.update(contProductParam);
	}
	
	@Override
	public int remove(String contProductParamId){
		return contProductParamDao.remove(contProductParamId);
	}
	
	@Override
	public int batchRemove(String[] contProductParamIds){
		return contProductParamDao.batchRemove(contProductParamIds);
	}
	
}
