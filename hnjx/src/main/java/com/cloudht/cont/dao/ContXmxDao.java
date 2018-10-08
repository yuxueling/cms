package com.cloudht.cont.dao;
import java.util.List;
import java.util.Map;

import com.cloudht.cont.vo.ContProductVO;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface ContXmxDao {

	List<ContProductVO> listProductByCategory(Map<String,Object> map);

	int countProductByCategory(Map<String,Object> map);

	ContProductVO getProduct(Map<String,Object> map);

}
