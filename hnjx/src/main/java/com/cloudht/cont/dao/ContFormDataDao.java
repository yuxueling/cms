package com.cloudht.cont.dao;

import com.cloudht.cont.domain.ContFormDataDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 表单数据表
 * @author yuxueling
 * @email 980899486@qq.com
 * @date 2018-09-27 08:56:14
 */
@Mapper
public interface ContFormDataDao {

	ContFormDataDO get(Integer contFormDataId);
	
	List<ContFormDataDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ContFormDataDO contFormData);
	
	int update(ContFormDataDO contFormData);
	
	int remove(Integer cont_form_data_id);
	
	int batchRemove(Integer[] contFormDataIds);
}
