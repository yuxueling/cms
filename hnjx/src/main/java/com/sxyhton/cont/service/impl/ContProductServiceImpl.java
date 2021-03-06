package com.sxyhton.cont.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

import com.sxyht.common.utils.PageUtils;
import com.sxyht.common.utils.Query;
import com.sxyhton.cont.dao.ContProductDao;
import com.sxyhton.cont.domain.ContProductDO;
import com.sxyhton.cont.service.ContProductService;



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
	public @Override PageUtils leftCategoryList(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ContProductDO> leftCategoryList = this.contProductDao.leftCategoryList(query);
		int leftCategoryListCount = this.contProductDao.leftCategoryListCount(query);
		PageUtils pageUtils = new PageUtils(leftCategoryList, leftCategoryListCount);
		return pageUtils;
	}
	
}
