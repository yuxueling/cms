package com.sxyhton.system.service;

import com.sxyhton.common.domain.Tree;
import com.sxyhton.system.domain.DeptDO;

import java.util.List;
import java.util.Map;

/**
 * 部门管理
 * 
 * @author yuxueling
 * @email 980899486@qq.com
 * @date 2017-09-27 14:28:36
 */
public interface DeptService {
	
	DeptDO get(Long deptId);
	
	List<DeptDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(DeptDO sysDept);
	
	int update(DeptDO sysDept);
	
	int remove(Long deptId);
	
	int batchRemove(Long[] deptIds);

	Tree<DeptDO> getTree();
	
	boolean checkDeptHasUser(Long deptId);
}
