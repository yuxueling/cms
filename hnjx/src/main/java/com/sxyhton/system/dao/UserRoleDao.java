package com.sxyhton.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.sxyhton.system.domain.UserRoleDO;

/**
 * 用户与角色对应关系
 * 
 * @author yuxueling
 * @email 980899486@qq.com
 * @date 2017-10-03 11:08:59
 */
@Mapper
public interface UserRoleDao {

	UserRoleDO get(Long id);

	List<UserRoleDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(UserRoleDO userRole);

	int update(UserRoleDO userRole);

	int remove(Long id);

	int batchRemove(Long[] ids);

	List<Long> listRoleId(Long userId);

	int removeByUserId(Long userId);

	int removeByRoleId(Long roleId);

	int batchSave(List<UserRoleDO> list);

	int batchRemoveByUserId(Long[] ids);
}