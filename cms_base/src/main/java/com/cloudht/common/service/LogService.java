package com.cloudht.common.service;

import com.sxyht.common.utils.Query;
import org.springframework.stereotype.Service;

import com.cloudht.common.domain.LogDO;
import com.cloudht.common.domain.PageDO;
@Service
public interface LogService {
	void save(LogDO logDO);
	PageDO<LogDO> queryList(Query query);
	int remove(Long id);
	int batchRemove(Long[] ids);
}
