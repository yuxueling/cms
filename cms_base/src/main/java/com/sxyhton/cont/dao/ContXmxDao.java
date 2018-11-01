package com.sxyhton.cont.dao;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.sxyhton.cont.vo.ContProductVO;
@Mapper
public interface ContXmxDao {

	List<ContProductVO> listProductByCategory(Map<String,Object> map);

	int countProductByCategory(Map<String,Object> map);

	ContProductVO getProduct(Map<String,Object> map);

	List<ContProductVO> listCateProdsByProdId(Map<String,Object> map);

	List<ContProductVO> listProductByCategoryCode(Map<String,Object> map);

	List<ContProductVO> listRecProduct(Map<String,Object> map);
	
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
