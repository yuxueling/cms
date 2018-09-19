package com.cloudht.cont.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.cloudht.cont.dao.ContCategoryDao;
import com.cloudht.cont.domain.ContCategoryDO;
import com.cloudht.cont.service.ContCategoryService;



@Service
public class ContCategoryServiceImpl implements ContCategoryService {
	@Autowired
	private ContCategoryDao contCategoryDao;
	
	@Override
	public ContCategoryDO get(Integer contCategoryId){
		return contCategoryDao.get(contCategoryId);
	}
	
	@Override
	public List<ContCategoryDO> list(Map<String, Object> map){
		return contCategoryDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return contCategoryDao.count(map);
	}
	
	@Override
	public int save(ContCategoryDO contCategory){
		return contCategoryDao.save(contCategory);
	}
	
	@Override
	public int update(ContCategoryDO contCategory){
		return contCategoryDao.update(contCategory);
	}
	
	@Override
	public int remove(Integer contCategoryId){
		return contCategoryDao.remove(contCategoryId);
	}
	
	@Override
	public int batchRemove(Integer[] contCategoryIds){
		return contCategoryDao.batchRemove(contCategoryIds);
	}
	
}
