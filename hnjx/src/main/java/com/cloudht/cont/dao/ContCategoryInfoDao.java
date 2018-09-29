package com.cloudht.cont.dao;

import com.cloudht.cont.domain.ContCategoryInfoDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author yuxueling
 * @email 980899486@qq.com
 * @date 2018-09-29 08:43:59
 */
@Mapper
public interface ContCategoryInfoDao {

	ContCategoryInfoDO get(Integer contCategoryInfoId);
	
	List<ContCategoryInfoDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ContCategoryInfoDO contCategoryInfo);
	
	int update(ContCategoryInfoDO contCategoryInfo);
	
	int remove(Integer cont_category_info_id);
	
	int batchRemove(Integer[] contCategoryInfoIds);
}
