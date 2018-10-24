package com.sxyhton.cont.service.impl;

import com.sxyhton.cont.dao.ContProductParamDao;
import com.sxyhton.cont.dao.ContProductPkDao;
import com.sxyhton.cont.domain.ContProductParamDO;
import com.sxyhton.cont.service.ContProductParamService;
import com.sxyhton.cont.vo.ContProductParamVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



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
