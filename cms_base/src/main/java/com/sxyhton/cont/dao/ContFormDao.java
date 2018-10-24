package com.sxyhton.cont.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.sxyhton.cont.domain.ContFormDO;

/**
 * 表单表
 * @author yuxueling
 * @email 980899486@qq.com
 * @date 2018-09-27 08:56:13
 */
@Mapper
public interface ContFormDao {

	ContFormDO get(Integer contFormId);
	
	List<ContFormDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ContFormDO contForm);
	
	int update(ContFormDO contForm);
	
	int remove(Integer cont_form_id);
	
	int batchRemove(Integer[] contFormIds);

}
