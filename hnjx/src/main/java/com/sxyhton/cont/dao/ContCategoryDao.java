package com.sxyhton.cont.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.sxyhton.cont.domain.ContCategoryDO;
import com.sxyhton.cont.domain.ContCategoryInfoDO;

/**
 * 
 * @author yuxueling
 * @email 980899486@qq.com
 * @date 2018-09-18 16:27:29
 */
@Mapper
public interface ContCategoryDao {

	ContCategoryDO get(Integer contCategoryId);
	
	List<ContCategoryDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ContCategoryDO contCategory);
	
	int update(ContCategoryDO contCategory);
	
	int remove(Integer cont_category_id);
	
	int batchRemove(Integer[] contCategoryIds);

	List<ContCategoryInfoDO> listInfoByDict(Map<String, Object> map);

	List<ContCategoryDO> listInfo(Map<String, Object> map);

	ContCategoryDO getContainInfo(Map<String, Object> map);

}
