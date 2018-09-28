package com.cloudht.cont.service.impl;

import com.cloudht.cont.dao.ContProductPkDao;
import com.cloudht.cont.vo.ContProductParamVO;
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
	@Autowired
	private ContProductPkDao contProductPkDao;
	
	@Override
	public ContProductParamDO get(Integer contProductParamId){
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
	public int remove(Integer contProductParamId){
		return contProductParamDao.remove(contProductParamId);
	}
	
	@Override
	public int batchRemove(Integer[] contProductParamIds){
		return contProductParamDao.batchRemove(contProductParamIds);
	}

	@Override
	public List<ContProductParamVO> listByDict(Integer contProducId) {
		return contProductParamDao.listByDict(contProducId);
	}

	@Override
	public int delByProductId(Integer contProductId) {
		contProductPkDao.delByProductId(contProductId);
		return contProductParamDao.delByProductId(contProductId);
	}

}
