package com.cloudht.cont.service;

import com.cloudht.cont.domain.ContProductDO;
import com.cloudht.cont.vo.ContProductVO;
import com.sxyht.common.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author yuxueling
 * @email 980899486@qq.com
 * @date 2018-09-18 16:27:29
 */
public interface ContXmxService {
	List<ContProductVO> listProductByCategory(Map<String,Object> map);

	int countProductByCategory(Map<String,Object> map);

	ContProductVO getProduct(Map<String,Object> map);

	List<ContProductVO> listCateProdsByProdId(Map<String,Object> map);

	List<ContProductVO> listProductByCategoryCode(Map<String,Object> map);

}
