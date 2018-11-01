package com.sxyhton.cont.service.impl;

import com.alibaba.fastjson.JSON;
import com.sxyht.common.utils.MailUtils;
import com.sxyht.common.utils.R;
import com.sxyhton.common.domain.DictDO;
import com.sxyhton.common.domain.Tree;
import com.sxyhton.common.service.DictService;
import com.sxyhton.cont.dao.ContCategoryDao;
import com.sxyhton.cont.dao.ContCategoryInfoDao;
import com.sxyhton.cont.dao.ContProductDao;
import com.sxyhton.cont.dao.ContXmxDao;
import com.sxyhton.cont.domain.*;
import com.sxyhton.cont.service.ContCategoryService;
import com.sxyhton.cont.service.ContFormDataService;
import com.sxyhton.cont.service.ContFormService;
import com.sxyhton.cont.service.ContXmxService;
import com.sxyhton.cont.vo.ContProductVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class ContXmxServiceImpl implements ContXmxService {
	@Autowired
	private ContXmxDao contXmxDao;
	@Autowired
	private ContCategoryDao contCategoryDao;
	@Autowired
	private ContCategoryService contCategoryService;
	@Autowired
	private ContCategoryInfoDao contCategoryInfoDao;
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

	@Override
	public List<ContProductVO> listRecProduct(Map<String, Object> map) {
		return contXmxDao.listRecProduct(map);
	}

	@Override
	public List<ContCategoryInfoDO> listCategoryInfo(Map<String, Object> map) {
		return contCategoryInfoDao.list(map);
	}

	@Override
	public Tree<ContCategoryDO> getTreeInfo(Map<String, Object> map) {
		return contCategoryService.getTreeInfo(map);
	}

	@Override
	public Integer queryNextContProductId(Integer contProductId) {
		return this.contXmxDao.queryNextContProductId(contProductId);
	}

	@Override
	public Integer queryPrevContProductId(Integer contProductId) {
		// TODO Auto-generated method stub
		return this.contXmxDao.queryPrevContProductId(contProductId);
	}



}
