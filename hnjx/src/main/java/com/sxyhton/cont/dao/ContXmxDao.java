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


}
