package com.cloudht.cont.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.cloudht.cont.dao.ContProductPkDao;
import com.cloudht.cont.domain.ContProductPkDO;
import com.cloudht.cont.service.ContProductPkService;



@Service
public class ContProductPkServiceImpl implements ContProductPkService {
	@Autowired
	private ContProductPkDao contProductPkDao;
	
	@Override
	public ContProductPkDO get(Integer contProductPkId){
		return contProductPkDao.get(contProductPkId);
	}
	
	@Override
	public List<ContProductPkDO> list(Map<String, Object> map){
		return contProductPkDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return contProductPkDao.count(map);
	}
	
	@Override
	public int save(ContProductPkDO contProductPk){
		return contProductPkDao.save(contProductPk);
	}
	
	@Override
	public int update(ContProductPkDO contProductPk){
		return contProductPkDao.update(contProductPk);
	}
	
	@Override
	public int remove(Integer contProductPkId){
		return contProductPkDao.remove(contProductPkId);
	}
	
	@Override
	public int batchRemove(Integer[] contProductPkIds){
		return contProductPkDao.batchRemove(contProductPkIds);
	}

	@Override
	public int batchInsert(List<ContProductPkDO> contProductPkList) {
		return contProductPkDao.batchInsert(contProductPkList);
	}

	@Override
	public int delByProductParamId(Integer contProductParamId) {
		return contProductPkDao.delByProductParamId(contProductParamId);
	}

}
