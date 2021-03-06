package com.sxyhton.cont.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.sxyhton.cont.dao.ContFormDataDao;
import com.sxyhton.cont.domain.ContFormDataDO;
import com.sxyhton.cont.service.ContFormDataService;



@Service
public class ContFormDataServiceImpl implements ContFormDataService {
	@Autowired
	private ContFormDataDao contFormDataDao;
	
	@Override
	public ContFormDataDO get(Integer contFormDataId){
		return contFormDataDao.get(contFormDataId);
	}
	
	@Override
	public List<ContFormDataDO> list(Map<String, Object> map){
		return contFormDataDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return contFormDataDao.count(map);
	}
	
	@Override
	public int save(ContFormDataDO contFormData){
		return contFormDataDao.save(contFormData);
	}
	
	@Override
	public int update(ContFormDataDO contFormData){
		return contFormDataDao.update(contFormData);
	}
	
	@Override
	public int remove(Integer contFormDataId){
		return contFormDataDao.remove(contFormDataId);
	}
	
	@Override
	public int batchRemove(Integer[] contFormDataIds){
		return contFormDataDao.batchRemove(contFormDataIds);
	}

	@Override
	public int batchSave(List<ContFormDataDO> contFormDataList) {
		return contFormDataDao.batchSave(contFormDataList);
	}

}
