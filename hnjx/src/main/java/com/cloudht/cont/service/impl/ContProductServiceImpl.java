package com.cloudht.cont.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.cloudht.cont.dao.ContProductDao;
import com.cloudht.cont.domain.ContProductDO;
import com.cloudht.cont.service.ContProductService;



@Service
public class ContProductServiceImpl implements ContProductService {
	@Autowired
	private ContProductDao contProductDao;
	
	@Override
	public ContProductDO get(Integer contProductId){
		return contProductDao.get(contProductId);
	}
	
	@Override
	public List<ContProductDO> list(Map<String, Object> map){
		return contProductDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return contProductDao.count(map);
	}
	
	@Override
	public int save(ContProductDO contProduct){
		return contProductDao.save(contProduct);
	}
	
	@Override
	public int update(ContProductDO contProduct){
		return contProductDao.update(contProduct);
	}
	
	@Override
	public int remove(Integer contProductId){
		return contProductDao.remove(contProductId);
	}
	
	@Override
	public int batchRemove(Integer[] contProductIds){
		return contProductDao.batchRemove(contProductIds);
	}
	
}
