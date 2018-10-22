package com.cloudht.common.service;

import com.cloudht.common.domain.SysFileDO;

import java.util.List;
import java.util.Map;

/**
 * 文件上传
 * 
 * @author yuxueling
 * @email 980899486@qq.com
 * @date 2017-09-19 16:02:20
 */
public interface FileService {
	
	SysFileDO get(Long id);
	
	List<SysFileDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SysFileDO sysFile);
	
	int update(SysFileDO sysFile);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	/**
	 * 判断一个文件是否存在
	 * @param url FileDO中存的路径
	 * @return
	 */
    Boolean isExist(String url);
}
