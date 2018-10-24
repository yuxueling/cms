package com.sxyhton.cont.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.sxyhton.cont.dao.ContProductImgDao;
import com.sxyhton.cont.domain.ContProductImgDO;
import com.sxyhton.cont.service.ContProductImgService;



@Service
public class ContProductImgServiceImpl implements ContProductImgService {
	@Autowired
	private ContProductImgDao contProductImgDao;
	
	@Override
	public ContProductImgDO get(Integer contProductImgId){
		return contProductImgDao.get(contProductImgId);
	}
	
	@Override
	public List<ContProductImgDO> list(Map<String, Object> map){
		return contProductImgDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return contProductImgDao.count(map);
	}
	
	@Override
	public int save(ContProductImgDO contProductImg){
		return contProductImgDao.save(contProductImg);
	}
	
	@Override
	public int update(ContProductImgDO contProductImg){
		return contProductImgDao.update(contProductImg);
	}
	
	@Override
	public int remove(Integer contProductImgId){
		return contProductImgDao.remove(contProductImgId);
	}
	
	@Override
	public int batchRemove(Integer[] contProductImgIds){
		return contProductImgDao.batchRemove(contProductImgIds);
	}
	
}
