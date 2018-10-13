package com.cloudht.cont.service.impl;

import com.cloudht.cont.dao.ContCategoryDao;
import com.cloudht.cont.dao.ContProductDao;
import com.cloudht.cont.dao.ContXmxDao;
import com.cloudht.cont.domain.ContCategoryDO;
import com.cloudht.cont.domain.ContProductDO;
import com.cloudht.cont.service.ContXmxService;
import com.cloudht.cont.vo.ContProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class ContXmxServiceImpl implements ContXmxService {
	@Autowired
	private ContXmxDao contXmxDao;
	@Autowired
	private ContCategoryDao contCategoryDao;
	@Autowired
	private ContProductDao contProductDao;

	@Override
	public List<ContProductVO> listProductByCategory(Map<String,Object> map) {
		return contXmxDao.listProductByCategory(map);
	}

	@Override
	public int countProductByCategory(Map<String, Object> map) {
		return contXmxDao.countProductByCategory(map);
	}

	@Override
	public ContProductVO getProduct(Map<String, Object> map) {
		return contXmxDao.getProduct(map);
	}

	@Override
	public List<ContProductVO> listCateProdsByProdId(Map<String, Object> map) {
		return contXmxDao.listCateProdsByProdId(map);
	}

	@Override
	public List<ContProductVO> listProductByCategoryCode(Map<String, Object> map) {
		return contXmxDao.listProductByCategoryCode(map);
	}

	@Override
	public List<ContCategoryDO> listProductDetailNavBar(Map<String, Object> map) {

		List<ContCategoryDO> resultList=new ArrayList<>();

		int productId = Integer.parseInt(map.get("contProductId").toString());
		ContProductDO contProductDO = contProductDao.get(productId);

		//获取产品所属分类
		Map<String, Object> infoMap=new HashMap<>();
		infoMap.put("langType",map.get("langType"));
		infoMap.put("contCategoryId",contProductDO.getCategoryId());
		ContCategoryDO contCategoryDO = contCategoryDao.getContainInfo(infoMap);
		resultList.add(contCategoryDO);
		if(contCategoryDO.getParentCategoryId()!=0){
			//获取分类的上级分类
			infoMap.put("contCategoryId",contCategoryDO.getParentCategoryId());
			resultList.add(contCategoryDao.getContainInfo(infoMap));
		}
		return resultList;
	}

}
