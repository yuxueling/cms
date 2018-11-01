package com.sxyhton.cont.service;

import com.sxyhton.cont.domain.ContCategoryDO;
import com.sxyhton.cont.domain.ContCategoryInfoDO;
import com.sxyhton.cont.vo.ContProductVO;

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
	/**
	 * @param map 参数例子 contProductId=6, langType=en
	 * @return
	 */
	ContProductVO getProduct(Map<String,Object> map);

	List<ContProductVO> listCateProdsByProdId(Map<String,Object> map);

	List<ContProductVO> listProductByCategoryCode(Map<String,Object> map);

	List<ContCategoryDO> listProductDetailNavBar(Map<String,Object> map);

	List<ContProductVO> listRecProduct(Map<String,Object> map);

	List<ContCategoryInfoDO> listCategoryInfo(Map<String,Object> map);
	
	/**
	 * 根据当前的主键查询下一个主键
	 * @author Hzof
	 * @param contProductId 当前的主键
	 * @return 下一个主键
	 */
	Integer queryNextContProductId(Integer contProductId);
	/**
	 * 根据当前的主键查询上一个主键
	 * @author Hzof
	 * @param contProductId 当前的主键
	 * @return 上一个主键
	 */
	Integer queryPrevContProductId(Integer contProductId);
}
