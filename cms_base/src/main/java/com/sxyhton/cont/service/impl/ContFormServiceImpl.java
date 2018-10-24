package com.sxyhton.cont.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.sxyhton.cont.dao.ContFormDao;
import com.sxyhton.cont.domain.ContFormDO;
import com.sxyhton.cont.service.ContFormService;



@Service
public class ContFormServiceImpl implements ContFormService {
	@Autowired
	private ContFormDao contFormDao;
	
	@Override
	public ContFormDO get(Integer contFormId){
		return contFormDao.get(contFormId);
	}
	
	@Override
	public List<ContFormDO> list(Map<String, Object> map){
		return contFormDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return contFormDao.count(map);
	}
	
	@Override
	public int save(ContFormDO contForm){
		return contFormDao.save(contForm);
	}
	
	@Override
	public int update(ContFormDO contForm){
		return contFormDao.update(contForm);
	}
	
	@Override
	public int remove(Integer contFormId){
		return contFormDao.remove(contFormId);
	}
	
	@Override
	public int batchRemove(Integer[] contFormIds){
		return contFormDao.batchRemove(contFormIds);
	}



}
