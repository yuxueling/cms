package com.cloudht.common.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.cloudht.common.domain.SysFileDO;

/**
 * 文件上传
 * @author yuxueling
 * @email 980899486@qq.com
 * @date 2017-10-03 15:45:42
 */
@Mapper
public interface SysFileDao {

    SysFileDO get(Long id);
	
	List<SysFileDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(SysFileDO file);
	
	int update(SysFileDO file);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
